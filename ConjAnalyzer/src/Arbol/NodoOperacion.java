/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;

import Conjuntos.GestorOperaciones;
import java.util.Set;

/**
 *
 * @author Keneth Lopez
 */
public class NodoOperacion extends Nodo {
    private String operador;
    private Nodo izquierdo;
    private Nodo derecho;

    public NodoOperacion(String operador, Nodo izquierdo, Nodo derecho) {
        this.operador = operador;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }

    @Override
    public Set<Character> evaluar() {
        Set<Character> resultadoIzquierdo = izquierdo.evaluar();
        Set<Character> resultadoDerecho = derecho != null ? derecho.evaluar() : null;

        switch (operador) {
            case "U":
                return GestorOperaciones.union(resultadoIzquierdo, resultadoDerecho);
            case "&":
                return GestorOperaciones.interseccion(resultadoIzquierdo, resultadoDerecho);
            case "-":
                return GestorOperaciones.diferencia(resultadoIzquierdo, resultadoDerecho);
            case "^":
                return GestorOperaciones.complemento(resultadoIzquierdo);
            default:
                throw new IllegalArgumentException("Operador desconocido: " + operador);
        }
    }

    @Override
    public String mostrarContenido() {
        String contenidoIzquierdo = izquierdo.mostrarContenido();
        String contenidoDerecho = derecho != null ? derecho.mostrarContenido() : "";

        switch (operador) {
            case "U":
                return "(" + contenidoIzquierdo + " U " + contenidoDerecho + ")";
            case "&":
                return "(" + contenidoIzquierdo + " & " + contenidoDerecho + ")";
            case "-":
                return "(" + contenidoIzquierdo + " - " + contenidoDerecho + ")";
            case "^":
                return "^" + contenidoIzquierdo;
            default:
                throw new IllegalArgumentException("Operador desconocido: " + operador);
        }
    }

    @Override
    public String toString() {
        return mostrarContenido();
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
}
