package Arbol;

import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.util.HashSet;
import java.util.Set;
import math.geom2d.Point2D;

public abstract class Nodo {

    // Método abstracto para evaluar el nodo y obtener su conjunto de caracteres
    public abstract Set<Character> evaluar();

    // Método abstracto para mostrar el contenido del nodo como una cadena
    public abstract String mostrarContenido();

    // Método abstracto para representar el nodo como una cadena
    @Override
    public abstract String toString();

    // Método abstracto para dibujar el nodo en el diagrama de Venn
    public abstract Area dibujar(Graphics2D g2d, Point2D centro, double radio);

    // Nuevo método abstracto para recopilar nombres de conjuntos en el nodo y sus hijos
    public abstract void recopilarConjuntos(Set<String> conjuntos);
}

