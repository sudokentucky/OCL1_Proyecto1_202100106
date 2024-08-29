/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Keneth Lopez
 */

// Crear una clase para gestionar las salidas
public class OutputManager {
    private List<String> outputs = new ArrayList<>();

    // Método para agregar salida normal
    public void addOutput(String output) {
        outputs.add(output);
        System.out.println("Output agregado: " + output); 
    }

    public void addErrorOutput(String output) {
        outputs.add("<span style='color:red;'>" + output + "</span>");
        System.out.println("Error Output agregado: " + output); 
    }
    public String getAllOutputs() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>"); 
        for (String output : outputs) {
            sb.append(output).append("<br>"); 
        }
        sb.append("</body></html>"); 
        return sb.toString();
    }

    // Método para limpiar las salidas
    public void clearOutputs() {
        outputs.clear();
    }
}

