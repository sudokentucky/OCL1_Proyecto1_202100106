/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conjuntos;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Keneth Lopez
 */
public class ConjuntoManager {
    // Clase interna que representa una operación
    public static class Operacion {
        private String notacion;
        private Set<Character> conjuntoResultante;

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
    }

    private Map<String, Set<Character>> conjuntos;
    private Map<String, Operacion> operaciones;

    public ConjuntoManager() {
        this.conjuntos = new HashMap<>();
        this.operaciones = new HashMap<>();
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
    public Set<Character> generarConjuntoDesdeLista(String... elementos) {
        Set<Character> conjunto = new HashSet<>();
        for (String elemento : elementos) {
            for (char c : elemento.toCharArray()) {
                conjunto.add(c);
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

    // Evaluar un conjunto contra el resultado de una operación
    public void evaluarConjunto(Set<Character> conjuntoAEvaluar, String nombreOperacion) {
        Set<Character> conjuntoResultante = obtenerResultadoOperacion(nombreOperacion);
        if (conjuntoResultante == null) {
            System.out.println("Operación " + nombreOperacion + " no encontrada.");
            return;
        }

        System.out.println("===============\nEvaluar: " + nombreOperacion + "\n===============");

        boolean todoCoincide = true;
        for (Character c : conjuntoAEvaluar) {
            if (conjuntoResultante.contains(c)) {
                System.out.println(c + " -> exitoso");
            } else {
                System.out.println(c + " -> fallo");
                todoCoincide = false;
            }
        }

        if (todoCoincide) {
            System.out.println("Todos los elementos del conjunto están presentes en el conjunto resultante de la operación " + nombreOperacion + ".");
        } else {
            System.out.println("La evaluación falló.");
        }

        System.out.println("===============");
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
        for (Map.Entry<String, Operacion> entry : operaciones.entrySet()) {
            String nombre = entry.getKey();
            String notacion = entry.getValue().getNotacion();
            resultado.append(nombre)
                     .append(" : ")
                     .append(notacion)
                     .append("\n");
        }
        return resultado.toString();
    }
}