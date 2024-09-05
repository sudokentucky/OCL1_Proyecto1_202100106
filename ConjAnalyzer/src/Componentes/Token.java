package Componentes;
/**
 *
 * @author Keneth Lopez
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    // Método estático para generar tokens en formato HTML 
    public static String generarTablaHTML(List<Token> tokens) {
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
        html.append("<tr><th>Tipo</th><th>Valor</th><th>Línea</th><th>Columna</th></tr>");
        html.append("</thead>");
        html.append("<tbody>");

        for (Token token : tokens) {
            html.append("<tr>");
            html.append("<td>").append(token.getTipo()).append("</td>");
            html.append("<td>").append(token.getValor()).append("</td>");
            html.append("<td>").append(token.getLinea()).append("</td>");
            html.append("<td>").append(token.getColumna()).append("</td>");
            html.append("</tr>");
        }

        html.append("</tbody>");
        html.append("</table>");
        html.append("</div>");
        html.append("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\" crossorigin=\"anonymous\"></script>");
        html.append("</body>");
        html.append("</html>");
        return html.toString();
    }
    
    public static void guardarHTMLenArchivo(String htmlContent, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(htmlContent);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo HTML: " + e.getMessage());
        }
    }

    public static void abrirHTMLenNavegador(String filePath) {
        try {
            File htmlFile = new File(filePath);
            if (htmlFile.exists()) {
                java.awt.Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                System.err.println("El archivo HTML no existe.");
            }
        } catch (IOException e) {
            System.err.println("Error al intentar abrir el archivo HTML en el navegador: " + e.getMessage());
        }
    }
}
