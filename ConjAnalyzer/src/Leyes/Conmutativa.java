package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import java.util.List;

/**
 * Clase para aplicar la ley conmutativa en nodos binarios.
 * @author Keneth Lopez
 */
public class Conmutativa implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        return nodo instanceof NodoBinario && esOperadorConmutativo(((NodoBinario) nodo).getOperador());
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        if (!(nodo instanceof NodoBinario)) {
            return nodo;
        }

        NodoBinario operacion = (NodoBinario) nodo;
        Nodo izquierdo = operacion.getIzquierdo();
        Nodo derecho = operacion.getDerecho();

        // Verificar que los nodos izquierdo y derecho no sean nulos
        if (izquierdo == null || derecho == null) {
            return operacion;  // No se puede aplicar la conmutativa si uno de los nodos es nulo
        }

        // Aplicar la propiedad conmutativa si es aplicable
        if (debeAplicarConmutativa(izquierdo, derecho)) {
            leyesAplicadas.add("propiedad conmutativa");
            return new NodoBinario(operacion.getOperador(), derecho, izquierdo);
        }

        return operacion;
    }

    private boolean esOperadorConmutativo(String operador) {
        return "U".equals(operador) || "&".equals(operador);
    }

    private boolean debeAplicarConmutativa(Nodo izquierdo, Nodo derecho) {
        // Se utiliza compareTo para decidir si se aplica la propiedad conmutativa
        return izquierdo.toString().compareTo(derecho.toString()) > 0;
    }
}
