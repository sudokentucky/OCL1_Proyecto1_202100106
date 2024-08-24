/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;
/**
 *
 * @author Keneth Lopez
 */
import java.util.List;

public class Token {
    private String tipo;
    private String valor;
    private int linea;
    private int columna;

    public Token(String tipo, String valor, int linea, int columna) {
        this.tipo = tipo;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    // Método estático para generar la tabla de tokens en formato HTML
    public static String generarTablaHTML(List<Token> tokens) {
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<table border='1'>");
        html.append("<tr><th>Tipo</th><th>Valor</th><th>Línea</th><th>Columna</th></tr>");

        for (Token token : tokens) {
            html.append("<tr>");
            html.append("<td>").append(token.getTipo()).append("</td>");
            html.append("<td>").append(token.getValor()).append("</td>");
            html.append("<td>").append(token.getLinea()).append("</td>");
            html.append("<td>").append(token.getColumna()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("</body></html>");
        return html.toString();
    }
}
