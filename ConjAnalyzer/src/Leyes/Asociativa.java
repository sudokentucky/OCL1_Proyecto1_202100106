package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import java.util.List;

/**
 * Clase para aplicar la ley asociativa en nodos binarios.
 * @author Keneth Lopez
 */
public class Asociativa implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        if (!(nodo instanceof NodoBinario)) {
            return false;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        String operador = operacion.getOperador();

        return (operador.equals("U") && esOperadorAsociativo(operacion, "U")) || 
               (operador.equals("&") && esOperadorAsociativo(operacion, "&"));
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        if (!(nodo instanceof NodoBinario)) {
            return nodo;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        String operador = operacion.getOperador();

        if (operador.equals("U") && esOperadorAsociativo(operacion, "U")) {
            leyesAplicadas.add("propiedad asociativa");
            return aplicarLeyAsociativa(operacion, "U");
        }

        if (operador.equals("&") && esOperadorAsociativo(operacion, "&")) {
            leyesAplicadas.add("propiedad asociativa");
            return aplicarLeyAsociativa(operacion, "&");
        }

        return operacion;
    }

    private Nodo aplicarLeyAsociativa(NodoBinario operacion, String operador) {
        Nodo izquierdo = operacion.getIzquierdo();
        NodoBinario derecho = (NodoBinario) operacion.getDerecho();
        
        return new NodoBinario(operador, new NodoBinario(operador, izquierdo, derecho.getIzquierdo()), derecho.getDerecho());
    }

    private boolean esOperadorAsociativo(NodoBinario operacion, String operadorEsperado) {
        Nodo derecho = operacion.getDerecho();
        return derecho instanceof NodoBinario && ((NodoBinario) derecho).getOperador().equals(operadorEsperado);
    }
}
