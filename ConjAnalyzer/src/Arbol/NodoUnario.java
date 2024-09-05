package Arbol;
/**
 *
 * @author Keneth Lopez
 */
import Conjuntos.GestorOperaciones;
import java.util.Set;

public class NodoUnario extends Nodo {
    private String operador;
    private Nodo operand;


    public NodoUnario(String operador, Nodo operand) {
        if (operador == null || operand == null) {
            throw new IllegalArgumentException("Operador o operando no pueden ser nulos");
        }
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
    public void recopilarConjuntos(Set<String> conjuntos) {
        operand.recopilarConjuntos(conjuntos);
    }

    @Override
    public String toString() {
        return mostrarContenido();
    }

    public String getOperador() {
        return operador;
    }
    
    public void setOperador(String operador) {
        this.operador = operador;
    }

    
    public void setOperand(Nodo operand) {
        this.operand = operand;
    }
    
    @Override
    public int contarNodos() {
        return 1 + operand.contarNodos();
    }
    @Override
    public int calcularProfundidad() {
        // Calcular la profundidad del nodo unario como la de su único hijo más uno
        return 1 + operand.calcularProfundidad();
    }

    public Nodo getOperand() {
        return operand;
    }
}
