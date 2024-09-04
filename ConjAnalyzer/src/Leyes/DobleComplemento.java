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
        if (!(nodo instanceof NodoUnario)) {
            return false;
        }

        NodoUnario operacion = (NodoUnario) nodo;
        Nodo operando = operacion.getOperand();
        return operacion.getOperador().equals("^") && operando instanceof NodoUnario && ((NodoUnario) operando).getOperador().equals("^");
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        // Verificar que el nodo es de tipo NodoUnario
        if (!(nodo instanceof NodoUnario)) {
            return nodo;
        }

        NodoUnario operacion = (NodoUnario) nodo;
        Nodo operando = operacion.getOperand();

        // Aplicar la ley del doble complemento: ¬(¬A) = A
        if (operando instanceof NodoUnario && ((NodoUnario) operando).getOperador().equals("^")) {
            leyesAplicadas.add("ley del doble complemento");
            return ((NodoUnario) operando).getOperand();
        }

        return operacion;
    }
}
