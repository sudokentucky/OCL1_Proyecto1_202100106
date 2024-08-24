/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;

import Conjuntos.ConjuntoManager;
import java.util.Set;

/**
 *
 * @author Keneth Lopez
 */
public class NodoConjunto extends Nodo {
    private String nombreConjunto;
    private ConjuntoManager conjuntoManager;

    public NodoConjunto(String nombreConjunto, ConjuntoManager conjuntoManager) {
        this.nombreConjunto = nombreConjunto;
        this.conjuntoManager = conjuntoManager;
    }

    @Override
    public Set<Character> evaluar() {
        return conjuntoManager.obtenerConjunto(nombreConjunto);
    }

    @Override
    public String mostrarContenido() {
        return nombreConjunto;
    }

    @Override
    public String toString() {
        return nombreConjunto;
    }

    public String getNombreConjunto() {
        return nombreConjunto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodoConjunto that = (NodoConjunto) obj;
        return nombreConjunto.equals(that.nombreConjunto);
    }

    @Override
    public int hashCode() {
        return nombreConjunto.hashCode();
    }
}