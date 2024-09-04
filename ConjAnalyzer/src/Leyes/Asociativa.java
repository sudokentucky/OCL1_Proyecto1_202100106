package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import java.util.List;

/**
 *
 * @author Keneth Lopez
 */
public class Asociativa implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        if (!(nodo instanceof NodoBinario)) {
            return false;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        return (operacion.getOperador().equals("U") && esAsociativaUnion(operacion)) || 
               (operacion.getOperador().equals("&") && esAsociativaInterseccion(operacion));
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

        if (esAsociativaUnion(operacion)) {
            NodoBinario derechoOperacion = (NodoBinario) derecho;
            leyesAplicadas.add("propiedad asociativa");
            return new NodoBinario("U", new NodoBinario("U", izquierdo, derechoOperacion.getIzquierdo()), derechoOperacion.getDerecho());
        }

        if (esAsociativaInterseccion(operacion)) {
            NodoBinario derechoOperacion = (NodoBinario) derecho;
            leyesAplicadas.add("propiedad asociativa");
            return new NodoBinario("&", new NodoBinario("&", izquierdo, derechoOperacion.getIzquierdo()), derechoOperacion.getDerecho());
        }

        return operacion;
    }

    private boolean esAsociativaUnion(NodoBinario operacion) {
        Nodo derecho = operacion.getDerecho();
        return derecho instanceof NodoBinario && ((NodoBinario) derecho).getOperador().equals("U");
    }

    private boolean esAsociativaInterseccion(NodoBinario operacion) {
        Nodo derecho = operacion.getDerecho();
        return derecho instanceof NodoBinario && ((NodoBinario) derecho).getOperador().equals("&");
    }
}
