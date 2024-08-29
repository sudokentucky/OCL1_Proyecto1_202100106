/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generador;

/**
 *
 * @author Keneth Lopez
 */
public class GeneradorLexer {
    public static void main(String[] args) {
        try {
            String rutaEspecificaciones = "./src/Language/";
            String rutaAnalizadores = "./src/Analizadores/";
            String rutaEspecificacionesAbsoluta = new java.io.File(rutaEspecificaciones).getAbsolutePath();
            String rutaAnalizadoresAbsoluta = new java.io.File(rutaAnalizadores).getAbsolutePath();
            String[] opJFlex = {
                "-d", rutaAnalizadoresAbsoluta, 
                rutaEspecificacionesAbsoluta + "/Lexer.jflex"
            };
            
            jflex.Main.generate(opJFlex);
            System.out.println("Generación de analizador léxico completada.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
