package Pruebas;

import Analizadores.AnalizadorLexico;
import Analizadores.*;
import Componentes.LexicalError;
import Componentes.SyntaxError;
import Componentes.Token;
import java.io.FileReader;
import java.util.List;
import Interfaz.OutputManager;
import Arbol.SimplificadorOperaciones;
import Conjuntos.ConjuntoManager; 


/**
 *
 * @author Keneth Lopez
 */
public class main {
    public static void main(String[] args) {
        try {
            String rutaarchivo = "./src/Pruebas/OUTRO.txt";
            
            // Crear una instancia de ConjuntoManager
            ConjuntoManager conjuntoManager = new ConjuntoManager();

            // Crear una instancia de SimplificadorOperaciones
            SimplificadorOperaciones simplificador = new SimplificadorOperaciones(conjuntoManager);

            // Leer el archivo de entrada para analizar
            FileReader fr = new FileReader(rutaarchivo);

            // Crear el analizador léxico (lexer)
            AnalizadorLexico lexer = new AnalizadorLexico(fr);

            // Crear el analizador sintáctico (parser) con el lexer y outputManager como entrada
            Parser parser = new Parser(lexer); // Asegúrate de que el parser acepte simplificador como argumento

            // Ejecutar el análisis sintáctico
            parser.parse();
            System.out.println("------------------------------------");
            conjuntoManager.getOperaciones();

            System.out.println("Análisis léxico y sintáctico completado.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generarYAbrirReporteTokens(List<Token> tokens) {
        // Generar contenido HTML para los Tokens
        String htmlContent = Token.generarTablaHTML(tokens);
        String filePath = "tokens_report.html";

        // Guardar el HTML en un archivo y abrirlo en el navegador
        Token.guardarHTMLenArchivo(htmlContent, filePath);
        Token.abrirHTMLenNavegador(filePath);
    }

    private static void generarYAbrirReporteLexicalErrors(List<LexicalError> erroresLexicos) {
        // Generar contenido HTML para los Errores Léxicos
        String htmlContent = LexicalError.generarTablaHTML(erroresLexicos);
        String filePath = "lexical_errors_report.html";

        // Guardar el HTML en un archivo y abrirlo en el navegador
        LexicalError.guardarHTMLenArchivo(htmlContent, filePath);
        LexicalError.abrirHTMLenNavegador(filePath);
    }

    private static void generarYAbrirReporteSyntaxErrors(List<SyntaxError> erroresSintacticos) {
        // Generar contenido HTML para los Errores Sintácticos
        String htmlContent = SyntaxError.generarTablaHTML(erroresSintacticos);
        String filePath = "syntax_errors_report.html";

        // Guardar el HTML en un archivo y abrirlo en el navegador
        SyntaxError.guardarHTMLenArchivo(htmlContent, filePath);
        SyntaxError.abrirHTMLenNavegador(filePath);
    }
}