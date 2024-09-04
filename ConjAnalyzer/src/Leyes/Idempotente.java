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
        if (!(nodo instanceof NodoBinario)) {
            return false;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        return (operacion.getOperador().equals("U") || operacion.getOperador().equals("&")) && 
               operacion.getIzquierdo().equals(operacion.getDerecho());
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        // Verificar que el nodo es de tipo NodoBinario
        if (!(nodo instanceof NodoBinario)) {
            return nodo;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        Nodo izquierdo = operacion.getIzquierdo();
        Nodo derecho = operacion.getDerecho();

        // Aplicar la ley idempotente: A ∪ A = A y A ∩ A = A
        if (izquierdo.equals(derecho)) {
            leyesAplicadas.add("propiedad idempotente");
            return izquierdo;
        }

        return operacion;
    }
}
