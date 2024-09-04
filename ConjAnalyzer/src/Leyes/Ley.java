/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Leyes;

import Arbol.Nodo;
import java.util.List;

/**
 *
 * @author Keneth Lopez
 */
public interface Ley {
    boolean esAplicable(Nodo nodo);
    Nodo aplicar(Nodo nodo, List<String> leyesAplicadas);
}
