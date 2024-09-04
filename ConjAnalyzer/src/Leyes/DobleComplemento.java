package Leyes;

import Arbol.Nodo;
import Arbol.NodoBinario;
import Arbol.NodoUnario;
import java.util.List;

public class DobleComplemento implements Ley {

    @Override
    public boolean esAplicable(Nodo nodo) {
        // Verificar si el nodo ya ha sido simplificado
        if (nodo.isSimplificado()) {
            System.out.println("DobleComplemento.esAplicable: Nodo " + nodo.mostrarContenido() + " ya ha sido simplificado. No es aplicable.");
            return false;
        }

        if (nodo instanceof NodoUnario) {
            NodoUnario operacion = (NodoUnario) nodo;
            Nodo operando = operacion.getOperand();
            boolean aplicable = operacion.getOperador().equals("^") 
                                && operando instanceof NodoUnario 
                                && ((NodoUnario) operando).getOperador().equals("^");
            if (aplicable) {
                System.out.println("DobleComplemento.esAplicable: Nodo " + nodo.mostrarContenido() + " es aplicable? " + aplicable);
                return true;
            }

            // Evaluar recursivamente el operando
            System.out.println("DobleComplemento.esAplicable: Evaluando recursivamente el operando " + operando.mostrarContenido());
            return esAplicable(operando);
        } else if (nodo instanceof NodoBinario) {
            NodoBinario operacion = (NodoBinario) nodo;
            System.out.println("DobleComplemento.esAplicable: Evaluando recursivamente los hijos del nodo binario " + nodo.mostrarContenido());
            boolean aplicableIzquierdo = esAplicable(operacion.getIzquierdo());
            boolean aplicableDerecho = esAplicable(operacion.getDerecho());
            return aplicableIzquierdo || aplicableDerecho;
        }

        System.out.println("DobleComplemento.esAplicable: Nodo " + nodo.mostrarContenido() + " no es aplicable.");
        return false;
    }

    @Override
    public Nodo aplicar(Nodo nodo, List<String> leyesAplicadas) {
        if (nodo.isSimplificado()) {
            System.out.println("DobleComplemento.aplicar: Nodo " + nodo.mostrarContenido() + " ya ha sido simplificado. No se aplica.");
            return nodo;
        }

        if (nodo instanceof NodoUnario) {
            NodoUnario operacion = (NodoUnario) nodo;
            Nodo operando = operacion.getOperand();

            if (operando instanceof NodoUnario && ((NodoUnario) operando).getOperador().equals("^")) {
                leyesAplicadas.add("ley del doble complemento");
                Nodo resultado = ((NodoUnario) operando).getOperand();
                System.out.println("DobleComplemento.aplicar: Aplicando la ley del doble complemento al nodo " + nodo.mostrarContenido() + ". Resultado: " + resultado.mostrarContenido());
                resultado.setSimplificado(true); // Marcar como simplificado
                return resultado;
            }
        } else if (nodo instanceof NodoBinario) {
            NodoBinario operacion = (NodoBinario) nodo;
            System.out.println("DobleComplemento.aplicar: Aplicando recursivamente la ley del doble complemento a los hijos del nodo binario " + nodo.mostrarContenido());

            Nodo izquierdoSimplificado = aplicar(operacion.getIzquierdo(), leyesAplicadas);
            Nodo derechoSimplificado = aplicar(operacion.getDerecho(), leyesAplicadas);

            Nodo resultado = new NodoBinario(operacion.getOperador(), izquierdoSimplificado, derechoSimplificado);
            resultado.setSimplificado(true); // Marcar como simplificado
            return resultado;
        }

        System.out.println("DobleComplemento.aplicar: El nodo " + nodo.mostrarContenido() + " no es aplicable para la ley del doble complemento.");
        return nodo;
    }
}
