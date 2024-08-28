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

    /**
     * Construir el árbol desde una lista de tokens en notación prefija
     * @param tokens Lista de tokens en notación prefija
     * @return Nodo raíz del árbol construido
     * @throws Exception Si ocurre un error en la construcción del árbol
     */
    public Nodo construirArbol(List<String> tokens) throws Exception {
        Iterator<String> iteradorTokens = tokens.iterator();
        Nodo arbol = construirNodo(iteradorTokens);
        // Llamar al método para imprimir la expresión prefija después de construir el árbol
        imprimirExpresionPrefija(arbol);
        return arbol;
    }

    /**
     * Método recursivo para construir un nodo a partir del iterador de tokens
     * @param iteradorTokens Iterador de tokens en notación prefija
     * @return Nodo construido
     * @throws Exception Si ocurre un error en la construcción del nodo
     */
    private Nodo construirNodo(Iterator<String> iteradorTokens) throws Exception {
        if (!iteradorTokens.hasNext()) {
            throw new Exception("No hay más tokens disponibles para construir el árbol.");
        }

        String token = iteradorTokens.next(); // Extraer el siguiente token

        switch (token) {
            case "U":
            case "&":
            case "-":
                // Operadores binarios: construir subárbol izquierdo y derecho
                Nodo izquierdo = construirNodo(iteradorTokens);
                Nodo derecho = construirNodo(iteradorTokens);
                return new NodoOperacion(token, izquierdo, derecho);

            case "^":
                // Operador unario: construir subárbol izquierdo
                Nodo operand = construirNodo(iteradorTokens);
                return new NodoOperacion(token, operand, null);

            default:
                // Token de conjunto
                return new NodoConjunto(token, conjuntoManager);
        }
    }

    /**
     * Método para imprimir la expresión en notación prefija del árbol
     * @param nodo Nodo raíz del árbol a imprimir
     */
    private void imprimirExpresionPrefija(Nodo nodo) {
        if (nodo instanceof NodoOperacion) {
            NodoOperacion operacion = (NodoOperacion) nodo;
            System.out.print(operacion.getOperador() + " ");
            imprimirExpresionPrefija(operacion.getIzquierdo());
            if (operacion.getDerecho() != null) {
                imprimirExpresionPrefija(operacion.getDerecho());
            }
        } else if (nodo instanceof NodoConjunto) {
            NodoConjunto conjunto = (NodoConjunto) nodo;
            System.out.print(conjunto.getNombreConjunto() + " ");
        }
    }
}
