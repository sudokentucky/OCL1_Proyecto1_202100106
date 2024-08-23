/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;

import java.util.Set;
import Conjuntos.ConjuntoManager;

/**
 *
 * @author Keneth Lopez
 */
public class SimplificadorOperaciones {
    private ConjuntoManager conjuntoManager;

    public SimplificadorOperaciones(ConjuntoManager conjuntoManager) {
        this.conjuntoManager = conjuntoManager;
    }

    public Nodo simplificar(Nodo nodo, String nombreOperacion) {
        if (nodo instanceof NodoOperacion) {
            NodoOperacion operacion = (NodoOperacion) nodo;

            // Simplificar recursivamente los hijos
            Nodo izquierdoSimplificado = simplificar(operacion.getIzquierdo(), nombreOperacion);
            Nodo derechoSimplificado = operacion.getDerecho() != null ? simplificar(operacion.getDerecho(), nombreOperacion) : null;

            // Crear una nueva operación con los hijos simplificados
            NodoOperacion operacionSimplificada = new NodoOperacion(operacion.getOperador(), izquierdoSimplificado, derechoSimplificado);

            // Aplicar propiedades de la teoría de conjuntos
            Nodo nodoResultado = aplicarPropiedades(operacionSimplificada);

            // Guardar solo la operación principal
            if (nombreOperacion.equals("OperacionPrincipal")) {
                String notacion = nodoResultado.mostrarContenido();
                Set<Character> conjuntoResultado = nodoResultado.evaluar();
                conjuntoManager.guardarOperacion(nombreOperacion, notacion, conjuntoResultado);
            }

            return nodoResultado;
        }

        // Si es un NodoConjunto, no necesita simplificación
        return nodo;
    }


    private Nodo aplicarPropiedades(NodoOperacion operacion) {
        Nodo izquierdo = operacion.getIzquierdo();
        Nodo derecho = operacion.getDerecho();

        switch (operacion.getOperador()) {
            case "^":
                // Ley del Doble Complemento: ^(^A) = A
                if (izquierdo instanceof NodoOperacion) {
                    NodoOperacion hijoIzquierdo = (NodoOperacion) izquierdo;
                    if (hijoIzquierdo.getOperador().equals("^")) {
                        System.out.println("Aplicando Ley del Doble Complemento: ^(^" + hijoIzquierdo.getIzquierdo().mostrarContenido() + ") = " + hijoIzquierdo.getIzquierdo().mostrarContenido());
                        return hijoIzquierdo.getIzquierdo();
                    }
                }

                // Leyes de De Morgan
                if (izquierdo instanceof NodoOperacion) {
                    NodoOperacion hijoIzquierdo = (NodoOperacion) izquierdo;
                    if (hijoIzquierdo.getOperador().equals("U") || hijoIzquierdo.getOperador().equals("&")) {
                        Nodo nuevoIzquierdo = new NodoOperacion("^", hijoIzquierdo.getIzquierdo(), null);
                        Nodo nuevoDerecho = new NodoOperacion("^", hijoIzquierdo.getDerecho(), null);
                        String nuevoOperador = hijoIzquierdo.getOperador().equals("U") ? "&" : "U";
                        System.out.println("Aplicando Ley de DeMorgan: ^(" + hijoIzquierdo.mostrarContenido() + ") = " + nuevoIzquierdo.mostrarContenido() + " " + nuevoOperador + " " + nuevoDerecho.mostrarContenido());

                        // Corregir la llamada a `simplificar` para que incluya el nombre de la operación
                        return new NodoOperacion(nuevoOperador, simplificar(nuevoIzquierdo, "OperacionPrincipal"), simplificar(nuevoDerecho, "OperacionPrincipal"));
                    }
                }

                break;

            case "U":
                // Propiedad Conmutativa: A U B = B U A
                if (derecho != null && izquierdo instanceof NodoConjunto && derecho instanceof NodoConjunto) {
                    System.out.println("Aplicando Propiedad Conmutativa: " + izquierdo.mostrarContenido() + " U " + derecho.mostrarContenido());
                    if (izquierdo.toString().compareTo(derecho.toString()) > 0) {
                        Nodo temp = izquierdo;
                        izquierdo = derecho;
                        derecho = temp;
                        operacion = new NodoOperacion("U", izquierdo, derecho);
                    }
                }

                // Propiedad Idempotente: A U A = A
                if (izquierdo.equals(derecho)) {
                    System.out.println("Aplicando Propiedad Idempotente: " + izquierdo.mostrarContenido() + " U " + derecho.mostrarContenido() + " = " + izquierdo.mostrarContenido());
                    return izquierdo;
                }

                // Propiedad Asociativa: A U (B U C) = (A U B) U C
                if (derecho instanceof NodoOperacion) {
                    NodoOperacion derechoOperacion = (NodoOperacion) derecho;
                    if (derechoOperacion.getOperador().equals("U")) {
                        System.out.println("Aplicando Propiedad Asociativa: " + izquierdo.mostrarContenido() + " U (" + derechoOperacion.mostrarContenido() + ")");
                        return new NodoOperacion("U", new NodoOperacion("U", izquierdo, derechoOperacion.getIzquierdo()), derechoOperacion.getDerecho());
                    }
                }

                // Propiedad de Absorción: A U (A ∩ B) = A
                if (derecho instanceof NodoOperacion) {
                    NodoOperacion derechoOperacion = (NodoOperacion) derecho;
                    if (derechoOperacion.getOperador().equals("&") && derechoOperacion.getIzquierdo().equals(izquierdo)) {
                        System.out.println("Aplicando Propiedad de Absorción: " + izquierdo.mostrarContenido() + " U (" + derechoOperacion.mostrarContenido() + ") = " + izquierdo.mostrarContenido());
                        return izquierdo;
                    }
                }
                break;

            case "&":
                // Propiedad Conmutativa: A ∩ B = B ∩ A
                if (derecho != null && izquierdo instanceof NodoConjunto && derecho instanceof NodoConjunto) {
                    System.out.println("Aplicando Propiedad Conmutativa: " + izquierdo.mostrarContenido() + " ∩ " + derecho.mostrarContenido());
                    if (izquierdo.toString().compareTo(derecho.toString()) > 0) {
                        Nodo temp = izquierdo;
                        izquierdo = derecho;
                        derecho = temp;
                        operacion = new NodoOperacion("&", izquierdo, derecho);
                    }
                }

                // Propiedad Idempotente: A ∩ A = A
                if (izquierdo.equals(derecho)) {
                    System.out.println("Aplicando Propiedad Idempotente: " + izquierdo.mostrarContenido() + " ∩ " + derecho.mostrarContenido() + " = " + izquierdo.mostrarContenido());
                    return izquierdo;
                }

                // Propiedad Asociativa: A ∩ (B ∩ C) = (A ∩ B) ∩ C
                if (derecho instanceof NodoOperacion) {
                    NodoOperacion derechoOperacion = (NodoOperacion) derecho;
                    if (derechoOperacion.getOperador().equals("&")) {
                        System.out.println("Aplicando Propiedad Asociativa: " + izquierdo.mostrarContenido() + " ∩ (" + derechoOperacion.mostrarContenido() + ")");
                        return new NodoOperacion("&", new NodoOperacion("&", izquierdo, derechoOperacion.getIzquierdo()), derechoOperacion.getDerecho());
                    }
                }

                // Propiedad de Absorción: A ∩ (A U B) = A
                if (derecho instanceof NodoOperacion) {
                    NodoOperacion derechoOperacion = (NodoOperacion) derecho;
                    if (derechoOperacion.getOperador().equals("U") && derechoOperacion.getIzquierdo().equals(izquierdo)) {
                        System.out.println("Aplicando Propiedad de Absorción: " + izquierdo.mostrarContenido() + " ∩ (" + derechoOperacion.mostrarContenido() + ") = " + izquierdo.mostrarContenido());
                        return izquierdo;
                    }
                }
                break;

        }

        // Devolver la operación simplificada si no se pudo simplificar más
        return operacion;
    }
}