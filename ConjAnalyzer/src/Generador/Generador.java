/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Generador;

/**
 *
 * @author Keneth Lopez
 */
public class Generador {
    public static void main(String[] args) {
        try {
            // Ruta donde están los archivos de JFlex y CUP
            String rutaEspecificaciones = "./src/Language/";

            // Ruta donde se generarán los archivos .java
            String rutaAnalizadores = "./src/Analizadores/";

            // 1. Generar el analizador léxico con JFlex
            String[] opJFlex = {rutaEspecificaciones + "Lexer.jflex", "-d", rutaAnalizadores};
            jflex.Main.generate(opJFlex);
            System.out.println("Generación de analizador léxico completada.");

            // 2. Generar el analizador sintáctico con CUP
            String[] opCUP = {"-destdir", rutaAnalizadores, "-parser", "Parser", rutaEspecificaciones + "Parser.cup"};
            java_cup.Main.main(opCUP);
            System.out.println("Generación de analizador sintáctico completada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
