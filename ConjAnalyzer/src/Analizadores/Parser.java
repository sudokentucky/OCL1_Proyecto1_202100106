
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package Analizadores;

import java_cup.runtime.*;
import java.util.*;
import Conjuntos.ConjuntoManager;
import Arbol.*;
import Arbol.SimplificadorOperaciones;
import Componentes.SyntaxError;
import Interfaz.OutputManager;
import java.util.Stack;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\024\000\002\002\004\000\002\002\005\000\002\003" +
    "\004\000\002\003\002\000\002\004\003\000\002\004\003" +
    "\000\002\004\003\000\002\004\003\000\002\004\004\000" +
    "\002\005\010\000\002\011\003\000\002\011\003\000\002" +
    "\012\005\000\002\006\010\000\002\007\005\000\002\007" +
    "\005\000\002\007\005\000\002\007\004\000\002\007\005" +
    "\000\002\010\011" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\063\000\004\004\005\001\002\000\004\002\065\001" +
    "\002\000\016\003\015\005\ufffe\022\010\024\006\025\021" +
    "\026\011\001\002\000\004\006\060\001\002\000\016\003" +
    "\015\005\ufffe\022\010\024\006\025\021\026\011\001\002" +
    "\000\022\003\ufff6\005\ufff6\010\ufff6\020\055\022\ufff6\024" +
    "\ufff6\025\ufff6\026\ufff6\001\002\000\004\015\047\001\002" +
    "\000\016\003\ufffb\005\ufffb\022\ufffb\024\ufffb\025\ufffb\026" +
    "\ufffb\001\002\000\016\003\ufffd\005\ufffd\022\ufffd\024\ufffd" +
    "\025\ufffd\026\ufffd\001\002\000\016\003\ufffc\005\ufffc\022" +
    "\ufffc\024\ufffc\025\ufffc\026\ufffc\001\002\000\004\010\046" +
    "\001\002\000\004\005\045\001\002\000\020\003\ufff7\005" +
    "\ufff7\010\ufff7\022\ufff7\024\ufff7\025\ufff7\026\ufff7\001\002" +
    "\000\016\003\ufffa\005\ufffa\022\ufffa\024\ufffa\025\ufffa\026" +
    "\ufffa\001\002\000\004\006\022\001\002\000\004\021\023" +
    "\001\002\000\004\007\024\001\002\000\014\004\031\011" +
    "\030\012\026\013\027\014\032\001\002\000\004\010\044" +
    "\001\002\000\014\004\031\011\030\012\026\013\027\014" +
    "\032\001\002\000\014\004\031\011\030\012\026\013\027" +
    "\014\032\001\002\000\014\004\031\011\030\012\026\013" +
    "\027\014\032\001\002\000\004\021\034\001\002\000\014" +
    "\004\031\011\030\012\026\013\027\014\032\001\002\000" +
    "\016\004\ufff0\010\ufff0\011\ufff0\012\ufff0\013\ufff0\014\ufff0" +
    "\001\002\000\004\005\035\001\002\000\016\004\uffef\010" +
    "\uffef\011\uffef\012\uffef\013\uffef\014\uffef\001\002\000\014" +
    "\004\031\011\030\012\026\013\027\014\032\001\002\000" +
    "\016\004\ufff2\010\ufff2\011\ufff2\012\ufff2\013\ufff2\014\ufff2" +
    "\001\002\000\014\004\031\011\030\012\026\013\027\014" +
    "\032\001\002\000\016\004\ufff3\010\ufff3\011\ufff3\012\ufff3" +
    "\013\ufff3\014\ufff3\001\002\000\014\004\031\011\030\012" +
    "\026\013\027\014\032\001\002\000\016\004\ufff1\010\ufff1" +
    "\011\ufff1\012\ufff1\013\ufff1\014\ufff1\001\002\000\016\003" +
    "\ufff4\005\ufff4\022\ufff4\024\ufff4\025\ufff4\026\ufff4\001\002" +
    "\000\004\002\000\001\002\000\016\003\ufff9\005\ufff9\022" +
    "\ufff9\024\ufff9\025\ufff9\026\ufff9\001\002\000\004\023\050" +
    "\001\002\000\004\017\051\001\002\000\004\021\052\001" +
    "\002\000\004\016\053\001\002\000\004\010\054\001\002" +
    "\000\016\003\uffee\005\uffee\022\uffee\024\uffee\025\uffee\026" +
    "\uffee\001\002\000\004\022\056\001\002\000\020\003\ufff5" +
    "\005\ufff5\010\ufff5\022\ufff5\024\ufff5\025\ufff5\026\ufff5\001" +
    "\002\000\004\005\uffff\001\002\000\004\021\061\001\002" +
    "\000\004\007\062\001\002\000\004\022\010\001\002\000" +
    "\004\010\064\001\002\000\016\003\ufff8\005\ufff8\022\ufff8" +
    "\024\ufff8\025\ufff8\026\ufff8\001\002\000\004\002\001\001" +
    "\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\063\000\004\002\003\001\001\000\002\001\001\000" +
    "\020\003\015\004\006\005\012\006\013\010\011\011\017" +
    "\012\016\001\001\000\002\001\001\000\020\003\056\004" +
    "\006\005\012\006\013\010\011\011\017\012\016\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\004\007\024" +
    "\001\001\000\002\001\001\000\004\007\041\001\001\000" +
    "\004\007\037\001\001\000\004\007\035\001\001\000\002" +
    "\001\001\000\004\007\032\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\007\036\001\001" +
    "\000\002\001\001\000\004\007\040\001\001\000\002\001" +
    "\001\000\004\007\042\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\006\011\062\012\016\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



        private List<SyntaxError> syntaxErrors = new ArrayList<>();
        private OutputManager outputManager;
        private ConjuntoManager conjuntoManager;
        private SimplificadorOperaciones simplificador;
        private ArbolPrefijo arbolPrefijo;

        public Parser(AnalizadorLexico lexer, OutputManager outputManager, ConjuntoManager conjuntoManager, SimplificadorOperaciones simplificador, ArbolPrefijo arbolPrefijo) {
            super(lexer);
            this.outputManager = outputManager;
            this.conjuntoManager = conjuntoManager;  
            this.simplificador = simplificador;
            this.arbolPrefijo = arbolPrefijo; 
            this.init_actions(); 
        }

   public void syntax_error(Symbol s) {
        // Obtener los IDs de los tokens esperados
        List<Integer> expected = expected_token_ids();
        StringBuilder expectedTokens = new StringBuilder();
        for (int id : expected) {
            expectedTokens.append(symbol_name_from_id(id)).append(" ");
        }

        // Determinar el token inesperado
        String unexpectedToken = (s != null && s.value != null) ? s.value.toString() : "Símbolo Desconocido";

        // Construir el comentario con los tokens esperados
        String comentario = "Se esperaba uno de los siguientes tokens: " + expectedTokens.toString().trim() + ".";

        // Construir el mensaje para la salida de error
        String message = "Error de Sintaxis en línea " + s.left + ", columna " + s.right + ". " +
                         "Token inesperado: '" + unexpectedToken + "'. " + comentario;

        // Imprimir el mensaje en la consola de errores
        System.err.println(message);

        // Agregar el error a la lista con el nuevo comentario
        syntaxErrors.add(new SyntaxError(s.left, s.right, unexpectedToken, comentario));

        // Agregar mensaje de error al OutputManager
        outputManager.addErrorOutput(message);
    }

    public void unrecovered_syntax_error(Symbol s) {
        String message = "Unrecovered Syntax Error at line " + s.left + ", column " + s.right + ": " + s.value;
        outputManager.addErrorOutput(message);
        syntaxErrors.add(new SyntaxError(s.left, s.right, s.value, "Unrecovered Syntax Error")); 
    }

    public Set<Character> obtenerConjunto(String nombre) {
        Set<Character> conjunto = conjuntoManager.obtenerConjunto(nombre);
        if (conjunto == null) {
            String message = "Error: El conjunto '" + nombre + "' no está definido.";
            outputManager.addOutput(message);
            syntaxErrors.add(new SyntaxError(0, 0, nombre, "Conjunto No Definido"));
            return Collections.emptySet(); // Retornar conjunto vacío como medida de recuperación
        }
        return conjunto;
    }

    // Método para obtener la lista de errores sintácticos
    public List<SyntaxError> getSyntaxErrors() {
        return syntaxErrors;
    }

    public String symbol_name_from_id(int id) {
        switch(id) {
            case sym.PUNTOYCOMA: return "PUNTOYCOMA";
            case sym.DIFERENCIA: return "DIFERENCIA";
            case sym.DOSPUNTOS: return "DOSPUNTOS";
            case sym.CONJUNTO: return "CONJUNTO";
            case sym.HASTA: return "HASTA";
            case sym.INTERSECCION: return "INTERSECCION";
            case sym.FLECHA: return "FLECHA";
            case sym.OPERA: return "OPERA";
            case sym.ID: return "ID";
            case sym.COMA: return "COMA";
            case sym.EOF: return "EOF";
            case sym.CONJ: return "CONJ";
            case sym.PAR_IZQ: return "PAR_IZQ";
            case sym.UNION: return "UNION";
            case sym.error: return "error";
            case sym.PAR_DER: return "PAR_DER";
            case sym.COMPLEMENTO: return "COMPLEMENTO";
            case sym.LLAVE_IZQ: return "LLAVE_IZQ";
            case sym.EVALUAR: return "EVALUAR";
            case sym.LLAVE_DER: return "LLAVE_DER";
            case sym.ELEMENTOS_EVALUAR: return "ELEMENTOS_EVALUAR";
            default: return "Símbolo Desconocido";
        }
    }

   // Implementación de validate_expected_symbol para verificar si un ID de símbolo es válido
    protected boolean validate_expected_symbol(int id) {
        switch(id) {
            case sym.PUNTOYCOMA:
            case sym.DIFERENCIA:
            case sym.DOSPUNTOS:
            case sym.CONJUNTO:
            case sym.HASTA:
            case sym.INTERSECCION:
            case sym.FLECHA:
            case sym.ELEMENTOS_EVALUAR:
            case sym.OPERA:
            case sym.ID:
            case sym.COMA:
            case sym.EOF:
            case sym.CONJ:
            case sym.PAR_IZQ:
            case sym.UNION:
            case sym.error:
            case sym.PAR_DER:
            case sym.COMPLEMENTO:
            case sym.LLAVE_IZQ:
            case sym.EVALUAR:
            case sym.LLAVE_DER:
                return true;
            default:
                return false;
        }
    }

    // Método para obtener los IDs de los tokens esperados
    public List<Integer> expected_token_ids() {
        List<Integer> expectedTokens = new LinkedList<>();
        int state = ((Symbol) stack.peek()).parse_state;
        short[] actions = action_tab[state];

        for (int i = 0; i < actions.length; i += 2) {
            if (actions[i] != -1 && validate_expected_symbol(actions[i])) {
                expectedTokens.add((int) actions[i]);
            }
        }
        return expectedTokens;
    }



/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Parser$actions {
  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action_part00000000(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= Programa EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // Programa ::= LLAVE_IZQ Sentencias LLAVE_DER 
            {
              Object RESULT =null;
		int llzleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int llzright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object llz = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int llrleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int llrright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object llr = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 
    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Programa",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // Sentencias ::= Sentencia Sentencias 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Sentencias",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // Sentencias ::= 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Sentencias",1, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // Sentencia ::= DefinicionConjunto 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Sentencia",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // Sentencia ::= DefinicionOperacion 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Sentencia",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // Sentencia ::= Evaluacion 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Sentencia",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // Sentencia ::= Conjunto 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Sentencia",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // Sentencia ::= error PUNTOYCOMA 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		 
                List<Integer> expected = expected_token_ids();
                StringBuilder expectedTokens = new StringBuilder();
                for (int id : expected) {
                    expectedTokens.append(symbol_name_from_id(id)).append(" ");
                }
              
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Sentencia",2, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // DefinicionConjunto ::= CONJ DOSPUNTOS ID FLECHA Conjunto PUNTOYCOMA 
            {
              Object RESULT =null;
		int conjleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int conjright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		Object conj = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int dospleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int dospright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		Object dosp = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int flechaleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int flecharight = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object flecha = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int conjSetleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int conjSetright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Set<Character> conjSet = (Set<Character>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int puntoleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int puntoright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object punto = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
        conjuntoManager.definirConjunto(id, conjSet);
        System.out.println("Definido conjunto: " + id);
        System.out.println("Conjunto contenido: " + conjSet);
    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("DefinicionConjunto",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // Conjunto ::= Rango 
            {
              Set<Character> RESULT =null;
		int rangosleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int rangosright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Set<Character> rangos = (Set<Character>)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
        RESULT = rangos;
    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Conjunto",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // Conjunto ::= CONJUNTO 
            {
              Set<Character> RESULT =null;
		int conjListleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int conjListright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String conjList = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
        RESULT = conjuntoManager.generarConjuntoDesdeLista(conjList);
        System.out.println("Generando conjunto desde lista: " + conjList);
        System.out.println("Conjunto generado: " + RESULT);
    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Conjunto",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // Rango ::= CONJUNTO HASTA CONJUNTO 
            {
              Set<Character> RESULT =null;
		int conj1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int conj1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		String conj1 = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int hastaleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int hastaright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object hasta = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int conj2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int conj2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		String conj2 = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
        RESULT = conjuntoManager.generarConjuntoDesdeRango(conj1, conj2);
        System.out.println("Generando conjunto desde rango: " + conj1 + " hasta " + conj2);
        System.out.println("Conjunto generado: " + RESULT);
    
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Rango",8, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // DefinicionOperacion ::= OPERA DOSPUNTOS ID FLECHA OperacionTokens PUNTOYCOMA 
            {
              Object RESULT =null;
		int operaleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).left;
		int operaright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)).right;
		Object opera = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-5)).value;
		int dospleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int dospright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		Object dosp = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int flechaleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int flecharight = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object flecha = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int tokensleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int tokensright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		List<String> tokens = (List<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int puntoleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int puntoright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object punto = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
    try {
        Nodo arbol = arbolPrefijo.construirArbol(tokens);
        Nodo arbolSimplificado = simplificador.simplificar(arbol, id);
        Set<Character> resultado = arbolSimplificado.evaluar();
        conjuntoManager.guardarOperacion(id, arbolSimplificado.mostrarContenido(), resultado);
        System.out.println("Operación '" + id + "' simplificada y guardada.");
        System.out.println("Árbol de operación simplificada: " + arbolSimplificado);
        System.out.println("Resultado de la operación: " + resultado);
        System.out.println("------------------------------------");
        System.out.println(conjuntoManager.getOperaciones());

    } catch (Exception e) {
        String message = "Error al definir operación '" + id + "': " + e.getMessage();
        System.err.println(message);
        outputManager.addErrorOutput(message);
        syntaxErrors.add(new SyntaxError(operaleft, puntoright, e.getMessage(), "Error en la Definición de Operación"));
    }

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("DefinicionOperacion",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // OperacionTokens ::= DIFERENCIA OperacionTokens OperacionTokens 
            {
              List<String> RESULT =null;
		int difleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int difright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object dif = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int op1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int op1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		List<String> op1 = (List<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int op2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int op2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		List<String> op2 = (List<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
    List<String> tokens = new ArrayList<>();
    tokens.add("-");  // Primero se añade el operador para notación prefija
    tokens.addAll(op1); // Luego el primer operando
    tokens.addAll(op2); // Luego el segundo operando
    RESULT = tokens;
    System.out.println("Token de diferencia añadido: " + tokens);

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("OperacionTokens",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // OperacionTokens ::= UNION OperacionTokens OperacionTokens 
            {
              List<String> RESULT =null;
		int unionleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int unionright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object union = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int op1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int op1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		List<String> op1 = (List<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int op2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int op2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		List<String> op2 = (List<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
    List<String> tokens = new ArrayList<>();
    tokens.add("U");  // Primero el operador
    tokens.addAll(op1); // Luego el primer operando
    tokens.addAll(op2); // Luego el segundo operando
    RESULT = tokens;
    System.out.println("Token de unión añadido: " + tokens);

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("OperacionTokens",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // OperacionTokens ::= INTERSECCION OperacionTokens OperacionTokens 
            {
              List<String> RESULT =null;
		int interleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int interright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object inter = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int op1left = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int op1right = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		List<String> op1 = (List<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int op2left = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int op2right = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		List<String> op2 = (List<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
    List<String> tokens = new ArrayList<>();
    tokens.add("&");  // Primero el operador
    tokens.addAll(op1); // Luego el primer operando
    tokens.addAll(op2); // Luego el segundo operando
    RESULT = tokens;
    System.out.println("Token de intersección añadido: " + tokens);

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("OperacionTokens",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // OperacionTokens ::= COMPLEMENTO OperacionTokens 
            {
              List<String> RESULT =null;
		int complleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int complright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object compl = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		int opleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int opright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		List<String> op = (List<String>)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
    List<String> tokens = new ArrayList<>();
    tokens.add("^");  // Primero el operador
    tokens.addAll(op); // Luego el operando
    RESULT = tokens;
    System.out.println("Token de complemento añadido: " + tokens);

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("OperacionTokens",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // OperacionTokens ::= LLAVE_IZQ ID LLAVE_DER 
            {
              List<String> RESULT =null;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		
    List<String> tokens = new ArrayList<>();
    tokens.add(id);  // Solo el identificador del conjunto
    RESULT = tokens;
    System.out.println("Identificador de conjunto añadido: " + tokens);

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("OperacionTokens",5, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // Evaluacion ::= EVALUAR PAR_IZQ ELEMENTOS_EVALUAR COMA ID PAR_DER PUNTOYCOMA 
            {
              Object RESULT =null;
		int elementosleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).left;
		int elementosright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)).right;
		String elementos = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-4)).value;
		int idleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int idright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		String id = (String)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int puntoleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int puntoright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object punto = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		
    // Extraer los elementos de ELEMENTOS_EVALUAR (vienen en la forma "{a, b, c}")
    String elementosStr = elementos;
    elementosStr = elementosStr.substring(1, elementosStr.length() - 1); 
    String[] elementosArray = elementosStr.split(",\\s*"); 
    Set<Character> resultadoOperacion = conjuntoManager.obtenerResultadoOperacion(id);
    // Verificar si la operación existe
    if (resultadoOperacion == null) {
        System.err.println("Operación no encontrada: " + id);
        outputManager.addErrorOutput("Operación no encontrada: " + id);
        syntaxErrors.add(new SyntaxError(elementosleft, idright, "Operación no encontrada: " + id, "Error en la Evaluación"));
    }
    // Evaluar cada elemento contra la operación
    outputManager.addOutput("===============");
    outputManager.addOutput("Evaluar: " + id);
    outputManager.addOutput("===============");

    for (String elemento : elementosArray) {
        elemento = elemento.trim();  // Remueve cualquier espacio alrededor del elemento
        if (resultadoOperacion.contains(elemento.charAt(0))) {
            outputManager.addOutput(elemento + " -> exitoso");
        } else {
            outputManager.addOutput(elemento + " -> fallo");
        }
    }
    outputManager.addOutput("===============");

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("Evaluacion",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-6)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
              return CUP$Parser$do_action_part00000000(
                               CUP$Parser$act_num,
                               CUP$Parser$parser,
                               CUP$Parser$stack,
                               CUP$Parser$top);
    }
}

}
