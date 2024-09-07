/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conjanalyzer;
import Interfaz.Inicio;

/**
 *
 * @author Keneth Lopez
 */
public class ConjAnalyzer {

    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(() -> {
            Inicio frame = new Inicio();  // Crear la instancia de la ventana
            frame.pack();  // Ajustar el tamaño según los componentes
            frame.setLocationRelativeTo(null);  // Centrar la ventana en la pantalla
            frame.setVisible(true);  // Hacer visible la ventana
        });
    }
    
}
