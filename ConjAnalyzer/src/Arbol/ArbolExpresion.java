package Arbol;

import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.Set;
import math.geom2d.Point2D;
import math.geom2d.conic.Circle2D;

public class ArbolExpresion {
    private Nodo raiz; // Raíz del árbol de expresiones
    private Nodo raizSimplificada;

    public ArbolExpresion(Nodo raiz) {
        this.raiz = raiz;
    }

    public ArbolExpresion() {
        this.raiz = null; // Inicialmente no hay raíz
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    // Método para simplificar el árbol
    public void simplificar(SimplificadorOperaciones simplificador, String nombreOperacion) {
        this.raiz = simplificador.simplificar(this.raiz, nombreOperacion);
    }

    // Método para evaluar el árbol
    public Set<Character> evaluar() {
        return raiz.evaluar();
    }

    // Método para mostrar la notación del árbol
    public String mostrarContenido() {
        return raiz.mostrarContenido();
    }

    // Nuevo método para construir el árbol desde una estructura de nodos
    public void construir(Nodo nodoRaiz) {
        this.raiz = nodoRaiz;
    }
    
    // Nuevo método para establecer el árbol simplificado
    public void setRaizSimplificada(Nodo raizSimplificada) {
        this.raizSimplificada = raizSimplificada;
    }

    // Nuevo método para obtener el árbol simplificado
    public Nodo getRaizSimplificada() {
        return raizSimplificada;
    }

    // Método para calcular el universo y las áreas de todos los nodos en el árbol
    public Area calcularAreaTotal(double x, double y, double width, double height) {
        if (raiz == null) {
            return new Area(); // Retornar un área vacía si no hay nodos
        }

        // Crear el círculo invisible central para el universo
        Circle2D universo = crearUniverso(x, y, width, height);

        // Calcular áreas de nodos basadas en el círculo del universo
        return calcularAreaNodo(raiz, universo);
    }

    // Método para crear el círculo del universo
    private Circle2D crearUniverso(double x, double y, double width, double height) {
        double radius = Math.min(width, height) / 2;
        Point2D center = new Point2D(x + width / 2, y + height / 2);
        return new Circle2D(center, radius);
    }

    // Método recursivo para calcular el área de un nodo basado en el círculo del universo
    private Area calcularAreaNodo(Nodo nodo, Circle2D universo) {
        if (nodo instanceof NodoConjunto) {
            return calcularAreaConjunto((NodoConjunto) nodo, universo);
        } else if (nodo instanceof NodoBinario) {
            return calcularAreaBinaria((NodoBinario) nodo, universo);
        } else if (nodo instanceof NodoUnario) {
            return calcularAreaUnaria((NodoUnario) nodo, universo);
        }
        return new Area(); // En caso de nodo desconocido
    }

    // Cálculo del área para NodoConjunto basado en el círculo del universo
    private Area calcularAreaConjunto(NodoConjunto nodo, Circle2D universo) {
        double radius = universo.radius()/ 2; // Ajuste del tamaño del conjunto
        Point2D centro = universo.center();
        Circle2D conjuntoCirculo = new Circle2D(centro.getX(), centro.getY(), radius);
        Area area = new Area(conjuntoCirculo.asAwtShape());
        System.out.println("Calculando área para conjunto " + nodo.getNombreConjunto() + ": " + area.getBounds());
        return area;
    }

    // Cálculo del área para NodoBinario basado en el círculo del universo
    private Area calcularAreaBinaria(NodoBinario nodo, Circle2D universo) {
        System.out.println("Calculando área para operador binario: " + nodo.getOperador());

        double radius = universo.radius()/ 2;
        Point2D centro = universo.center();

        // Definir posiciones relativas para los nodos izquierdo y derecho
        Circle2D circuloIzquierdo = new Circle2D(centro.getX() - radius / 2, centro.getY(), radius);
        Circle2D circuloDerecho = new Circle2D(centro.getX() + radius / 2, centro.getY(), radius);

        // Calcular áreas de nodos izquierdo y derecho
        Area areaIzquierda = calcularAreaNodo(nodo.getIzquierdo(), circuloIzquierdo);
        Area areaDerecha = calcularAreaNodo(nodo.getDerecho(), circuloDerecho);

        // Crear área resultante para las operaciones binarias
        Area areaResultante = new Area(areaIzquierda);
        switch (nodo.getOperador()) {
            case "U":
                areaResultante.add(areaDerecha);
                break;
            case "&":
                areaResultante.intersect(areaDerecha);
                break;
            case "-":
                areaResultante.subtract(areaDerecha);
                break;
            default:
                throw new UnsupportedOperationException("Operador desconocido: " + nodo.getOperador());
        }

        return areaResultante;
    }

    // Cálculo del área para NodoUnario basado en el círculo del universo
    private Area calcularAreaUnaria(NodoUnario nodo, Circle2D universo) {
        System.out.println("Calculando área para operador unario: " + nodo.getOperador());

        Area areaUniverso = new Area(universo.asAwtShape());
        Area areaHijo = calcularAreaNodo(nodo.getOperand(), universo);

        if ("^".equals(nodo.getOperador())) {
            if (areaHijo != null) {
                areaUniverso.subtract(areaHijo);
                System.out.println("Área calculada del complemento (" + nodo.getOperand().mostrarContenido() + "): " + areaUniverso.getBounds());
                return areaUniverso;
            } else {
                System.out.println("Área del hijo es null, no se puede calcular complemento.");
            }
        }

        return new Area(); // Si no hay un área calculada
    }
}
