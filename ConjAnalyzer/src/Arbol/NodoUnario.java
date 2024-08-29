/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Arbol;
import Conjuntos.GestorOperaciones;
import java.util.List;
import java.util.Set;
/**
 *
 * @author Keneth Lopez
 */
public class NodoUnario extends Nodo { 
    private String operador;
    private Nodo operand;

    public NodoUnario(String operador, Nodo operand) {
        this.operador = operador;
        this.operand = operand;
    }

    @Override
    public Set<Character> evaluar() {
        Set<Character> resultadoOperand = operand.evaluar();

        switch (operador) {
            case "^":
                return GestorOperaciones.complemento(resultadoOperand);
            default:
                throw new IllegalArgumentException("Operador unario desconocido: " + operador);
        }
    }


    @Override
    public String mostrarContenido() {
        return operador + operand.mostrarContenido();
    }

    @Override
    public String toString() {
        return mostrarContenido();
    }

    public String getOperador() {
        return operador;
    }

    public Nodo getOperand() {
        return operand;
    }
}