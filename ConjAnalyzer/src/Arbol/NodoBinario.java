package Arbol;

import Conjuntos.GestorOperaciones;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;
import math.geom2d.Point2D;

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
