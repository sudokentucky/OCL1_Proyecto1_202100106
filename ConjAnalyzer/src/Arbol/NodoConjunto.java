package Arbol;

import Conjuntos.ConjuntoManager;
import java.awt.Font;
import java.awt.Graphics2D;
import math.geom2d.conic.Circle2D;
import java.awt.geom.Area;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;
import math.geom2d.Point2D;

public class NodoConjunto extends Nodo {
    private String nombreConjunto;
    private ConjuntoManager conjuntoManager;
    private Area areaCache; 

    public NodoConjunto(String nombreConjunto, ConjuntoManager conjuntoManager) {
        this.nombreConjunto = nombreConjunto;
        this.conjuntoManager = conjuntoManager;
        this.areaCache = null; // Inicialmente nula, para calcularla después si es necesario
    }

    @Override
    public Set<Character> evaluar() {
        return conjuntoManager.obtenerConjunto(nombreConjunto);
    }

    @Override
    public String mostrarContenido() {
        return nombreConjunto;
    }
    @Override
    public String toString() {
        return nombreConjunto;
    }

    public String getNombreConjunto() {
        return nombreConjunto;
    }
    
    @Override
    public Area dibujar(Graphics2D g2d, Point2D centro, double radio) {
        // Crear un círculo con el radio escalado
        Ellipse2D.Double conjuntoCirculo = new Ellipse2D.Double(
            centro.getX() - radio, centro.getY() - radio, 2 * radio, 2 * radio);
        Area areaConjunto = new Area(conjuntoCirculo);

        // Dibujar el círculo en el gráfico
        g2d.draw(conjuntoCirculo);

        // Dibujar la etiqueta del conjunto
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString(nombreConjunto, (int) centro.getX() - 5, (int) centro.getY() + 5);

        return areaConjunto;
    }

    @Override
    public void recopilarConjuntos(Set<String> conjuntos) {
        conjuntos.add(nombreConjunto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodoConjunto that = (NodoConjunto) obj;
        return nombreConjunto.equals(that.nombreConjunto);
    }

    @Override
    public int hashCode() {
        return nombreConjunto.hashCode();
    }
}
