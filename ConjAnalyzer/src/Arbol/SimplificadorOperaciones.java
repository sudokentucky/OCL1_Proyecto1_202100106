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

/**
 *
 * @author Keneth Lopez
 */
public class SimplificadorOperaciones {
    private ConjuntoManager conjuntoManager;
    private Map<String, List<String>> leyesAplicadasPorOperacion; 

    public SimplificadorOperaciones(ConjuntoManager conjuntoManager) {
        this.conjuntoManager = conjuntoManager;
        this.leyesAplicadasPorOperacion = new HashMap<>();
    }

    public Nodo simplificar(Nodo nodo, String nombreOperacion) {
        Set<String> estadosVisitados = new HashSet<>();
        List<String> leyesAplicadas = new ArrayList<>(); // Lista para almacenar las leyes aplicadas en la operación actual
        Nodo resultadoSimplificado = simplificarRecursivo(nodo, nombreOperacion, estadosVisitados, leyesAplicadas);
        leyesAplicadasPorOperacion.put(nombreOperacion, leyesAplicadas); // Guardar las leyes aplicadas para esta operación
        return resultadoSimplificado;
    }

    private Nodo simplificarRecursivo(Nodo nodo, String nombreOperacion, Set<String> estadosVisitados, List<String> leyesAplicadas) {
    if (nodo instanceof NodoOperacion) {
        NodoOperacion operacion = (NodoOperacion) nodo;

        // Para notación prefija, se evalúa primero el operador, luego los operandos
        Nodo izquierdoSimplificado = simplificarRecursivo(operacion.getIzquierdo(), nombreOperacion, estadosVisitados, leyesAplicadas);
        Nodo derechoSimplificado = operacion.getDerecho() != null ? simplificarRecursivo(operacion.getDerecho(), nombreOperacion, estadosVisitados, leyesAplicadas) : null;

        NodoOperacion operacionSimplificada = new NodoOperacion(operacion.getOperador(), izquierdoSimplificado, derechoSimplificado);

        Nodo nodoResultado = aplicarPropiedades(operacionSimplificada, leyesAplicadas); // Pasar la lista de leyes aplicadas

        String representacionNodo = nodoResultado.mostrarContenido();
        if (!estadosVisitados.add(representacionNodo)) {
            System.out.println("Ciclo detectado, deteniendo simplificación para: " + representacionNodo);
            return nodoResultado;
        }

        estadosVisitados.add(representacionNodo);

        if (nombreOperacion.equals("OperacionPrincipal")) {
            Set<Character> conjuntoResultado = nodoResultado.evaluar();
            conjuntoManager.guardarOperacion(nombreOperacion, representacionNodo, conjuntoResultado);
        }

        return nodoResultado;
    }

    return nodo; // Si es un NodoConjunto, no necesita simplificación

    }

   private Nodo aplicarPropiedades(NodoOperacion operacion, List<String> leyesAplicadas) {
    Nodo izquierdo = operacion.getIzquierdo();
    Nodo derecho = operacion.getDerecho();

    switch (operacion.getOperador()) {
        case "^":
            if (izquierdo instanceof NodoOperacion) {
                NodoOperacion hijoIzquierdo = (NodoOperacion) izquierdo;
                if (hijoIzquierdo.getOperador().equals("^")) {
                    System.out.println("Aplicando Ley del Doble Complemento: ^(^" + hijoIzquierdo.getIzquierdo().mostrarContenido() + ") = " + hijoIzquierdo.getIzquierdo().mostrarContenido());
                    leyesAplicadas.add("ley de doble complemento");
                    return hijoIzquierdo.getIzquierdo();
                }
            }

            if (izquierdo instanceof NodoOperacion) {
                NodoOperacion hijoIzquierdo = (NodoOperacion) izquierdo;
                if (hijoIzquierdo.getOperador().equals("U") || hijoIzquierdo.getOperador().equals("&")) {
                    Nodo nuevoIzquierdo = new NodoOperacion("^", hijoIzquierdo.getIzquierdo(), null);
                    Nodo nuevoDerecho = new NodoOperacion("^", hijoIzquierdo.getDerecho(), null);
                    String nuevoOperador = hijoIzquierdo.getOperador().equals("U") ? "&" : "U";
                    System.out.println("Aplicando Ley de DeMorgan: ^(" + hijoIzquierdo.mostrarContenido() + ") = " + nuevoIzquierdo.mostrarContenido() + " " + nuevoOperador + " " + nuevoDerecho.mostrarContenido());
                    leyesAplicadas.add("leyes de DeMorgan");
                    return new NodoOperacion(nuevoOperador, simplificar(nuevoIzquierdo, "OperacionPrincipal"), simplificar(nuevoDerecho, "OperacionPrincipal"));
                }
            }
            break;

        case "U":
            if (derecho instanceof NodoOperacion) {
                NodoOperacion derechoOperacion = (NodoOperacion) derecho;
                if (derechoOperacion.getOperador().equals("&") && derechoOperacion.getIzquierdo().equals(izquierdo)) {
                    System.out.println("Aplicando Propiedad de Absorción: " + izquierdo.mostrarContenido() + " U (" + derechoOperacion.mostrarContenido() + ") = " + izquierdo.mostrarContenido());
                    leyesAplicadas.add("propiedad de absorción");
                    return izquierdo;
                }
            }
            
            if (derecho != null && izquierdo instanceof NodoConjunto && derecho instanceof NodoConjunto) {
                System.out.println("Aplicando Propiedad Conmutativa: " + izquierdo.mostrarContenido() + " U " + derecho.mostrarContenido());
                leyesAplicadas.add("propiedad conmutativa");
                if (izquierdo.toString().compareTo(derecho.toString()) > 0) {
                    Nodo temp = izquierdo;
                    izquierdo = derecho;
                    derecho = temp;
                    operacion = new NodoOperacion("U", izquierdo, derecho);
                }
            }

            if (izquierdo.equals(derecho)) {
                System.out.println("Aplicando Propiedad Idempotente: " + izquierdo.mostrarContenido() + " U " + derecho.mostrarContenido() + " = " + izquierdo.mostrarContenido());
                leyesAplicadas.add("propiedad idempotente");
                return izquierdo;
            }

            if (derecho instanceof NodoOperacion) {
                NodoOperacion derechoOperacion = (NodoOperacion) derecho;
                if (derechoOperacion.getOperador().equals("U")) {
                    System.out.println("Aplicando Propiedad Asociativa: " + izquierdo.mostrarContenido() + " U (" + derechoOperacion.mostrarContenido() + ")");
                    leyesAplicadas.add("propiedad asociativa");
                    return new NodoOperacion("U", new NodoOperacion("U", izquierdo, derechoOperacion.getIzquierdo()), derechoOperacion.getDerecho());
                }
            }

            
            break;

        case "&":
            if (derecho != null && izquierdo instanceof NodoConjunto && derecho instanceof NodoConjunto) {
                System.out.println("Aplicando Propiedad Conmutativa: " + izquierdo.mostrarContenido() + " ∩ " + derecho.mostrarContenido());
                leyesAplicadas.add("propiedad conmutativa");
                if (izquierdo.toString().compareTo(derecho.toString()) > 0) {
                    Nodo temp = izquierdo;
                    izquierdo = derecho;
                    derecho = temp;
                    operacion = new NodoOperacion("&", izquierdo, derecho);
                }
            }

            if (izquierdo.equals(derecho)) {
                System.out.println("Aplicando Propiedad Idempotente: " + izquierdo.mostrarContenido() + " ∩ " + derecho.mostrarContenido() + " = " + izquierdo.mostrarContenido());
                leyesAplicadas.add("propiedad idempotente");
                return izquierdo;
            }

            if (derecho instanceof NodoOperacion) {
                NodoOperacion derechoOperacion = (NodoOperacion) derecho;
                if (derechoOperacion.getOperador().equals("&")) {
                    System.out.println("Aplicando Propiedad Asociativa: " + izquierdo.mostrarContenido() + " ∩ (" + derechoOperacion.mostrarContenido() + ")");
                    leyesAplicadas.add("propiedad asociativa");
                    return new NodoOperacion("&", new NodoOperacion("&", izquierdo, derechoOperacion.getIzquierdo()), derechoOperacion.getDerecho());
                }
            }

            if (derecho instanceof NodoOperacion) {
                NodoOperacion derechoOperacion = (NodoOperacion) derecho;
                if (derechoOperacion.getOperador().equals("U") && derechoOperacion.getIzquierdo().equals(izquierdo)) {
                    System.out.println("Aplicando Propiedad de Absorción: " + izquierdo.mostrarContenido() + " ∩ (" + derechoOperacion.mostrarContenido() + ") = " + izquierdo.mostrarContenido());
                    leyesAplicadas.add("propiedad de absorción");
                    return izquierdo;
                }
            }
            break;
    }

    return operacion; // Devolver la operación si no se pudo simplificar más
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
