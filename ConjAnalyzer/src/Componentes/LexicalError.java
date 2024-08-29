package Componentes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        html.append("<html>");
        html.append("<head>");
        // Incluir Bootstrap CSS y JS desde CDN
        html.append("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class='container mt-5'>"); 
        html.append("<table class='table table-bordered table-striped'>");
        html.append("<thead class='table-dark'>");
        html.append("<tr><th>Valor</th><th>Mensaje</th><th>Línea</th><th>Columna</th></tr>");
        html.append("</thead>");
        html.append("<tbody>");

        for (LexicalError error : errores) {
            html.append("<tr>");
            html.append("<td>").append(error.getValor()).append("</td>");
            html.append("<td>").append(error.getMensaje()).append("</td>");
            html.append("<td>").append(error.getLinea()).append("</td>");
            html.append("<td>").append(error.getColumna()).append("</td>");
            html.append("</tr>");
        }

        html.append("</tbody>");
        html.append("</table>");
        html.append("</div>");
        // Incluir Bootstrap JS desde CDN
        html.append("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\"></script>");
        html.append("</body>");
        html.append("</html>");
        return html.toString();
    }


    // Método estático para guardar el HTML en un archivo
    public static void guardarHTMLenArchivo(String htmlContent, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(htmlContent);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo HTML: " + e.getMessage());
        }
    }

    // Método estático para abrir el archivo HTML en el navegador
    public static void abrirHTMLenNavegador(String filePath) {
        try {
            File htmlFile = new File(filePath);
            if (htmlFile.exists()) {
                // Abre el archivo HTML en el navegador predeterminado
                java.awt.Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                System.err.println("El archivo HTML no existe.");
            }
        } catch (IOException e) {
            System.err.println("Error al intentar abrir el archivo HTML en el navegador: " + e.getMessage());
        }
    }
}
