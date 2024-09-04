package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import java.util.List;

/**
 * Implementación de la ley distributiva.
 * La ley distributiva establece que:
 * - A ∪ (B ∩ C) = (A ∪ B) ∩ (A ∪ C)
 * - A ∩ (B ∪ C) = (A ∩ B) ∪ (A ∩ C)
 * 
 * Esta clase verifica si se puede aplicar la ley distributiva a un nodo y la aplica si es posible.
 * 
 */
public class Distributiva implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        if (!(nodo instanceof NodoBinario)) {
            return false;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        Nodo derecho = operacion.getDerecho();
        return (operacion.getOperador().equals("U") && derecho instanceof NodoBinario && ((NodoBinario) derecho).getOperador().equals("&")) ||
               (operacion.getOperador().equals("&") && derecho instanceof NodoBinario && ((NodoBinario) derecho).getOperador().equals("U"));
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

        if (operacion.getOperador().equals("U") && derecho instanceof NodoBinario) {
            NodoBinario derechoOperacion = (NodoBinario) derecho;
            leyesAplicadas.add("propiedad distributiva");

            // Aplicar la ley distributiva: A ∪ (B ∩ C) = (A ∪ B) ∩ (A ∪ C)
            return new NodoBinario("&", 
                    new NodoBinario("U", izquierdo, derechoOperacion.getIzquierdo()), 
                    new NodoBinario("U", izquierdo, derechoOperacion.getDerecho()));
        }

        if (operacion.getOperador().equals("&") && derecho instanceof NodoBinario) {
            NodoBinario derechoOperacion = (NodoBinario) derecho;
            leyesAplicadas.add("propiedad distributiva");

            // Aplicar la ley distributiva: A ∩ (B ∪ C) = (A ∩ B) ∪ (A ∩ C)
            return new NodoBinario("U", 
                    new NodoBinario("&", izquierdo, derechoOperacion.getIzquierdo()), 
                    new NodoBinario("&", izquierdo, derechoOperacion.getDerecho()));
        }

        return operacion;
    }
}
