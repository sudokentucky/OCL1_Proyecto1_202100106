/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

import java.util.List;

/**
 *
 * @author Keneth Lopez
 */
public class SyntaxError {
    private int line;
    private int column;
    private Object token;
    private String type;

    public SyntaxError(int line, int column, Object token, String type) {
        this.line = line;
        this.column = column;
        this.token = token;
        this.type = type;
    }

    public void print() {
        System.out.println(
            "Syntax Error" + (token != null ? " in Line " + line + " Column " + column : "") +
            ". This Component was not expected: " + (token != null ? type + " = " + token : "EOF") + "."
        );
    }

    @Override
    public String toString() {
        return "Error Sintáctico" + (token != null ? " en Línea " + line + " Columna " + column : "") +
            ". No se esperaba este componente: " + (token != null ? (token.equals("\"\"") ? "EMPTYSTRING" + " = " + token : type + " = " + token) : "EOF") + ".";
    }

    // Getters y Setters
    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Método estático para generar la tabla de errores sintácticos en formato HTML
    public static String generarTablaHTML(List<SyntaxError> errores) {
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<table border='1'>");
        html.append("<tr><th>Línea</th><th>Columna</th><th>Token</th><th>Tipo</th></tr>");

        for (SyntaxError error : errores) {
            html.append("<tr>");
            html.append("<td>").append(error.getLine()).append("</td>");
            html.append("<td>").append(error.getColumn()).append("</td>");
            html.append("<td>").append(error.getToken()).append("</td>");
            html.append("<td>").append(error.getType()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("</body></html>");
        return html.toString();
    }
}