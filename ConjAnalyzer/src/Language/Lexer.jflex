package Analizadores;

import Componentes.LexicalError;
import Componentes.Token;
import java_cup.runtime.Symbol; 
import java.util.ArrayList;

%%

%{
    // Declaración de las listas de tokens y errores
    private ArrayList<LexicalError> errors = new ArrayList<>();
    private ArrayList<Token> tokens = new ArrayList<>();

    // Método para agregar errores
    void addError(String valor, String mensaje, int linea, int columna) {
        errors.add(new LexicalError(valor, mensaje, linea, columna));
    }

    // Método para obtener errores
    public ArrayList<LexicalError> getErrors() {
        return errors;
    }

    // Método para agregar tokens
    void addToken(String tipo, String valor, int linea, int columna) {
        tokens.add(new Token(tipo, valor, linea, columna));
    }

    // Método para obtener tokens
    public ArrayList<Token> getTokens() {
        return tokens;
    }
%}

%class AnalizadorLexico
%public
%line
%column
%unicode
%char
%cup
%type Symbol

%init{
    yyline = 1;
    yycolumn = 1;
%init}

// Reglas más específicas antes de las generales

UNION = "U"
INTERSECCION = "&"
DIFERENCIA = "-"
COMPLEMENTO = "^"
HASTA = "~"

LLAVE_IZQ = "{"
LLAVE_DER = "}"

ID = [a-zA-Z_][a-zA-Z0-9_]* 
NO_UNIVERSO = [\x00-\x20\x7F-\xFF]

RES_CONJ = "CONJ"
RES_OPERA = "OPERA"
RES_EVALUAR = "EVALUAR"

PAR_IZQ = "("
PAR_DER = ")"
COMA = ","
FLECHA = "->"
DOSPUNTOS = ":"
PUNTOYCOMA = ";"
ASCII = [\x21-\x7E]  // Caracteres ASCII del 33 al 126
CONJUNTO_ELEMENTOS = {ASCII}(\s*,\s*{ASCII})*
ELEMENTOS_EVALUAR = \{\s*{ASCII}(\s*,\s*{ASCII})*\s*\}

BLANCOS = [ \t\r]+
SALTOLINEA = \n
CARACTER = [^\r\n]
COM_LINEA = "#"{CARACTER}* {SALTOLINEA}?
COM_MULTI = "<!" [^!] ~"!>" | "<!" "!"+ ">"


%%

{BLANCOS} {/* Ignorar espacios en blanco */}

{SALTOLINEA} {  }

{COM_LINEA} | {COM_MULTI} {/* Ignorar comentarios */}

// Palabras reservadas
{RES_CONJ} { addToken("CONJ", yytext(), yyline, yycolumn); return new Symbol(sym.CONJ, yyline, yycolumn, yytext()); }
{RES_OPERA} { addToken("OPERA", yytext(), yyline, yycolumn); return new Symbol(sym.OPERA, yyline, yycolumn, yytext()); }
{RES_EVALUAR} { addToken("EVALUAR", yytext(), yyline, yycolumn); return new Symbol(sym.EVALUAR, yyline, yycolumn, yytext()); }

// Operadores de conjuntos
{UNION} { addToken("UNION", yytext(), yyline, yycolumn); return new Symbol(sym.UNION, yyline, yycolumn, yytext()); }
{INTERSECCION} { addToken("INTERSECCION", yytext(), yyline, yycolumn); return new Symbol(sym.INTERSECCION, yyline, yycolumn, yytext()); }
{DIFERENCIA} { addToken("DIFERENCIA", yytext(), yyline, yycolumn); return new Symbol(sym.DIFERENCIA, yyline, yycolumn, yytext()); }
{COMPLEMENTO} { addToken("COMPLEMENTO", yytext(), yyline, yycolumn); return new Symbol(sym.COMPLEMENTO, yyline, yycolumn, yytext()); }
{HASTA} { addToken("HASTA", yytext(), yyline, yycolumn); return new Symbol(sym.HASTA, yyline, yycolumn, yytext()); }
// Símbolos y operadores
{DOSPUNTOS} { addToken("DOSPUNTOS", yytext(), yyline, yycolumn); return new Symbol(sym.DOSPUNTOS, yyline, yycolumn, yytext()); }
{LLAVE_IZQ} { addToken("LLAVE_IZQ", yytext(), yyline, yycolumn); return new Symbol(sym.LLAVE_IZQ, yyline, yycolumn, yytext()); }
{LLAVE_DER} { addToken("LLAVE_DER", yytext(), yyline, yycolumn); return new Symbol(sym.LLAVE_DER, yyline, yycolumn, yytext()); }
{PAR_IZQ} { addToken("PAR_IZQ", yytext(), yyline, yycolumn); return new Symbol(sym.PAR_IZQ, yyline, yycolumn, yytext()); }
{PAR_DER} { addToken("PAR_DER", yytext(), yyline, yycolumn); return new Symbol(sym.PAR_DER, yyline, yycolumn, yytext()); }
{COMA} { addToken("COMA", yytext(), yyline, yycolumn); return new Symbol(sym.COMA, yyline, yycolumn, yytext()); }
{FLECHA} { addToken("FLECHA", yytext(), yyline, yycolumn); return new Symbol(sym.FLECHA, yyline, yycolumn, yytext()); }
{PUNTOYCOMA} { addToken("PUNTOYCOMA", yytext(), yyline, yycolumn); return new Symbol(sym.PUNTOYCOMA, yyline, yycolumn, yytext()); }

{ELEMENTOS_EVALUAR} {
    addToken("ELEMENTOS_EVALUAR", yytext(), yyline, yycolumn);
    return new Symbol(sym.ELEMENTOS_EVALUAR, yyline, yycolumn, yytext());
}

//Conjunto
{CONJUNTO_ELEMENTOS} {
    addToken("CONJUNTO", yytext(), yyline, yycolumn); 
    return new Symbol(sym.CONJUNTO, yyline, yycolumn, yytext());
}

{ID} { addToken("ID", yytext(), yyline, yycolumn); return new Symbol(sym.ID, yyline, yycolumn, yytext()); }


{NO_UNIVERSO} { addError(yytext(), "Error Léxico", yyline, yycolumn);
    return new Symbol(sym.error, yyline, yycolumn, yytext()); }

// Cualquier otro carácter desconocido se considera un error léxico
. {
    addError(yytext(), "Error Léxico", yyline, yycolumn);
    return new Symbol(sym.error, yyline, yycolumn, yytext());
}
