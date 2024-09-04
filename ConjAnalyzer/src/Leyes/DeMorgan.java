package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import Arbol.NodoUnario;
import java.util.List;

/**
 * Implementación de la ley de De Morgan.
 * Esta clase verifica si se puede aplicar la ley de De Morgan a un nodo y la aplica si es posible.
 * La ley de De Morgan establece que:
 * - El complemento de una unión es igual a la intersección de los complementos: ^(A ∪ B) = ^A ∩^ B
 * - El complemento de una intersección es igual a la unión de los complementos: ^(A ∩ B) = ^A ∪ ^B
 
 */
public class DeMorgan implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        // Comprobar si el nodo es de tipo NodoUnario
        if (!(nodo instanceof NodoUnario)) {
            System.out.println("DeMorgan.esAplicable: El nodo no es de tipo NodoUnario. No aplicable.");
            return false;
        }

        NodoUnario operacion = (NodoUnario) nodo;
        Nodo operando = operacion.getOperand();

        // Comprobar si el operador es '^' y el operando es NodoBinario
        if (operacion.getOperador().equals("^") && operando instanceof NodoBinario) {
            NodoBinario operandoBinario = (NodoBinario) operando;
            boolean aplicable = operandoBinario.getOperador().equals("U") || operandoBinario.getOperador().equals("&");
            if (aplicable) {
                System.out.println("DeMorgan.esAplicable: La ley de DeMorgan es aplicable para el nodo: " + operacion.mostrarContenido());
                return true;
            }
        }

        System.out.println("DeMorgan.esAplicable: El operando no es de tipo NodoBinario o el operador no es '^'. No aplicable.");
        return false;
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        // Comprobar si el nodo es de tipo NodoUnario
        if (!(nodo instanceof NodoUnario)) {
            System.out.println("DeMorgan.aplicar: El nodo no es de tipo NodoUnario. No se aplica la ley de DeMorgan.");
            return nodo;
        }

        NodoUnario operacion = (NodoUnario) nodo;
        Nodo operando = operacion.getOperand();

        // Verificar si el operando es un NodoBinario
        if (operando instanceof NodoBinario) {
            NodoBinario operandoBinario = (NodoBinario) operando;
            String nuevoOperador = operandoBinario.getOperador().equals("U") ? "&" : "U";
            leyesAplicadas.add("ley de DeMorgan");

            // Aplicar la ley de De Morgan: cambiar operadores y aplicar complementos
            System.out.println("DeMorgan.aplicar: Aplicando ley de DeMorgan:");
            System.out.println("DeMorgan.aplicar: Nodo original: " + operacion.mostrarContenido());
            System.out.println("DeMorgan.aplicar: Operando Binario: " + operandoBinario.mostrarContenido());
            System.out.println("DeMorgan.aplicar: Nuevo operador después de aplicar ley: " + nuevoOperador);

            Nodo resultado = new NodoBinario(nuevoOperador,
                    new NodoUnario("^", operandoBinario.getIzquierdo()),
                    new NodoUnario("^", operandoBinario.getDerecho()));

            System.out.println("DeMorgan.aplicar: Nodo resultado después de aplicar ley: " + resultado.mostrarContenido());

            // Devuelve el resultado transformado para permitir más simplificaciones
            return resultado;
        }

        // Si el operando no es un NodoBinario, retornar la operación sin cambios
        System.out.println("DeMorgan.aplicar: El operando no es de tipo NodoBinario. No se aplica la ley de DeMorgan.");
        return operacion;
    }
}
