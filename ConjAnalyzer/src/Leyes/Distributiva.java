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
 */
public class Distributiva implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        if (!(nodo instanceof NodoBinario)) {
            return false;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        Nodo derecho = operacion.getDerecho();

        return esOperadorDistribuible(operacion.getOperador(), derecho);
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        if (!(nodo instanceof NodoBinario)) {
            return nodo;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        Nodo derecho = operacion.getDerecho();

        if (esOperadorDistribuible(operacion.getOperador(), derecho)) {
            leyesAplicadas.add("propiedad distributiva");
            return aplicarLeyDistributiva(operacion);
        }

        return operacion;
    }

    private boolean esOperadorDistribuible(String operador, Nodo derecho) {
        if (!(derecho instanceof NodoBinario)) {
            return false;
        }

        NodoBinario derechoBinario = (NodoBinario) derecho;
        return (operador.equals("U") && derechoBinario.getOperador().equals("&")) ||
               (operador.equals("&") && derechoBinario.getOperador().equals("U"));
    }

    private Nodo aplicarLeyDistributiva(NodoBinario operacion) {
        String operador = operacion.getOperador();
        Nodo izquierdo = operacion.getIzquierdo();
        NodoBinario derechoOperacion = (NodoBinario) operacion.getDerecho();

        if (operador.equals("U")) {
            // A ∪ (B ∩ C) = (A ∪ B) ∩ (A ∪ C)
            return new NodoBinario("&", 
                    new NodoBinario("U", izquierdo, derechoOperacion.getIzquierdo()), 
                    new NodoBinario("U", izquierdo, derechoOperacion.getDerecho()));
        } else if (operador.equals("&")) {
            // A ∩ (B ∪ C) = (A ∩ B) ∪ (A ∩ C)
            return new NodoBinario("U", 
                    new NodoBinario("&", izquierdo, derechoOperacion.getIzquierdo()), 
                    new NodoBinario("&", izquierdo, derechoOperacion.getDerecho()));
        }

        return operacion;
    }
}
