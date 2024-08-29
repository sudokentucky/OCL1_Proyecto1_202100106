/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conjuntos;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Keneth Lopez
 */
public class GestorOperaciones {

    // Unión de dos conjuntos
    public static Set<Character> union(Set<Character> conjuntoA, Set<Character> conjuntoB) {
        Set<Character> resultado = new HashSet<>(conjuntoA);
        resultado.addAll(conjuntoB);
        return resultado;
    }

    // Intersección de dos conjuntos
    public static Set<Character> interseccion(Set<Character> conjuntoA, Set<Character> conjuntoB) {
        Set<Character> resultado = new HashSet<>(conjuntoA);
        resultado.retainAll(conjuntoB);
        return resultado;
    }

    // Diferencia de dos conjuntos (A - B)
    public static Set<Character> diferencia(Set<Character> conjuntoA, Set<Character> conjuntoB) {;
        Set<Character> resultado = new HashSet<>(conjuntoA);
        resultado.removeAll(conjuntoB);
        return resultado;
    }

    // Complemento de un conjunto respecto al conjunto universal
    public static Set<Character> complemento(Set<Character> conjunto) {
        Set<Character> conjuntoUniversal = generarConjuntoUniversal();
        conjuntoUniversal.removeAll(conjunto);
        return conjuntoUniversal;
    }

    // Genera el conjunto universal (caracteres ASCII del 33 al 126)
    private static Set<Character> generarConjuntoUniversal() {
        Set<Character> conjuntoUniversal = new HashSet<>();
        for (char c = '!'; c <= '~'; c++) {
            conjuntoUniversal.add(c);
        }
        return conjuntoUniversal;
    }
}
