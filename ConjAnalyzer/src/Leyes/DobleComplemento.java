package Leyes;

import Arbol.Nodo;
import Arbol.NodoUnario;
import java.util.List;

/**
 * Implementación de la ley del doble complemento.
 * Esta ley establece que el complemento de un complemento es el conjunto original:
 * ^(^A) = A
 */
public class DobleComplemento implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        return esDobleComplemento(nodo);
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        if (!esDobleComplemento(nodo)) {
            return nodo;
        }

        NodoUnario operacion = (NodoUnario) nodo;
        NodoUnario operando = (NodoUnario) operacion.getOperand();

        Nodo resultado = operando.getOperand();
        leyesAplicadas.add("ley del doble complemento");
        resultado.setSimplificado(true); // Marcar como simplificado
        return resultado;
    }

    /**
     * Verifica si un nodo es un doble complemento.
     * Un nodo es un doble complemento si es un NodoUnario con operador '^' y
     * su operando es también un NodoUnario con operador '^'.
     *
     * @param nodo El nodo a verificar.
     * @return true si el nodo es un doble complemento, false en caso contrario.
     */
    private boolean esDobleComplemento(Nodo nodo) {
        if (!(nodo instanceof NodoUnario)) {
            return false;
        }

        NodoUnario operacion = (NodoUnario) nodo;
        Nodo operando = operacion.getOperand();

        return operacion.getOperador().equals("^")
                && operando instanceof NodoUnario
                && ((NodoUnario) operando).getOperador().equals("^");
    }
}
