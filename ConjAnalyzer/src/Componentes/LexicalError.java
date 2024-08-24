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
public class LexicalError {
    private String valor;
    private String mensaje;
    private int linea;
    private int columna;

    public LexicalError(String valor, String mensaje, int linea, int columna) {
        this.valor = valor;
        this.mensaje = mensaje;
        this.linea = linea;
        this.columna = columna;
    }

    // Getters y Setters
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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

    // Método estático para generar la tabla de errores léxicos en formato HTML
    public static String generarTablaHTML(List<LexicalError> errores) {
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<table border='1'>");
        html.append("<tr><th>Valor</th><th>Mensaje</th><th>Línea</th><th>Columna</th></tr>");

        for (LexicalError error : errores) {
            html.append("<tr>");
            html.append("<td>").append(error.getValor()).append("</td>");
            html.append("<td>").append(error.getMensaje()).append("</td>");
            html.append("<td>").append(error.getLinea()).append("</td>");
            html.append("<td>").append(error.getColumna()).append("</td>");
            html.append("</tr>");
        }

        html.append("</table>");
        html.append("</body></html>");
        return html.toString();
    }
}