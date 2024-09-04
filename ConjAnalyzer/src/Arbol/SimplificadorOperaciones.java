package Arbol;

import java.util.HashSet;
import java.util.Set;
import Conjuntos.ConjuntoManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import Leyes.*;
import java.util.Arrays;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class SimplificadorOperaciones {
    private ConjuntoManager conjuntoManager;
    private Map<String, List<String>> leyesAplicadasPorOperacion;
    private List<Ley> leyes;

    public SimplificadorOperaciones(ConjuntoManager conjuntoManager) {
        this.conjuntoManager = conjuntoManager;
        this.leyesAplicadasPorOperacion = new HashMap<>();
        this.leyes = Arrays.asList(
            new Absorcion(), 
            new Asociativa(), 
            new Conmutativa(), 
            new DeMorgan(), 
            new Distributiva(), 
            new DobleComplemento(), 
            new Idempotente()
        );
    }

    // Método para simplificar un árbol y guardar el árbol simplificado en el objeto ArbolExpresion
    public Nodo simplificar(Nodo nodo, String nombreOperacion) {
        Set<String> estadosVisitados = new HashSet<>();
        List<String> leyesAplicadas = new ArrayList<>();
        Nodo resultadoSimplificado = simplificarRecursivo(nodo, estadosVisitados, leyesAplicadas);

        // Guardar las leyes aplicadas por la operación
        leyesAplicadasPorOperacion.put(nombreOperacion, leyesAplicadas);

        return resultadoSimplificado;
    }

    private Nodo simplificarRecursivo(Nodo nodo, Set<String> estadosVisitados, List<String> leyesAplicadas) {
        String representacionNodo = nodo.mostrarContenido();
        if (estadosVisitados.contains(representacionNodo)) {
            System.out.println("Ciclo detectado, deteniendo simplificación para: " + representacionNodo);
            return nodo;
        }

        // Marcar el nodo como visitado
        estadosVisitados.add(representacionNodo);

        // Simplificar recursivamente los nodos hijos si es un nodo binario
        if (nodo instanceof NodoBinario) {
            NodoBinario operacion = (NodoBinario) nodo;

            // Aplicar simplificación recursiva a los hijos
            Nodo izquierdoSimplificado = simplificarRecursivo(operacion.getIzquierdo(), new HashSet<>(estadosVisitados), leyesAplicadas);
            Nodo derechoSimplificado = simplificarRecursivo(operacion.getDerecho(), new HashSet<>(estadosVisitados), leyesAplicadas);

            // Reconstruir el nodo binario simplificado
            NodoBinario operacionSimplificada = new NodoBinario(operacion.getOperador(), izquierdoSimplificado, derechoSimplificado);
            Nodo nodoResultado = aplicarLeyes(operacionSimplificada, leyesAplicadas, estadosVisitados);

            return nodoResultado;

        } else if (nodo instanceof NodoUnario) {
            NodoUnario operacion = (NodoUnario) nodo;

            // Aplicar simplificación recursiva al operando
            Nodo operandoSimplificado = simplificarRecursivo(operacion.getOperand(), new HashSet<>(estadosVisitados), leyesAplicadas);

            // Reconstruir el nodo unario simplificado
            NodoUnario operacionSimplificada = new NodoUnario(operacion.getOperador(), operandoSimplificado);
            Nodo nodoResultado = aplicarLeyes(operacionSimplificada, leyesAplicadas, estadosVisitados);

            return nodoResultado;
        }

        // Si es un nodo de conjunto o un nodo que no requiere simplificación adicional
        return nodo;
    }

    private Nodo aplicarLeyes(Nodo nodo, List<String> leyesAplicadas, Set<String> estadosVisitados) {
        boolean seAplicoLey;
        int iteraciones = 0;
        Set<String> leyesAplicadasAesteNodo = new HashSet<>();

        do {
            seAplicoLey = false;
            for (Ley ley : leyes) {
                if (ley.esAplicable(nodo) && !leyesAplicadasAesteNodo.contains(ley.getClass().getSimpleName())) {
                    Nodo nodoSimplificado = ley.aplicar(nodo, leyesAplicadas);
                    String representacionSimplificada = nodoSimplificado.mostrarContenido();

                    // Solo continuar si el nodo simplificado es diferente y no ha sido visitado antes
                    if (!representacionSimplificada.equals(nodo.mostrarContenido()) && !estadosVisitados.contains(representacionSimplificada)) {
                        nodo = nodoSimplificado;
                        estadosVisitados.add(representacionSimplificada);
                        leyesAplicadasAesteNodo.add(ley.getClass().getSimpleName());
                        seAplicoLey = true;
                        break;
                    }
                }
            }

            if (++iteraciones > 50) { // Limitar a 50 iteraciones para prevenir bucles infinitos
                System.out.println("Límite de iteraciones alcanzado, deteniendo simplificación.");
                break;
            }

        } while (seAplicoLey);

        return nodo;
    }

    public void generarJSON(String rutaArchivo) {
        JSONObject json = new JSONObject();

        for (Map.Entry<String, List<String>> entrada : leyesAplicadasPorOperacion.entrySet()) {
            String nombreOperacion = entrada.getKey();
            List<String> leyes = entrada.getValue();

            if (leyes.isEmpty()) {
                json.put(nombreOperacion, "No se puede simplificar la operación");
            } else {
                JSONObject detallesOperacion = new JSONObject();
                detallesOperacion.put("leyes", leyes);

                String notacionOperacion = conjuntoManager.obtenerNotacionOperacion(nombreOperacion);
                if (notacionOperacion == null) {
                    System.err.println("Error: La notación de la operación '" + nombreOperacion + "' es nula.");
                    notacionOperacion = "Notación no disponible";
                }

                detallesOperacion.put("conjunto simplificado", notacionOperacion);
                json.put(nombreOperacion, detallesOperacion);
            }
        }

        try (FileWriter file = new FileWriter(rutaArchivo)) {
            file.write(json.toString(4)); // Indentado para mejor legibilidad
            System.out.println("Archivo JSON generado exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
