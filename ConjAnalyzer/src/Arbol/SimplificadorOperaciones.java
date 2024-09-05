package Arbol;
/**
 *
 * @author Keneth Lopez
 */
import Conjuntos.ConjuntoManager;
import Leyes.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.json.JSONObject;

public class SimplificadorOperaciones {
    private ConjuntoManager conjuntoManager;
    private Map<String, List<String>> leyesAplicadasPorOperacion;
    private List<Ley> leyes;
    private static final int MAX_ITERACIONES = 50; 

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

    public Nodo simplificar(Nodo nodo, String nombreOperacion) {
        Set<String> estadosVisitados = new HashSet<>();
        Map<String, Nodo> cacheSimplificaciones = new HashMap<>();
        List<String> leyesAplicadas = new ArrayList<>();
        boolean cambios;
        int iteraciones = 0;

        int complejidadInicial = nodo.calcularComplejidad();
        int maxIteracionesAdaptativo = Math.max(complejidadInicial * 10, MAX_ITERACIONES);
        int maxSinProgreso = 5; 
        int sinProgreso = 0;
        int complejidadPrev = complejidadInicial;
        Nodo mejorSimplificacion = nodo;

        do {
            cambios = false;
            Nodo resultadoSimplificado = simplificarRecursivo(nodo, estadosVisitados, cacheSimplificaciones, leyesAplicadas);

            if (!resultadoSimplificado.equals(nodo)) {
                cambios = true;
                nodo = resultadoSimplificado;

                int nuevaComplejidad = nodo.calcularComplejidad();

                // Control de expansión
                if (nuevaComplejidad > complejidadPrev) {
                    sinProgreso++;
                    if (sinProgreso >= maxSinProgreso) {
                        return mejorSimplificacion; // Revertir a la mejor simplificación
                    }
                } else {
                    sinProgreso = 0; // Reinicia si hay progreso
                    mejorSimplificacion = nodo; // Actualiza la mejor simplificación
                }

                complejidadPrev = nuevaComplejidad; // Actualiza la complejidad anterior
            }

            iteraciones++;
        } while (cambios && iteraciones < maxIteracionesAdaptativo);

        leyesAplicadasPorOperacion.put(nombreOperacion, leyesAplicadas);
        return nodo;
    }

    private Nodo simplificarRecursivo(Nodo nodo, Set<String> estadosVisitados, Map<String, Nodo> cacheSimplificaciones, List<String> leyesAplicadas) {
        String representacionNodo = nodo.mostrarContenido();

        // Verifica si el nodo ya está simplificado o está en el cache, devolverlo
        if (nodo.isSimplificado()) {
            return nodo;
        }
        if (cacheSimplificaciones.containsKey(representacionNodo)) {
            return cacheSimplificaciones.get(representacionNodo);
        }

        // Marcar el nodo como visitado
        estadosVisitados.add(representacionNodo);

        // Simplificar recursivamente el nodo y sus hijos
        nodo = simplificarNodo(nodo, estadosVisitados, cacheSimplificaciones, leyesAplicadas);

        // Aplicar leyes de simplificación al nodo actual
        Nodo nodoSimplificado = aplicarLeyesProfundas(nodo, leyesAplicadas, estadosVisitados, cacheSimplificaciones);

        // Almacenar el resultado en el cache
        cacheSimplificaciones.put(representacionNodo, nodoSimplificado);

        // Marcar el nodo como simplificado
        nodo.setSimplificado(true);

        return nodoSimplificado;
    }

    private Nodo simplificarNodo(Nodo nodo, Set<String> estadosVisitados, Map<String, Nodo> cacheSimplificaciones, List<String> leyesAplicadas) {
        if (nodo instanceof NodoBinario) {
            NodoBinario operacion = (NodoBinario) nodo;
            Nodo izquierdoSimplificado = simplificarRecursivo(operacion.getIzquierdo(), new HashSet<>(estadosVisitados), cacheSimplificaciones, leyesAplicadas);
            Nodo derechoSimplificado = simplificarRecursivo(operacion.getDerecho(), new HashSet<>(estadosVisitados), cacheSimplificaciones, leyesAplicadas);
            return new NodoBinario(operacion.getOperador(), izquierdoSimplificado, derechoSimplificado);
        } else if (nodo instanceof NodoUnario) {
            NodoUnario operacion = (NodoUnario) nodo;
            Nodo operandoSimplificado = simplificarRecursivo(operacion.getOperand(), new HashSet<>(estadosVisitados), cacheSimplificaciones, leyesAplicadas);
            return new NodoUnario(operacion.getOperador(), operandoSimplificado);
        }
        return nodo;
    }

    private Nodo aplicarLeyesProfundas(Nodo nodo, List<String> leyesAplicadas, Set<String> estadosVisitados, Map<String, Nodo> cacheSimplificaciones) {
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
                    } else {
                    }
                }
            }

            iteraciones++;
            if (iteraciones > MAX_ITERACIONES) {
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
            file.write(json.toString(4));
            System.out.println("Archivo JSON generado exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al generar el archivo JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
