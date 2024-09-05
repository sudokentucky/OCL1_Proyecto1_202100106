package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import Arbol.NodoUnario;
import java.util.List;

/**
 * Implementación de la ley de De Morgan.
 * Esta clase verifica si se puede aplicar la ley de De Morgan a un nodo y la aplica si es posible.
 * La ley de De Morgan establece que:
 * - El complemento de una unión es igual a la intersección de los complementos: ^(A ∪ B) = ^A ∩ ^B
 * - El complemento de una intersección es igual a la unión de los complementos: ^(A ∩ B) = ^A ∪ ^B
 */
public class DeMorgan implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        if (!(nodo instanceof NodoUnario)) {
            return false;
        }

        NodoUnario operacion = (NodoUnario) nodo;
        Nodo operando = operacion.getOperand();

        return esOperadorComplemento(operacion) && esOperandoBinarioConOperadorUniónOIntersección(operando);
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        if (!(nodo instanceof NodoUnario)) {
            return nodo;
        }

        NodoUnario operacion = (NodoUnario) nodo;
        Nodo operando = operacion.getOperand();

        if (operando instanceof NodoBinario) {
            NodoBinario operandoBinario = (NodoBinario) operando;
            String nuevoOperador = obtenerOperadorInvertido(operandoBinario.getOperador());

            // Aplicar la ley de De Morgan: cambiar operadores y aplicar complementos a los hijos
            Nodo nuevoIzquierdo = new NodoUnario("^", operandoBinario.getIzquierdo());
            Nodo nuevoDerecho = new NodoUnario("^", operandoBinario.getDerecho());
            Nodo resultado = new NodoBinario(nuevoOperador, nuevoIzquierdo, nuevoDerecho);

            leyesAplicadas.add("ley de DeMorgan");
            return resultado;
        }

        return operacion;
    }

    private boolean esOperadorComplemento(NodoUnario nodoUnario) {
        return "^".equals(nodoUnario.getOperador());
    }

    private boolean esOperandoBinarioConOperadorUniónOIntersección(Nodo operando) {
        if (operando instanceof NodoBinario) {
            NodoBinario operandoBinario = (NodoBinario) operando;
            String operador = operandoBinario.getOperador();
            return "U".equals(operador) || "&".equals(operador);
        }
        return false;
    }

    private String obtenerOperadorInvertido(String operador) {
        return "U".equals(operador) ? "&" : "U";
    }
}
