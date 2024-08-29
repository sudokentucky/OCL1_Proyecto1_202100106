/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;
import Conjuntos.GestorOperaciones;
import java.util.List;
import java.util.Set;
/**
 *
 * @author Keneth Lopez
 */
public class NodoBinario extends Nodo { 
    private String operador;
    private Nodo izquierdo;
    private Nodo derecho;

    public NodoBinario(String operador, Nodo izquierdo, Nodo derecho) {
        this.operador = operador;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
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
