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
        System.out.println("Conjunto A antes de la unión: " + conjuntoA);
        System.out.println("Conjunto B antes de la unión: " + conjuntoB);
        Set<Character> resultado = new HashSet<>(conjuntoA);
        resultado.addAll(conjuntoB);
        System.out.println("Resultado después de la unión: " + resultado);
        return resultado;
    }

    // Intersección de dos conjuntos
    public static Set<Character> interseccion(Set<Character> conjuntoA, Set<Character> conjuntoB) {
        System.out.println("Conjunto A antes de la intersección: " + conjuntoA);
        System.out.println("Conjunto B antes de la intersección: " + conjuntoB);
        Set<Character> resultado = new HashSet<>(conjuntoA);
        resultado.retainAll(conjuntoB);
        System.out.println("Resultado después de la intersección: " + resultado);
        return resultado;
    }

    // Diferencia de dos conjuntos (A - B)
    public static Set<Character> diferencia(Set<Character> conjuntoA, Set<Character> conjuntoB) {
        System.out.println("Conjunto A antes de la diferencia: " + conjuntoA);
        System.out.println("Conjunto B antes de la diferencia: " + conjuntoB);
        Set<Character> resultado = new HashSet<>(conjuntoA);
        resultado.removeAll(conjuntoB);
        System.out.println("Resultado después de la diferencia: " + resultado);
        return resultado;
    }

    // Complemento de un conjunto respecto al conjunto universal
    public static Set<Character> complemento(Set<Character> conjunto) {
        System.out.println("Conjunto antes del complemento: " + conjunto);
        Set<Character> conjuntoUniversal = generarConjuntoUniversal();
        System.out.println("Conjunto Universal antes del complemento: " + conjuntoUniversal);
        conjuntoUniversal.removeAll(conjunto);
        System.out.println("Resultado después del complemento: " + conjuntoUniversal);
        return conjuntoUniversal;
    }

    // Genera el conjunto universal (caracteres ASCII del 33 al 126)
    private static Set<Character> generarConjuntoUniversal() {
        Set<Character> conjuntoUniversal = new HashSet<>();
        for (char c = '!'; c <= '~'; c++) {
            conjuntoUniversal.add(c);
        }
        System.out.println("Conjunto Universal generado: " + conjuntoUniversal);
        return conjuntoUniversal;
    }
}
