package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import java.util.List;

/**
 *
 * @author Keneth Lopez
 */
public class Conmutativa implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        if (!(nodo instanceof NodoBinario)) {
            return false;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        return operacion.getOperador().equals("U") || operacion.getOperador().equals("&");
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

        // Aplicar la propiedad conmutativa si es aplicable
        if (izquierdo.toString().compareTo(derecho.toString()) > 0) {
            leyesAplicadas.add("propiedad conmutativa");
            return new NodoBinario(operacion.getOperador(), derecho, izquierdo);
        }

        return operacion;
    }
}
