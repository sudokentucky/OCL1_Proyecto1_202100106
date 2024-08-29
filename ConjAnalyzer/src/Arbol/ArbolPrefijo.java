package Arbol;

import Conjuntos.ConjuntoManager;
import java.util.Iterator;
import java.util.List;

/**
 * Clase para construir un árbol a partir de una notación prefija
 * 
 * @author Keneth Lopez
 */
public class ArbolPrefijo {
    private ConjuntoManager conjuntoManager;

    public ArbolPrefijo(ConjuntoManager conjuntoManager) {
        this.conjuntoManager = conjuntoManager;
    }

    public Nodo construirArbol(List<String> tokens) throws Exception {
        Iterator<String> iteradorTokens = tokens.iterator();
        Nodo arbol = construirNodo(iteradorTokens);
        imprimirExpresionPrefija(arbol);
        return arbol;
    }

    private Nodo construirNodo(Iterator<String> iteradorTokens) throws Exception {
        if (!iteradorTokens.hasNext()) {
            throw new Exception("No hay más tokens disponibles para construir el árbol.");
        }

        String token = iteradorTokens.next();
        switch (token) {
            case "-":
            case "&":
            case "U": {
                // Operador binario: construir subárbol izquierdo y derecho
                Nodo operandoIzquierdo = construirNodo(iteradorTokens);
                Nodo operandoDerecho = construirNodo(iteradorTokens);
                return new NodoBinario(token, operandoIzquierdo, operandoDerecho);
            }
            case "^": {
                // Operador unario: construir subárbol izquierdo
                Nodo operando = construirNodo(iteradorTokens);
                return new NodoUnario(token, operando);
            }
            default:
                return new NodoConjunto(token, conjuntoManager);
        }
    }

    private void imprimirExpresionPrefija(Nodo nodo) {
        if (nodo instanceof NodoBinario) {
            NodoBinario operacion = (NodoBinario) nodo;
            System.out.print(operacion.getOperador() + " ");
            imprimirExpresionPrefija(operacion.getIzquierdo());
            imprimirExpresionPrefija(operacion.getDerecho());
        } else if (nodo instanceof NodoUnario) {
            NodoUnario operacion = (NodoUnario) nodo;
            System.out.print(operacion.getOperador() + " ");
            imprimirExpresionPrefija(operacion.getOperand());
        } else if (nodo instanceof NodoConjunto) {
            NodoConjunto conjunto = (NodoConjunto) nodo;
            System.out.print(conjunto.getNombreConjunto() + " ");
        }
    }
}
