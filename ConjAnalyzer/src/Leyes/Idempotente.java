package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import java.util.List;

/**
 * Implementación de la ley idempotente.
 * Esta ley establece que un conjunto unido o intersectado consigo mismo es el conjunto original:
 * A ∪ A = A
 * A ∩ A = A
 */
public class Idempotente implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        return esOperacionIdempotente(nodo);
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        if (!esOperacionIdempotente(nodo)) {
            return nodo;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        Nodo izquierdo = operacion.getIzquierdo();
        
        leyesAplicadas.add("propiedad idempotente");
        return izquierdo;
    }

    /**
     * Verifica si una operación binaria es idempotente.
     * Una operación es idempotente si es un NodoBinario con operador 'U' o '&'
     * y ambos operandos son iguales.
     *
     * @param nodo El nodo a verificar.
     * @return true si la operación es idempotente, false en caso contrario.
     */
    private boolean esOperacionIdempotente(Nodo nodo) {
        if (!(nodo instanceof NodoBinario)) {
            return false;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        Nodo izquierdo = operacion.getIzquierdo();
        Nodo derecho = operacion.getDerecho();
        
        return (operacion.getOperador().equals("U") || operacion.getOperador().equals("&")) &&
                izquierdo.equals(derecho);
    }
}
