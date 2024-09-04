package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import java.util.List;

/**
 *
 * @author Keneth Lopez
 */
public class Absorcion implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        if (!(nodo instanceof NodoBinario)) {
            return false;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        return (operacion.getOperador().equals("U") && (esPropiedadDeAbsorcion(operacion.getIzquierdo(), operacion.getDerecho()) || esPropiedadDeAbsorcion(operacion.getDerecho(), operacion.getIzquierdo()))) ||
               (operacion.getOperador().equals("&") && (esPropiedadDeAbsorcion(operacion.getIzquierdo(), operacion.getDerecho()) || esPropiedadDeAbsorcion(operacion.getDerecho(), operacion.getIzquierdo())));
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

        if (esPropiedadDeAbsorcion(izquierdo, derecho)) {
            leyesAplicadas.add("propiedad de absorción");
            return izquierdo;
        } else if (esPropiedadDeAbsorcion(derecho, izquierdo)) {
            leyesAplicadas.add("propiedad de absorción");
            return derecho;
        }

        return operacion;
    }

    private boolean esPropiedadDeAbsorcion(Nodo posibleAbsorbedor, Nodo posibleAbsorcion) {
        if (posibleAbsorcion instanceof NodoBinario) {
            NodoBinario nodoBinario = (NodoBinario) posibleAbsorcion;
            return (nodoBinario.getOperador().equals("&") && (nodoBinario.getIzquierdo().equals(posibleAbsorbedor) || nodoBinario.getDerecho().equals(posibleAbsorbedor))) ||
                   (nodoBinario.getOperador().equals("U") && (nodoBinario.getIzquierdo().equals(posibleAbsorbedor) || nodoBinario.getDerecho().equals(posibleAbsorbedor)));
        }
        return false;
    }
}
