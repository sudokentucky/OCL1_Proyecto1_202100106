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
            // Ruta donde está el archivo de JFlex
            String rutaEspecificaciones = "./src/Language/";

            // Ruta donde se generarán los archivos .java
            String rutaAnalizadores = "./src/Analizadores/";

            // Convertir a rutas absolutas para evitar problemas de rutas relativas
            String rutaEspecificacionesAbsoluta = new java.io.File(rutaEspecificaciones).getAbsolutePath();
            String rutaAnalizadoresAbsoluta = new java.io.File(rutaAnalizadores).getAbsolutePath();

            // Generar el analizador léxico con JFlex
            String[] opJFlex = {rutaEspecificacionesAbsoluta + "/Lexer.jflex", "-d", rutaAnalizadoresAbsoluta};
            jflex.Main.generate(opJFlex);
            System.out.println("Generación de analizador léxico completada.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}