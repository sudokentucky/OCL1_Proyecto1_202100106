package Interfaz;

import Arbol.ArbolExpresion;
import Arbol.Nodo;
import Arbol.NodoConjunto;
import Arbol.NodoBinario;
import Arbol.NodoUnario;
import Conjuntos.ConjuntoManager;
import math.geom2d.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VennDiagramPanel extends JPanel {

    private ArbolExpresion arbolExpresion;
    private double universeWidth = 400;
    private double universeHeight = 300;
    private double universeX;
    private double universeY;
    private Map<String, Area> areasConjuntos;  // Mapa para almacenar áreas de conjuntos por nombre
    private ConjuntoManager conjuntoManager;  

    public VennDiagramPanel(ArbolExpresion arbolExpresion, ConjuntoManager conjuntoManager) {
        this.arbolExpresion = arbolExpresion;
        this.conjuntoManager = conjuntoManager;
        System.out.println("VennDiagramPanel inicializado con un árbol de expresión.");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        configurarGraficos(g2d);

        if (arbolExpresion != null && arbolExpresion.getRaizSimplificada() != null) {
            prepararUniverso(g2d);
            Nodo raizSimplificada = arbolExpresion.getRaizSimplificada();
            Point2D centroUniverso = new Point2D(universeX + universeWidth / 2, universeY + universeHeight / 2);
            double escala = calcularFactorEscala(universeWidth, universeHeight, obtenerNumConjuntos());
            int numConjuntos = obtenerNumConjuntos();
            Point2D[] posicionesConjuntos = calcularPosicionesConjuntos(centroUniverso, universeWidth / 3 * escala, numConjuntos);

            // Inicializar áreas de conjuntos
            areasConjuntos = new HashMap<>();
            Set<String> nombresConjuntos = new HashSet<>();
            raizSimplificada.recopilarConjuntos(nombresConjuntos);
            int i = 0;
            for (String nombreConjunto : nombresConjuntos) {
                NodoConjunto nodoConjunto = new NodoConjunto(nombreConjunto, conjuntoManager); // Proporcionar ConjuntoManager
                areasConjuntos.put(nombreConjunto, dibujarConjunto(g2d, nodoConjunto, posicionesConjuntos[i], universeWidth / 3 * escala));
                i++;
            }

            // Dibujar operaciones
            dibujarOperaciones(g2d, raizSimplificada, centroUniverso, universeWidth / 3 * escala);
            dibujarOperacion(g2d, raizSimplificada);
        } else {
            System.out.println("No hay árbol de expresión o la raíz es nula, no se dibuja nada.");
        }
    }

    private void configurarGraficos(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void prepararUniverso(Graphics2D g2d) {
        universeX = (getWidth() - universeWidth) / 2;
        universeY = (getHeight() - universeHeight) / 2;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect((int) universeX, (int) universeY, (int) universeWidth, (int) universeHeight);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("U", (int) universeX - 20, (int) universeY + 20);
        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle2D.Double(universeX, universeY, universeWidth, universeHeight));
    }

    private double calcularFactorEscala(double width, double height, int numConjuntos) {
        double radioMaximo = Math.min(width, height) / (2 * numConjuntos);
        return radioMaximo / (width / 3);
    }

    private int obtenerNumConjuntos() {
        Set<String> conjuntos = new HashSet<>();
        arbolExpresion.getRaizSimplificada().recopilarConjuntos(conjuntos);
        return conjuntos.size();
    }

    private Point2D[] calcularPosicionesConjuntos(Point2D centro, double radio, int numConjuntos) {
        Point2D[] posiciones = new Point2D[numConjuntos];
        double separation = radio * 0.9;  // Ajusta esta separación según sea necesario

        switch (numConjuntos) {
            case 1:
                posiciones[0] = new Point2D(centro.getX(), centro.getY());
                break;

            case 2:
                posiciones[0] = new Point2D(centro.getX() - separation / 1.6, centro.getY());
                posiciones[1] = new Point2D(centro.getX() + separation / 1.6, centro.getY());
                break;

            case 3:
                posiciones[0] = new Point2D(centro.getX() - separation / 1.6, centro.getY() - separation / 3);
                posiciones[1] = new Point2D(centro.getX() + separation / 1.6, centro.getY() - separation / 3);
                posiciones[2] = new Point2D(centro.getX(), centro.getY() + separation * 2 / 3);
                break;

            case 4:
                posiciones[0] = new Point2D(centro.getX() - separation / 1.6, centro.getY() - separation / 2);
                posiciones[1] = new Point2D(centro.getX() + separation / 1.6, centro.getY() - separation / 2);
                posiciones[2] = new Point2D(centro.getX() - separation / 1.6, centro.getY() + separation / 2);
                posiciones[3] = new Point2D(centro.getX() + separation / 1.6, centro.getY() + separation / 2);
                break;

            default:
                // Posición genérica circular para más de 4 conjuntos
                for (int i = 0; i < numConjuntos; i++) {
                    double angle = i * (360.0 / numConjuntos);
                    double x = centro.getX() + separation * Math.cos(Math.toRadians(angle));
                    double y = centro.getY() + separation * Math.sin(Math.toRadians(angle));
                    posiciones[i] = new Point2D(x, y);
                }
                break;
        }

        return posiciones;
    }

    private Area dibujarConjunto(Graphics2D g2d, NodoConjunto nodoConjunto, Point2D centro, double radio) {
        Ellipse2D.Double conjuntoCirculo = new Ellipse2D.Double(
                centro.getX() - radio, centro.getY() - radio, 2 * radio, 2 * radio);
        Area areaConjunto = new Area(conjuntoCirculo);
        g2d.setColor(new Color(100, 100, 255, 100)); // Color azul con transparencia para áreas de conjunto
        g2d.fill(areaConjunto);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2)); 
        g2d.draw(conjuntoCirculo);
        dibujarEtiquetaConjunto(g2d, centro, radio, nodoConjunto.getNombreConjunto());
        return areaConjunto;
    }

    private void dibujarOperaciones(Graphics2D g2d, Nodo nodo, Point2D centro, double radio) {
        if (nodo instanceof NodoUnario) {
            dibujarOperacionesUnarias(g2d, (NodoUnario) nodo, centro, radio);
        } else if (nodo instanceof NodoBinario) {
            dibujarOperacionesBinarias(g2d, (NodoBinario) nodo, centro, radio);
        }
    }

    private void dibujarOperacionesUnarias(Graphics2D g2d, NodoUnario nodoUnario, Point2D centro, double radio) {
        if ("^".equals(nodoUnario.getOperador())) {
            // Crear el área del universo usando las dimensiones exactas del rectángulo del universo
            Area areaUniverso = new Area(new Rectangle2D.Double(universeX, universeY, universeWidth, universeHeight)); 
            Area areaOperando = obtenerAreaNodo(nodoUnario.getOperand());
            areaUniverso.subtract(areaOperando); // Hacer la resta del operando desde el área del universo
            g2d.setColor(new Color(200, 200, 200, 150)); // Gris claro para complemento
            g2d.fill(areaUniverso);
            g2d.setColor(Color.BLACK);
            g2d.draw(areaUniverso);
        }
    }

    private void dibujarOperacionesBinarias(Graphics2D g2d, NodoBinario nodoBinario, Point2D centro, double radio) {
        Area areaIzquierda = obtenerAreaNodo(nodoBinario.getIzquierdo());
        Area areaDerecha = obtenerAreaNodo(nodoBinario.getDerecho());
        Area resultado = aplicarOperacionBinaria(areaIzquierda, areaDerecha, nodoBinario.getOperador());
        
        // Cambiar el color según la operación
        g2d.setColor(new Color(200, 200, 200, 150)); // Gris claro para áreas de operación
        g2d.fill(resultado);
        g2d.setColor(Color.BLACK);
        g2d.draw(resultado);
    }

    private Area obtenerAreaNodo(Nodo nodo) {
        if (nodo instanceof NodoConjunto) {
            NodoConjunto nodoConjunto = (NodoConjunto) nodo;
            return areasConjuntos.get(nodoConjunto.getNombreConjunto());
        } else if (nodo instanceof NodoBinario) {
            NodoBinario nodoBinario = (NodoBinario) nodo;
            Area areaIzquierda = obtenerAreaNodo(nodoBinario.getIzquierdo());
            Area areaDerecha = obtenerAreaNodo(nodoBinario.getDerecho());
            return aplicarOperacionBinaria(new Area(areaIzquierda), new Area(areaDerecha), nodoBinario.getOperador());
        }
        return new Area();
    }

    private Area aplicarOperacionBinaria(Area areaIzquierda, Area areaDerecha, String operador) {
        Area resultado = new Area(areaIzquierda);
        switch (operador) {
            case "U":
                resultado.add(areaDerecha);
                break;
            case "&":
                resultado.intersect(areaDerecha);
                break;
            case "-":
                resultado.subtract(areaDerecha);
                break;
        }
        return resultado;
    }

    private void dibujarEtiquetaConjunto(Graphics2D g2d, Point2D centro, double radio, String nombreConjunto) {
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        Rectangle2D bounds = g2d.getFontMetrics().getStringBounds(nombreConjunto, g2d);
        int x = (int) (centro.getX() - bounds.getWidth() / 2);
        int y = (int) (centro.getY() + bounds.getHeight() / 4); // Ajuste para centrar el texto verticalmente
        g2d.setColor(Color.BLACK);
        g2d.drawString(nombreConjunto, x, y);
    }

    private void dibujarOperacion(Graphics2D g2d, Nodo raizSimplificada) {
        String operacion = raizSimplificada.mostrarContenido();
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.BLACK);
        g2d.drawString("Operación: " + operacion, (int) universeX, (int) (universeY + universeHeight + 30));
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
