package Interfaz;

import Arbol.ArbolExpresion;
import Arbol.Nodo;
import math.geom2d.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import java.util.HashSet;
import java.util.Set;

public class VennDiagramPanel extends JPanel {

    private ArbolExpresion arbolExpresion;
    private double universeWidth = 400;
    private double universeHeight = 300;
    private double universeX;
    private double universeY;

    public VennDiagramPanel(ArbolExpresion arbolExpresion) {
        this.arbolExpresion = arbolExpresion;
        System.out.println("VennDiagramPanel inicializado con un árbol de expresión.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Verificar si hay un árbol de expresión y si tiene una raíz
        if (arbolExpresion != null && arbolExpresion.getRaizSimplificada() != null) {
            // Recopilar todos los nombres de conjuntos únicos
            Set<String> conjuntos = new HashSet<>();
            arbolExpresion.getRaizSimplificada().recopilarConjuntos(conjuntos);

            // Calcular la posición del universo centrado en el panel
            universeX = (getWidth() - universeWidth) / 2;
            universeY = (getHeight() - universeHeight) / 2;

            // Dibujar el contorno del universo
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect((int) universeX, (int) universeY, (int) universeWidth, (int) universeHeight);
            g2d.setFont(new Font("Arial", Font.BOLD, 16));
            g2d.drawString("U", (int) universeX - 20, (int) universeY + 20);

            Nodo raizSimplificada = arbolExpresion.getRaizSimplificada();
            Point2D centroUniverso = new Point2D(universeX + universeWidth / 2, universeY + universeHeight / 2);

            // Calcular el factor de escala basado en el tamaño del universo
            double escala = calcularFactorEscala(universeWidth, universeHeight, conjuntos.size());

            // Dibujar los conjuntos utilizando la lógica de los nodos
            dibujarConjuntos(g2d, raizSimplificada, centroUniverso, universeWidth / 3 * escala);
        } else {
            System.out.println("No hay árbol de expresión o la raíz es nula, no se dibuja nada.");
        }
    }

    private double calcularFactorEscala(double width, double height, int numConjuntos) {
        double radioMaximo = Math.min(width, height) / (2 * numConjuntos);
        return radioMaximo / (width / 3);
    }

    private void dibujarConjuntos(Graphics2D g2d, Nodo nodo, Point2D centro, double radio) {
        // Llama al método de dibujo del nodo, que manejará su propia lógica
        Area area = nodo.dibujar(g2d, centro, radio);

        if (area != null && !area.isEmpty()) {
            dibujarArea(g2d, area, nodo.mostrarContenido());
        }
    }

    private void dibujarArea(Graphics2D g2d, Area area, String etiqueta) {
        g2d.setColor(new Color(100, 100, 255, 100)); // Color azul con transparencia para áreas resultantes
        g2d.fill(area);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.draw(area);

        // Añadir etiquetas para identificar los conjuntos
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        Rectangle2D bounds = area.getBounds2D();
        g2d.drawString(etiqueta, (int) bounds.getCenterX(), (int) (bounds.getMaxY() + 20));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 400);
    }

    public void setArbolExpresion(ArbolExpresion arbolExpresion) {
        this.arbolExpresion = arbolExpresion;
        System.out.println("Árbol de expresión actualizado en VennDiagramPanel.");
        repaint();
    }

    public void updateDiagram(ArbolExpresion arbol) {
        this.setArbolExpresion(arbol);
    }
}
