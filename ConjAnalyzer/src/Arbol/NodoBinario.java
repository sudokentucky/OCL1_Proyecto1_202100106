package Arbol;
/*
 *
 * @author Keneth Lopez
 */
import Conjuntos.GestorOperaciones;
import java.awt.geom.Area;
import java.awt.Shape;
import java.util.Set;


public class NodoBinario extends Nodo {
    private String operador;
    private Nodo izquierdo;
    private Nodo derecho;
    private Area areaCache; 

    public NodoBinario(String operador, Nodo izquierdo, Nodo derecho) {
        if (operador == null || izquierdo == null || derecho == null) {
            throw new IllegalArgumentException("Operador o nodos no pueden ser nulos");
        }
        this.operador = operador;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.areaCache = null;
    }

    @Override
    public Set<Character> evaluar() {
        Set<Character> resultadoIzquierdo = izquierdo.evaluar();
        Set<Character> resultadoDerecho = derecho.evaluar();

        switch (operador) {
            case "U":
                return GestorOperaciones.union(resultadoIzquierdo, resultadoDerecho);
            case "&":
                return GestorOperaciones.interseccion(resultadoIzquierdo, resultadoDerecho);
            case "-":
                return GestorOperaciones.diferencia(resultadoIzquierdo, resultadoDerecho);
            default:
                throw new IllegalArgumentException("Operador binario desconocido: " + operador);
        }
    }
    @Override
    public String mostrarContenido() {
        return "(" + izquierdo.mostrarContenido() + " " + operador + " " + derecho.mostrarContenido() + ")";
    }

    public Shape obtenerForma() {
        return areaCache;
    }

    @Override
    public String toString() {
        return mostrarContenido();
    }
    

    @Override
        public void recopilarConjuntos(Set<String> conjuntos) {
            izquierdo.recopilarConjuntos(conjuntos);
            derecho.recopilarConjuntos(conjuntos);
        }

    public String getOperador() {
        return operador;
    }
    
        @Override
    public int calcularProfundidad() {
        // Calcular la profundidad del nodo binario como el máximo entre sus hijos
        return 1 + Math.max(izquierdo.calcularProfundidad(), derecho.calcularProfundidad());
    }
    
    @Override
    public int contarNodos() {
        return 1 + izquierdo.contarNodos() + derecho.contarNodos();
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }
    
    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}
