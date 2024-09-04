package Arbol;

import java.util.HashSet;
import java.util.Set;

public class ArbolExpresion {
    private Nodo raiz;
    private Nodo raizSimplificada;

    public ArbolExpresion(Nodo raiz) {
        this.raiz = raiz;
    }

    public ArbolExpresion() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public void simplificar(SimplificadorOperaciones simplificador, String nombreOperacion) {
        this.raiz = simplificador.simplificar(this.raiz, nombreOperacion);
    }

    public Set<Character> evaluar() {
        return raiz.evaluar();
    }

    public String mostrarContenido() {
        return raiz.mostrarContenido();
    }

    public void construir(Nodo nodoRaiz) {
        this.raiz = nodoRaiz;
    }
    
    public void setRaizSimplificada(Nodo raizSimplificada) {
        this.raizSimplificada = raizSimplificada;
    }

    public Nodo getRaizSimplificada() {
        return raizSimplificada;
    }

    /**
     * Método para obtener todos los nodos de tipo NodoConjunto en el árbol, sin duplicados.
     * 
     * @return Un conjunto de nodos de tipo NodoConjunto.
     */
    public Set<NodoConjunto> obtenerNodosConjunto() {
        Set<NodoConjunto> conjuntos = new HashSet<>();
        agregarNodosConjunto(raiz, conjuntos);
        return conjuntos;
    }

    /**
     * Método recursivo auxiliar para agregar nodos de tipo NodoConjunto al conjunto.
     * 
     * @param nodo El nodo actual en el árbol.
     * @param conjuntos El conjunto que almacena nodos de tipo NodoConjunto.
     */
    private void agregarNodosConjunto(Nodo nodo, Set<NodoConjunto> conjuntos) {
        if (nodo == null) {
            return;
        }

        if (nodo instanceof NodoConjunto) {
            conjuntos.add((NodoConjunto) nodo);
        } else if (nodo instanceof NodoBinario) {
            NodoBinario nodoBinario = (NodoBinario) nodo;
            agregarNodosConjunto(nodoBinario.getIzquierdo(), conjuntos);
            agregarNodosConjunto(nodoBinario.getDerecho(), conjuntos);
        } else if (nodo instanceof NodoUnario) {
            NodoUnario nodoUnario = (NodoUnario) nodo;
            agregarNodosConjunto(nodoUnario.getOperand(), conjuntos);
        }
    }
}
