package Arbol;
/**
 *
 * @author Keneth Lopez
 */
import Conjuntos.ConjuntoManager;
import java.awt.geom.Area;
import java.util.Set;

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
    public int contarNodos() {
        return 1; // NodoConjunto es una hoja
    }
    
    @Override
    public String toString() {
        return nombreConjunto;
    }

    public String getNombreConjunto() {
        return nombreConjunto;
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

    @Override
    public int calcularProfundidad() {
        // Como NodoConjunto es una hoja, su profundidad es 0.
        return 0;
    }
}
