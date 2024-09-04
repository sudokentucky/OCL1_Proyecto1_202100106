package Arbol;

import Conjuntos.GestorOperaciones;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Set;
import math.geom2d.Point2D;

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
    public Area dibujar(Graphics2D g2d, Point2D centro, double radio) {
        Area areaResultado = new Area();

        if (operador.equals("^")) {  // Operador de complemento
            // Dibujar el nodo operando y obtener su área
            Area areaOperand = operand.dibujar(g2d, centro, radio);

            // Crear un área que represente el universo basado en el radio
            Rectangle2D universoRect = new Rectangle2D.Double(centro.getX() - radio * 2, centro.getY() - radio * 1.5, 4 * radio, 3 * radio);
            Area areaUniverso = new Area(universoRect);

            // Calcular el complemento recortado al área del universo
            areaUniverso.subtract(areaOperand);  // El complemento dentro del universo
            g2d.setColor(new Color(255, 100, 100, 100));  // Color rojo claro con transparencia para el complemento
            g2d.fill(areaUniverso);  // Dibujar el complemento ajustado al universo

            areaResultado = areaUniverso;
        } else {
            // Otros operadores unarios, si existen
        }

        return areaResultado;
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

    public Nodo getOperand() {
        return operand;
    }
}
