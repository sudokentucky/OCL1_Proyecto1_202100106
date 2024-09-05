package Arbol;
/*
 *
 * @author Keneth Lopez
 */
import java.util.Set;


public abstract class Nodo {
    private boolean simplificado = false;

    // Método abstracto para evaluar el nodo y obtener su conjunto de caracteres
    public abstract Set<Character> evaluar();

    // Método abstracto para mostrar el contenido del nodo como una cadena
    public abstract String mostrarContenido();

    // Método abstracto para representar el nodo como una cadena
    @Override
    public abstract String toString();
    
    public boolean isSimplificado() {
        return simplificado;
    }
    
    public void setSimplificado(boolean simplificado) {
        this.simplificado = simplificado;
    }
    //método para calcular la profundidad del nodo
    public abstract int calcularProfundidad();
    public abstract int contarNodos();
    
    public int calcularComplejidad() {
        return calcularProfundidad() + contarNodos();
    }
    
    //método abstracto para recopilar nombres de conjuntos en el nodo y sus hijos
    public abstract void recopilarConjuntos(Set<String> conjuntos);
}

