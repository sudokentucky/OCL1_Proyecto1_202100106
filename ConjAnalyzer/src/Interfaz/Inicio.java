/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;
import Analizadores.AnalizadorLexico;
import Analizadores.Parser;
import Arbol.*;
import java.io.StringReader;
import Componentes.Token;
import Componentes.LexicalError;
import Componentes.SyntaxError;
import Conjuntos.ConjuntoManager;
import Conjuntos.ConjuntoManager.Operacion;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.SwingUtilities;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;

/**
 *
 * @author Keneth Lopez
 */


public class Inicio extends javax.swing.JFrame {
    public List<ArbolExpresion> arbolesExpresion = new ArrayList<>();
    private OutputManager outputManager;
    private ConjuntoManager conjuntoManager;
    private SimplificadorOperaciones simplificador;
    private VennDiagramPanel vennDiagramPanel;
    private ArbolExpresion arbolExpresion;
    private List<Map.Entry<String, Operacion>> operacionesList;
    private int currentOperationIndex = 0;
    private Parser parser;  // Añadir aquí


    /**
     * Creates new form Inicio
     */
    /**
     * Creates new form Inicio
     */
    public Inicio() {
        this.outputManager = new OutputManager();
        this.conjuntoManager = new ConjuntoManager();
        this.simplificador = new SimplificadorOperaciones(conjuntoManager);

        // Crear un nodo raíz inicial para el árbol (por ejemplo, un NodoConjunto vacío)
        Nodo nodoRaizInicial = new NodoConjunto("", conjuntoManager);

        // Inicializar el árbol de expresiones con el nodo raíz inicial
        this.arbolExpresion = new ArbolExpresion(nodoRaizInicial);

        initComponents();

        // Inicializar el VennDiagramPanel con el árbol de expresión
        vennDiagramPanel = new VennDiagramPanel(arbolExpresion, conjuntoManager);
        JpanelGraph.setLayout(new BorderLayout());
        JpanelGraph.add(vennDiagramPanel, BorderLayout.CENTER);
        JpanelGraph.revalidate();
        JpanelGraph.repaint();
    }


    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        entrada = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Salida = new javax.swing.JTextPane();
        entrada1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TextoEnrada = new javax.swing.JTextArea();
        JpanelGraph = new javax.swing.JPanel();
        entrada2 = new javax.swing.JLabel();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        Archivo = new javax.swing.JMenu();
        NewFile = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        saveAs = new javax.swing.JMenuItem();
        excecute = new javax.swing.JMenu();
        AnalisisExcecute = new javax.swing.JMenuItem();
        Reportes = new javax.swing.JMenu();
        Tokens = new javax.swing.JMenuItem();
        LexerErr = new javax.swing.JMenuItem();
        SyntaxErr = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        entrada.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        entrada.setText("Entrada");

        Salida.setEditable(false);
        jScrollPane1.setViewportView(Salida);

        entrada1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        entrada1.setText("Consola");

        TextoEnrada.setColumns(20);
        TextoEnrada.setRows(5);
        jScrollPane4.setViewportView(TextoEnrada);

        JpanelGraph.setAlignmentX(0.7F);
        JpanelGraph.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                JpanelGraphPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout JpanelGraphLayout = new javax.swing.GroupLayout(JpanelGraph);
        JpanelGraph.setLayout(JpanelGraphLayout);
        JpanelGraphLayout.setHorizontalGroup(
            JpanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );
        JpanelGraphLayout.setVerticalGroup(
            JpanelGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        entrada2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        entrada2.setText("Graficas");

        previousButton.setText("Anterior");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setText("Siguiente");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        Archivo.setText("Archivo");

        NewFile.setText("Nuevo Archivo");
        NewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewFileActionPerformed(evt);
            }
        });
        Archivo.add(NewFile);

        save.setText("Guardar");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        Archivo.add(save);

        saveAs.setText("Guardar como");
        saveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsActionPerformed(evt);
            }
        });
        Archivo.add(saveAs);

        jMenuBar1.add(Archivo);

        excecute.setText("Ejecutar");

        AnalisisExcecute.setText("Analisis");
        AnalisisExcecute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AnalisisExcecuteActionPerformed(evt);
            }
        });
        excecute.add(AnalisisExcecute);

        jMenuBar1.add(excecute);

        Reportes.setText("Reportes");
        Reportes.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                ReportesMenuSelected(evt);
            }
        });

        Tokens.setText("Tokens");
        Tokens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TokensActionPerformed(evt);
            }
        });
        Reportes.add(Tokens);

        LexerErr.setText("Errores Lexicos");
        LexerErr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LexerErrActionPerformed(evt);
            }
        });
        Reportes.add(LexerErr);

        SyntaxErr.setText("Errores Sintacticos");
        SyntaxErr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SyntaxErrActionPerformed(evt);
            }
        });
        Reportes.add(SyntaxErr);

        jMenuBar1.add(Reportes);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 859, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(entrada1)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(previousButton)
                                .addGap(99, 99, 99)
                                .addComponent(nextButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(JpanelGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(entrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(entrada2)
                .addGap(248, 248, 248))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entrada)
                    .addComponent(entrada2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JpanelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entrada1)
                    .addComponent(previousButton)
                    .addComponent(nextButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReportesMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_ReportesMenuSelected
        // TODO add your handling code here:
    }//GEN-LAST:event_ReportesMenuSelected

    private void TokensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TokensActionPerformed
        // TODO add your handling code here:
        abrirReporte("tokens_report.html");

    }//GEN-LAST:event_TokensActionPerformed

    private File currentFile = null;

    private void saveToFile(File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            // Escribe el contenido del JTextArea en el archivo
            fileWriter.write(TextoEnrada.getText());
            JOptionPane.showMessageDialog(this, "Archivo guardado exitosamente.", "Guardar Archivo", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void NewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewFileActionPerformed
        // TODO add your handling code here:
        if (!TextoEnrada.getText().isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Hay cambios no guardados. ¿Desea guardar antes de crear un nuevo archivo?",
                "Confirmar Nuevo Archivo", 
                JOptionPane.YES_NO_CANCEL_OPTION, 
                JOptionPane.WARNING_MESSAGE);

            if (confirm == JOptionPane.YES_OPTION) {
                saveActionPerformed(evt);
            } else if (confirm == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }

        TextoEnrada.setText("");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Nuevo Archivo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CA (.ca)", "ca"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().endsWith(".ca")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".ca");
            }
            saveToFile(fileToSave);
            currentFile = fileToSave;

            JOptionPane.showMessageDialog(this, "Nuevo archivo creado y guardado exitosamente.", "Nuevo Archivo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Operación cancelada. El archivo no se ha guardado.", "Operación Cancelada", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_NewFileActionPerformed
    private void updateVennDiagramPanel() {
    // Verificar si hay árboles de expresión en la lista
    if (arbolesExpresion != null && !arbolesExpresion.isEmpty()) {
        // Verificar que el índice actual esté dentro del rango de la lista de árboles
        if (currentOperationIndex >= 0 && currentOperationIndex < arbolesExpresion.size()) {
            // Obtener el árbol correspondiente de la lista de árboles
            ArbolExpresion arbolActual = arbolesExpresion.get(currentOperationIndex);

            // Debug: Imprimir el contenido del árbol actual
            System.out.println("Mostrando árbol de expresión en índice: " + currentOperationIndex);
            System.out.println("Contenido del árbol: " + arbolActual.mostrarContenido());

            // Actualizar el árbol de expresión en el panel y repintar
            vennDiagramPanel.updateDiagram(arbolActual);  // Usa el nuevo método para actualizar el diagrama
        } else {
            System.out.println("Índice de árbol de expresión inválido.");
        }
    } else {
        System.out.println("No hay árboles de expresión para mostrar.");
    }
}

    private void AnalisisExcecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AnalisisExcecuteActionPerformed
        // Verificar si el JTextArea está vacío antes de proceder
        if (TextoEnrada.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El área de texto está vacía. Por favor ingrese el código a analizar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        arbolExpresion.mostrarContenido(); // Mostrar el contenido del árbol para depuración
        outputManager.clearOutputs();
        String textoEntrada = TextoEnrada.getText();
        StringReader sr = new StringReader(textoEntrada);
        AnalizadorLexico lexer = new AnalizadorLexico(sr);
        Parser parser = new Parser(lexer, outputManager, conjuntoManager, simplificador, arbolExpresion, this);

        
        try {
            parser.parse();
            arbolExpresion.mostrarContenido(); // Mostrar el contenido del árbol para depuración
            generarYGuardarReportes(lexer, parser);
            SwingUtilities.invokeLater(() -> {
                Salida.setContentType("text/html");
                Salida.setText(outputManager.getAllOutputs());
            });
            arbolExpresion.mostrarContenido(); // Mostrar el contenido del árbol para depuración
            simplificador.generarJSON("./src/Salidas/operaciones.json");

            // Obtener el nodo raíz del árbol de expresión actualizado después del análisis
            Nodo nodoRaiz = arbolExpresion.getRaiz(); // Esto debería ser el nodo raíz del árbol actualizado

            // Verificar si el árbol tiene un nodo raíz válido
            if (nodoRaiz != null) {
                // Actualizar el VennDiagramPanel con el nuevo árbol de expresión
                vennDiagramPanel.setArbolExpresion(arbolExpresion);  // Asignar el árbol de expresión al panel
                vennDiagramPanel.repaint();  // Repintar el panel para reflejar el nuevo árbol de expresión
            } else {
                System.out.println("El árbol de expresión no tiene un nodo raíz válido.");
            }

            JOptionPane.showMessageDialog(this, "Análisis léxico y sintáctico completado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            SwingUtilities.invokeLater(() -> {
                Salida.setText("Ocurrió un error durante el análisis: " + e.getMessage());
            });
            JOptionPane.showMessageDialog(this, "Ocurrió un error durante el análisis: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_AnalisisExcecuteActionPerformed

    private void generarYGuardarReportes(AnalizadorLexico lexer, Parser parser) {
        // Obtener tokens y errores del lexer y parser
        List<Token> tokens = lexer.getTokens();
        List<LexicalError> lexicalErrors = lexer.getErrors();
        List<SyntaxError> syntaxErrors = parser.getSyntaxErrors();

        // Generar y guardar reportes HTML si las listas no están vacías
        if (!tokens.isEmpty()) {
            guardarReporteTokens(tokens);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron tokens para generar el reporte.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        if (!lexicalErrors.isEmpty()) {
            guardarReporteLexicalErrors(lexicalErrors);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron errores léxicos para generar el reporte.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

        if (!syntaxErrors.isEmpty()) {
            guardarReporteSyntaxErrors(syntaxErrors);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontraron errores sintácticos para generar el reporte.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void guardarReporteTokens(List<Token> tokens) {
        try {
            String htmlContent = Token.generarTablaHTML(tokens);
            String filePath = "tokens_report.html";
            Token.guardarHTMLenArchivo(htmlContent, filePath);
            JOptionPane.showMessageDialog(this, "Reporte de tokens guardado exitosamente en " + filePath, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el reporte de tokens: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void guardarReporteLexicalErrors(List<LexicalError> erroresLexicos) {
        try {
            String htmlContent = LexicalError.generarTablaHTML(erroresLexicos);
            String filePath = "lexical_errors_report.html";
            LexicalError.guardarHTMLenArchivo(htmlContent, filePath);
            JOptionPane.showMessageDialog(this, "Reporte de errores léxicos guardado exitosamente en " + filePath, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el reporte de errores léxicos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void guardarReporteSyntaxErrors(List<SyntaxError> erroresSintacticos) {
        try {
            String htmlContent = SyntaxError.generarTablaHTML(erroresSintacticos);
            String filePath = "syntax_errors_report.html";
            SyntaxError.guardarHTMLenArchivo(htmlContent, filePath);
            JOptionPane.showMessageDialog(this, "Reporte de errores sintácticos guardado exitosamente en " + filePath, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el reporte de errores sintácticos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void LexerErrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LexerErrActionPerformed
        // TODO add your handling code here:
        abrirReporte("lexical_errors_report.html");

    }//GEN-LAST:event_LexerErrActionPerformed

    private void SyntaxErrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SyntaxErrActionPerformed
        // TODO add your handling code here:
        abrirReporte("syntax_errors_report.html");

    }//GEN-LAST:event_SyntaxErrActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        if (currentFile != null) {
            // Guardar el archivo actual
            saveToFile(currentFile);
        } else {
            // Si no hay un archivo actual, usar Guardar como
            saveAsActionPerformed(evt);
        }
    }//GEN-LAST:event_saveActionPerformed

    private void saveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();

        // Configurar el filtro de archivos para que sólo muestre archivos con extensión .ca
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CA (.ca)", "ca"));

        // Mostrar el diálogo de guardar archivo
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            File fileToSave = fileChooser.getSelectedFile();

            // Asegurarse de que el archivo tiene la extensión .ca
            if (!fileToSave.getName().endsWith(".ca")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".ca");
            }

            // Guardar el archivo
            saveToFile(fileToSave);

            // Actualizar la referencia al archivo actual
            currentFile = fileToSave;
        }
    }//GEN-LAST:event_saveAsActionPerformed

    private void JpanelGraphPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_JpanelGraphPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_JpanelGraphPropertyChange

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        // TODO add your handling code here:
        // Verificar si hay operaciones y si no estamos en la primera operación
            if (arbolesExpresion != null && currentOperationIndex > 0) {
            currentOperationIndex--;
            System.out.println("Navegando a árbol de expresión anterior: índice " + currentOperationIndex);
            updateVennDiagramPanel(); // Llama al método para actualizar el panel
        }
    }//GEN-LAST:event_previousButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        // Verificar si hay árboles y si no estamos en el último árbol
        if (arbolesExpresion != null && currentOperationIndex < arbolesExpresion.size() - 1) {
            currentOperationIndex++;
            System.out.println("Navegando a siguiente árbol de expresión: índice " + currentOperationIndex);
            updateVennDiagramPanel(); // Llama al método para actualizar el panel
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void abrirReporte(String filePath) {
        try {
            // Abrir el archivo HTML en el navegador predeterminado del sistema
            java.awt.Desktop.getDesktop().browse(java.nio.file.Paths.get(filePath).toUri());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "No se pudo abrir el reporte: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
        

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AnalisisExcecute;
    private javax.swing.JMenu Archivo;
    private javax.swing.JPanel JpanelGraph;
    private javax.swing.JMenuItem LexerErr;
    private javax.swing.JMenuItem NewFile;
    private javax.swing.JMenu Reportes;
    private javax.swing.JTextPane Salida;
    private javax.swing.JMenuItem SyntaxErr;
    private javax.swing.JTextArea TextoEnrada;
    private javax.swing.JMenuItem Tokens;
    private javax.swing.JLabel entrada;
    private javax.swing.JLabel entrada1;
    private javax.swing.JLabel entrada2;
    private javax.swing.JMenu excecute;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem saveAs;
    // End of variables declaration//GEN-END:variables
}
