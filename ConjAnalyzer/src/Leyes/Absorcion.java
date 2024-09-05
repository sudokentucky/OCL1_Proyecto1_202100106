package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import java.util.List;

/**
 * Clase para aplicar la ley de absorción en nodos binarios.
 * @author Keneth Lopez
 */
public class Absorcion implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        if (!(nodo instanceof NodoBinario)) {
            return false;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        String operador = operacion.getOperador();

        if (operador.equals("U") || operador.equals("&")) {
            return verificarPropiedadDeAbsorcion(operacion.getIzquierdo(), operacion.getDerecho()) ||
                   verificarPropiedadDeAbsorcion(operacion.getDerecho(), operacion.getIzquierdo());
        }

        return false;
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        if (nodo == null || leyesAplicadas == null) {
            throw new IllegalArgumentException("Nodo y leyesAplicadas no pueden ser nulos");
        }

        if (!(nodo instanceof NodoBinario)) {
            return nodo;
        }

        NodoBinario operacion = (NodoBinario) nodo;

        Nodo resultado = aplicarAbsorcion(operacion, leyesAplicadas);
        return resultado != null ? resultado : operacion;
    }

    private Nodo aplicarAbsorcion(NodoBinario operacion, List<String> leyesAplicadas) {
        Nodo izquierdo = operacion.getIzquierdo();
        Nodo derecho = operacion.getDerecho();

        if (verificarPropiedadDeAbsorcion(izquierdo, derecho)) {
            leyesAplicadas.add("propiedad de absorción");
            return izquierdo;
        } else if (verificarPropiedadDeAbsorcion(derecho, izquierdo)) {
            leyesAplicadas.add("propiedad de absorción");
            return derecho;
        }

        return null;
    }

    private boolean verificarPropiedadDeAbsorcion(Nodo posibleAbsorbedor, Nodo posibleAbsorcion) {
        if (!(posibleAbsorcion instanceof NodoBinario)) {
            return false;
        }

        NodoBinario nodoBinario = (NodoBinario) posibleAbsorcion;
        String operador = nodoBinario.getOperador();

        return (operador.equals("&") || operador.equals("U")) &&
               (nodoBinario.getIzquierdo().equals(posibleAbsorbedor) || nodoBinario.getDerecho().equals(posibleAbsorbedor));
    }
}
