/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;

import java.util.List;
import java.util.Set;
import java.util.Stack;
import Conjuntos.ConjuntoManager;
/**
 *
 * @author Keneth Lopez
 */
public class ArbolBuilder {
    private ConjuntoManager conjuntoManager;
    private SimplificadorOperaciones simplificador;

    public ArbolBuilder(ConjuntoManager conjuntoManager) {
        this.conjuntoManager = conjuntoManager;
        this.simplificador = new SimplificadorOperaciones(conjuntoManager);
    }

    public Nodo construirArbol(List<String> tokens) {
        Stack<Nodo> stack = new Stack<>();

        // Recorrer los tokens en orden inverso para la notación prefija
        for (int i = tokens.size() - 1; i >= 0; i--) {
            String token = tokens.get(i);

            switch (token) {
                case "U":
                    // Sacar los dos operandos para la unión
                    Nodo leftUnion = stack.pop();
                    Nodo rightUnion = stack.pop();
                    stack.push(new NodoOperacion("U", leftUnion, rightUnion));
                    break;
                case "&":
                    // Sacar los dos operandos para la intersección
                    Nodo leftInter = stack.pop();
                    Nodo rightInter = stack.pop();
                    stack.push(new NodoOperacion("&", leftInter, rightInter));
                    break;
                case "-":
                    // Sacar los dos operandos para la diferencia
                    Nodo leftDiff = stack.pop();
                    Nodo rightDiff = stack.pop();
                    stack.push(new NodoOperacion("-", leftDiff, rightDiff));
                    break;
                case "^":
                    // Sacar el único operando para el complemento
                    Nodo operandCompl = stack.pop();
                    stack.push(new NodoOperacion("^", operandCompl, null));
                    break;
                default:
                    // Asumir que es un identificador de conjunto y apilarlo
                    stack.push(new NodoConjunto(token, conjuntoManager));
                    break;
            }
        }

        // El nodo final en la pila es la raíz del árbol
        Nodo root = stack.pop();

        // Simplificar el árbol antes de devolverlo
        Nodo simplificado = simplificador.simplificar(root, "OperacionConstruida");

        return simplificado;
    }

    public Set<Character> evaluar(Nodo arbol) {
        return arbol.evaluar();
    }

    public String mostrarContenido(Nodo arbol) {
        return arbol.mostrarContenido();
    }
}