/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Componentes;

/**
 *
 * @author Keneth Lopez
 */
public class SyntaxError {
    int line;
    int column;
    Object token;
    String type;
    public SyntaxError(int line,int column,Object token,String type) {
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
    public String toString() {
        return "Error Sintáctico" + (token != null ? " en Línea " + line + " Columna " + column : "") +
            ". No se esperaba este componente: " + (token != null ? (token.equals("\"\"") ? "EMPTYSTRING" + " = " + token : type + " = " + token) : "EOF") + ".";
    }
}