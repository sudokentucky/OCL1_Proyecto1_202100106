package Componentes;
/**
 *
 * @author Keneth Lopez
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SyntaxError {
    private int line;
    private int column;
    private Object token;
    private String comentario;

    // Constructor actualizado
    public SyntaxError(int line, int column, Object token, String comentario) {
        this.line = line;
        this.column = column;
        this.token = token;
        this.comentario = comentario;
    }

    public void print() {
        System.out.println(
            "Syntax Error" + (token != null ? " in Line " + line + " Column " + column : "") +
            ". This Component was not expected: " + (token != null ? comentario + " = " + token : "EOF") + "."
        );
    }

    // Método toString actualizado
    @Override
    public String toString() {
        return "Error Sintáctico" + (token != null ? " en Línea " + line + " Columna " + column : "") +
            ". No se esperaba este componente: " + (token != null ? comentario + " = " + token : "EOF") + ".";
    }

    // Getters y Setters actualizados
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    // Método para generar la tabla HTML con Bootstrap
public static String generarTablaHTML(List<SyntaxError> errores) {
        StringBuilder html = new StringBuilder();
        html.append("<html>");
        html.append("<head>");
        html.append("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\" crossorigin=\"anonymous\">");
        html.append("</head>");
        html.append("<body>");
        html.append("<div class='container mt-5'>");
        html.append("<table class='table table-bordered table-striped'>");
        html.append("<thead class='table-dark'>");
        html.append("<tr><th>Línea</th><th>Columna</th><th>Token</th><th>Comentario</th></tr>");
        html.append("</thead>");
        html.append("<tbody>");

        for (SyntaxError error : errores) {
            html.append("<tr>");
            html.append("<td>").append(error.getLine()).append("</td>");
            html.append("<td>").append(error.getColumn()).append("</td>");
            html.append("<td>").append(error.getToken() != null ? error.getToken() : "NULL").append("</td>");
            html.append("<td>").append(error.getComentario()).append("</td>");
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


    // Método para guardar el HTML en un archivo
    public static void guardarHTMLenArchivo(String htmlContent, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(htmlContent);
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo HTML: " + e.getMessage());
        }
    }

    // Método para abrir el archivo HTML en el navegador
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
