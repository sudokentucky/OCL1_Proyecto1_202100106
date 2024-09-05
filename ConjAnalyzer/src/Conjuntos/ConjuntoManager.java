package Conjuntos;
/**
 *
 * @author Keneth Lopez
 */
import Arbol.Nodo;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class ConjuntoManager {
    // Clase interna que representa una operación
    public static class Operacion {
        private String notacion;
        private Set<Character> conjuntoResultante;
        private Nodo nodoOperacion; 

        public Operacion(String notacion, Set<Character> conjuntoResultante) {
            this.notacion = notacion;
            this.conjuntoResultante = conjuntoResultante;
        }

        public String getNotacion() {
            return notacion;
        }

        public Set<Character> getConjuntoResultante() {
            return conjuntoResultante;
        }

        public Nodo getNodo() {
            return nodoOperacion;
        }
    }

    private Map<String, Set<Character>> conjuntos;
    private Map<String, Operacion> operaciones;

    public ConjuntoManager() {
        this.conjuntos = new HashMap<>();
        this.operaciones = new HashMap<>();
    }
    
    // Método para obtener el índice de un conjunto por su nombre
    public int obtenerIndiceConjunto(String nombreConjunto) {
        int index = 0;
        for (String nombre : conjuntos.keySet()) {
            if (nombre.equals(nombreConjunto)) {
                return index;
            }
            index++;
        }
        throw new IllegalArgumentException("Conjunto no encontrado: " + nombreConjunto);
    }

    // Generar un conjunto a partir de un rango, por ejemplo, 'a'~'z' o '0'~'4'
    public Set<Character> generarConjuntoDesdeRango(String inicio, String fin) {
        Set<Character> conjunto = new HashSet<>();
        char start = inicio.charAt(0);
        char end = fin.charAt(0);
        for (char c = start; c <= end; c++) {
            conjunto.add(c);
        }
        return conjunto;
    }

    // Generar un conjunto a partir de una lista explícita de elementos
    public Set<Character> generarConjuntoDesdeLista(String elementos) {
        Set<Character> conjunto = new HashSet<>();
        // Divide la cadena por comas y elimina espacios en blanco
        String[] caracteres = elementos.split(",\\s*");
        for (String charStr : caracteres) {
            if (!charStr.isEmpty()) {
                conjunto.add(charStr.charAt(0));  // Añadir cada carácter al conjunto
            }
        }
        return conjunto;
    }

    // Almacenar un conjunto definido
    public void definirConjunto(String nombre, Set<Character> elementos) {
        conjuntos.put(nombre, elementos);
    }

    // Obtener un conjunto por su nombre
    public Set<Character> obtenerConjunto(String nombre) {
        return conjuntos.get(nombre);
    }
    
    // Guardar una operación realizada junto con su nombre, notación y conjunto resultante
    public void guardarOperacion(String nombreOperacion, String notacion, Set<Character> conjuntoResultante) {
        operaciones.put(nombreOperacion, new Operacion(notacion, conjuntoResultante));
        System.out.println("Operación guardada: " + nombreOperacion + " - Notación: " + notacion);
    }

    // Obtener la notación de una operación
    public String obtenerNotacionOperacion(String nombreOperacion) {
        Operacion operacion = operaciones.get(nombreOperacion);
        return operacion != null ? operacion.getNotacion() : null;
    }

    // Obtener el conjunto resultante de una operación
    public Set<Character> obtenerResultadoOperacion(String nombreOperacion) {
        Operacion operacion = operaciones.get(nombreOperacion);
        return operacion != null ? operacion.getConjuntoResultante() : null;
    }

    // Obtener todos los conjuntos registrados con sus nombres y elementos
    public String getConjuntos() {
        StringBuilder resultado = new StringBuilder();
        for (Map.Entry<String, Set<Character>> entry : conjuntos.entrySet()) {
            String nombre = entry.getKey();
            Set<Character> elementos = entry.getValue();
            resultado.append(nombre)
                     .append(" : ")
                     .append(elementos.toString())
                     .append("\n");
        }
        return resultado.toString();
    }

    // Obtener todas las operaciones guardadas con su notación
    public String getOperaciones() {
        StringBuilder resultado = new StringBuilder();
        if (operaciones.isEmpty()) {
            return "No hay operaciones definidas.";
        }
        for (Map.Entry<String, Operacion> entry : operaciones.entrySet()) {
            String nombre = entry.getKey();
            String notacion = entry.getValue().getNotacion();
            resultado.append(nombre)
                     .append(" : ")
                     .append(notacion)
                     .append("\n");
        }

        // Devolver el resultado como una cadena
        return resultado.toString();
    }

    // Método para obtener todas las operaciones
    public Map<String, Operacion> getOperacionesMap() {
        return operaciones;
    }

}
