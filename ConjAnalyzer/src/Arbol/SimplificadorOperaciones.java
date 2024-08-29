package Arbol;

import java.util.HashSet;
import java.util.Set;
import Conjuntos.ConjuntoManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/**
 *
 * @author Keneth Lopez
 */
public class SimplificadorOperaciones {
    private ConjuntoManager conjuntoManager;
    private Map<String, List<String>> leyesAplicadasPorOperacion; 

    public SimplificadorOperaciones(ConjuntoManager conjuntoManager) {
        this.conjuntoManager = conjuntoManager;
        this.leyesAplicadasPorOperacion = new HashMap<>();
    }

    public Nodo simplificar(Nodo nodo, String nombreOperacion) {
        Set<String> estadosVisitados = new HashSet<>();
        List<String> leyesAplicadas = new ArrayList<>();
        Nodo resultadoSimplificado = simplificarRecursivo(nodo, nombreOperacion, estadosVisitados, leyesAplicadas);
        leyesAplicadasPorOperacion.put(nombreOperacion, leyesAplicadas); 
        return resultadoSimplificado;
    }

    private Nodo simplificarRecursivo(Nodo nodo, String nombreOperacion, Set<String> estadosVisitados, List<String> leyesAplicadas) {
        if (nodo instanceof NodoBinario) {
            NodoBinario operacion = (NodoBinario) nodo;

            Nodo izquierdoSimplificado = simplificarRecursivo(operacion.getIzquierdo(), nombreOperacion, estadosVisitados, leyesAplicadas);
            Nodo derechoSimplificado = simplificarRecursivo(operacion.getDerecho(), nombreOperacion, estadosVisitados, leyesAplicadas);

            NodoBinario operacionSimplificada = new NodoBinario(operacion.getOperador(), izquierdoSimplificado, derechoSimplificado);

            Nodo nodoResultado = aplicarPropiedadesBinarias(operacionSimplificada, leyesAplicadas);

            String representacionNodo = nodoResultado.mostrarContenido();
            if (!estadosVisitados.add(representacionNodo)) {
                System.out.println("Ciclo detectado, deteniendo simplificación para: " + representacionNodo);
                return nodoResultado;
            }

            if (nombreOperacion.equals("OperacionPrincipal")) {
                Set<Character> conjuntoResultado = nodoResultado.evaluar();
                conjuntoManager.guardarOperacion(nombreOperacion, representacionNodo, conjuntoResultado);
            }

            return nodoResultado;

        } else if (nodo instanceof NodoUnario) {
            NodoUnario operacion = (NodoUnario) nodo;
            Nodo operandoSimplificado = simplificarRecursivo(operacion.getOperand(), nombreOperacion, estadosVisitados, leyesAplicadas);
            NodoUnario operacionSimplificada = new NodoUnario(operacion.getOperador(), operandoSimplificado);
            Nodo nodoResultado = aplicarPropiedadesUnarias(operacionSimplificada, leyesAplicadas);
            String representacionNodo = nodoResultado.mostrarContenido();
            if (!estadosVisitados.add(representacionNodo)) {
                System.out.println("Ciclo detectado, deteniendo simplificación para: " + representacionNodo);
                return nodoResultado;
            }

            if (nombreOperacion.equals("OperacionPrincipal")) {
                Set<Character> conjuntoResultado = nodoResultado.evaluar();
                conjuntoManager.guardarOperacion(nombreOperacion, representacionNodo, conjuntoResultado);
            }

            return nodoResultado;

        }

        return nodo; // Si es un NodoConjunto, no necesita simplificación
    }
    /*
    PROPIEDADES DE UN OPERADOR (UNARIAS)
    */
    private boolean esLeyDeMorgan(NodoUnario operacion) {
        Nodo operando = operacion.getOperand();

        if (operando instanceof NodoBinario) {
            NodoBinario operandoBinario = (NodoBinario) operando;

            // ^(A ∪ B) = ^A ∩ ^B y ^(A ∩ B) = ^A ∪ ^B
            return ("U".equals(operandoBinario.getOperador()) || "&".equals(operandoBinario.getOperador()));
        }

        return false;
    }

    private boolean esDobleComplemento(NodoUnario operacion) {
        Nodo operando = operacion.getOperand();

        // ^^A = A
        return (operando instanceof NodoUnario && "^".equals(((NodoUnario) operando).getOperador()));
    }
    
    private Nodo aplicarPropiedadesUnarias(NodoUnario operacion, List<String> leyesAplicadas) {
        Nodo operando = operacion.getOperand();

        // Ley del Doble Complemento: ^^A = A
        if (esDobleComplemento(operacion)) {
            System.out.println("Aplicando Ley del Doble Complemento: ^^" + operando.mostrarContenido() + " = " + ((NodoUnario) operando).getOperand().mostrarContenido());
            leyesAplicadas.add("ley del doble complemento");
            return ((NodoUnario) operando).getOperand();
        }

        // Ley de DeMorgan: ^(A ∪ B) = ^A ∩ ^B y ^(A ∩ B) = ^A ∪ ^B
        if (esLeyDeMorgan(operacion)) {
            NodoBinario operandoBinario = (NodoBinario) operando;
            String nuevoOperador = "U".equals(operandoBinario.getOperador()) ? "&" : "U";
            System.out.println("Aplicando Ley de DeMorgan: ^(" + operando.mostrarContenido() + ") = " + nuevoOperador);
            leyesAplicadas.add("leyes de DeMorgan");
            return new NodoBinario(nuevoOperador,
                    new NodoUnario("^", operandoBinario.getIzquierdo()),
                    new NodoUnario("^", operandoBinario.getDerecho()));
        }

        return operacion; // Devolver la operación si no se pudo simplificar más
    }

     /*
    PROPIEDADES DE DOS OPERADORES (BINARIAS)
    */
    private boolean esPropiedadDeAbsorcion(Nodo posibleAbsorbedor, Nodo posibleAbsorcion) {
        if (posibleAbsorcion instanceof NodoBinario) {
            NodoBinario nodoBinario = (NodoBinario) posibleAbsorcion;
            // Verifica A ∪ (A ∩ B) = A o A ∪ (B ∩ A) = A
            if ("&".equals(nodoBinario.getOperador())) {
                return (nodoBinario.getIzquierdo().equals(posibleAbsorbedor) || nodoBinario.getDerecho().equals(posibleAbsorbedor));
            }
            // Verifica A ∩ (A ∪ B) = A o A ∩ (B ∪ A) = A
            if ("U".equals(nodoBinario.getOperador())) {
                return (nodoBinario.getIzquierdo().equals(posibleAbsorbedor) || nodoBinario.getDerecho().equals(posibleAbsorbedor));
            }
        }
        return false;
    }

    private boolean esPropiedadConmutativa(Nodo nodo1, Nodo nodo2) {
        return nodo1.equals(nodo2);
    }
    
    private boolean esPropiedadAsociativa(NodoBinario operacion) {
        Nodo izquierdo = operacion.getIzquierdo();
        Nodo derecho = operacion.getDerecho();

        // A ∪ (B ∪ C) = (A ∪ B) ∪ C
        if ("U".equals(operacion.getOperador()) && derecho instanceof NodoBinario) {
            NodoBinario derechoOperacion = (NodoBinario) derecho;
            return "U".equals(derechoOperacion.getOperador());
        }

        // A ∩ (B ∩ C) = (A ∩ B) ∩ C
        if ("&".equals(operacion.getOperador()) && derecho instanceof NodoBinario) {
            NodoBinario derechoOperacion = (NodoBinario) derecho;
            return "&".equals(derechoOperacion.getOperador());
        }

        return false;
    }
    
    private boolean esPropiedadDistributiva(NodoBinario operacion) {
        Nodo izquierdo = operacion.getIzquierdo();
        Nodo derecho = operacion.getDerecho();

        // A ∪ (B ∩ C) = (A ∪ B) ∩ (A ∪ C)
        if ("U".equals(operacion.getOperador()) && derecho instanceof NodoBinario) {
            NodoBinario derechoOperacion = (NodoBinario) derecho;
            return "&".equals(derechoOperacion.getOperador());
        }

        // A ∩ (B ∪ C) = (A ∩ B) ∪ (A ∩ C)
        if ("&".equals(operacion.getOperador()) && derecho instanceof NodoBinario) {
            NodoBinario derechoOperacion = (NodoBinario) derecho;
            return "U".equals(derechoOperacion.getOperador());
        }

        return false;
    }
    

private Nodo aplicarPropiedadesBinarias(NodoBinario operacion, List<String> leyesAplicadas) {
    Nodo izquierdo = operacion.getIzquierdo();
    Nodo derecho = operacion.getDerecho();

    switch (operacion.getOperador()) {
        case "U":
            // Absorción: A ∪ (A ∩ B) = A, (A ∩ B) ∪ A = A
            if (esPropiedadDeAbsorcion(izquierdo, derecho)) {
                System.out.println("Aplicando Propiedad de Absorción: " + izquierdo.mostrarContenido() + " U " + derecho.mostrarContenido() + " = " + izquierdo.mostrarContenido());
                leyesAplicadas.add("propiedad de absorción");
                return izquierdo;
            } else if (esPropiedadDeAbsorcion(derecho, izquierdo)) {
                System.out.println("Aplicando Propiedad de Absorción: " + derecho.mostrarContenido() + " U " + izquierdo.mostrarContenido() + " = " + derecho.mostrarContenido());
                leyesAplicadas.add("propiedad de absorción");
                return derecho;
            }

            // Propiedad Conmutativa: A ∪ B = B ∪ A
            if (esPropiedadConmutativa(izquierdo, derecho)) {
                System.out.println("Aplicando Propiedad Conmutativa: " + izquierdo.mostrarContenido() + " U " + derecho.mostrarContenido());
                leyesAplicadas.add("propiedad conmutativa");
                if (izquierdo.toString().compareTo(derecho.toString()) > 0) {
                    return new NodoBinario("U", derecho, izquierdo);
                }
            }

            // Propiedad Idempotente: A ∪ A = A
            if (izquierdo.equals(derecho)) {
                System.out.println("Aplicando Propiedad Idempotente: " + izquierdo.mostrarContenido() + " U " + derecho.mostrarContenido() + " = " + izquierdo.mostrarContenido());
                leyesAplicadas.add("propiedad idempotente");
                return izquierdo;
            }

            // Propiedad Asociativa: A ∪ (B ∪ C) = (A ∪ B) ∪ C
            if (esPropiedadAsociativa(operacion)) {
                System.out.println("Aplicando Propiedad Asociativa: " + izquierdo.mostrarContenido() + " U (" + derecho.mostrarContenido() + ")");
                leyesAplicadas.add("propiedad asociativa");
                NodoBinario derechoOperacion = (NodoBinario) derecho;
                return new NodoBinario("U", new NodoBinario("U", izquierdo, derechoOperacion.getIzquierdo()), derechoOperacion.getDerecho());
            }

            // Propiedad Distributiva: A ∪ (B ∩ C) = (A ∪ B) ∩ (A ∪ C)
            if (esPropiedadDistributiva(operacion)) {
                System.out.println("Aplicando Propiedad Distributiva: " + izquierdo.mostrarContenido() + " U (" + derecho.mostrarContenido() + ")");
                leyesAplicadas.add("propiedad distributiva");
                NodoBinario derechoOperacion = (NodoBinario) derecho;
                return new NodoBinario("&", 
                        new NodoBinario("U", izquierdo, derechoOperacion.getIzquierdo()), 
                        new NodoBinario("U", izquierdo, derechoOperacion.getDerecho()));
            }
            break;

        case "&":
            // Absorción: A ∩ (A ∪ B) = A, (A ∪ B) ∩ A = A
            if (esPropiedadDeAbsorcion(izquierdo, derecho)) {
                System.out.println("Aplicando Propiedad de Absorción: " + izquierdo.mostrarContenido() + " ∩ " + derecho.mostrarContenido() + " = " + izquierdo.mostrarContenido());
                leyesAplicadas.add("propiedad de absorción");
                return izquierdo;
            } else if (esPropiedadDeAbsorcion(derecho, izquierdo)) {
                System.out.println("Aplicando Propiedad de Absorción: " + derecho.mostrarContenido() + " ∩ " + izquierdo.mostrarContenido() + " = " + derecho.mostrarContenido());
                leyesAplicadas.add("propiedad de absorción");
                return derecho;
            }

            // Propiedad Conmutativa: A ∩ B = B ∩ A
            if (esPropiedadConmutativa(izquierdo, derecho)) {
                System.out.println("Aplicando Propiedad Conmutativa: " + izquierdo.mostrarContenido() + " ∩ " + derecho.mostrarContenido());
                leyesAplicadas.add("propiedad conmutativa");
                if (izquierdo.toString().compareTo(derecho.toString()) > 0) {
                    return new NodoBinario("&", derecho, izquierdo);
                }
            }

            // Propiedad Idempotente: A ∩ A = A
            if (izquierdo.equals(derecho)) {
                System.out.println("Aplicando Propiedad Idempotente: " + izquierdo.mostrarContenido() + " ∩ " + derecho.mostrarContenido() + " = " + izquierdo.mostrarContenido());
                leyesAplicadas.add("propiedad idempotente");
                return izquierdo;
            }

            // Propiedad Asociativa: A ∩ (B ∩ C) = (A ∩ B) ∩ C
            if (esPropiedadAsociativa(operacion)) {
                System.out.println("Aplicando Propiedad Asociativa: " + izquierdo.mostrarContenido() + " ∩ (" + derecho.mostrarContenido() + ")");
                leyesAplicadas.add("propiedad asociativa");
                NodoBinario derechoOperacion = (NodoBinario) derecho;
                return new NodoBinario("&", new NodoBinario("&", izquierdo, derechoOperacion.getIzquierdo()), derechoOperacion.getDerecho());
            }

            // Propiedad Distributiva: A ∩ (B ∪ C) = (A ∩ B) ∪ (A ∩ C)
            if (esPropiedadDistributiva(operacion)) {
                System.out.println("Aplicando Propiedad Distributiva: " + izquierdo.mostrarContenido() + " ∩ (" + derecho.mostrarContenido() + ")");
                leyesAplicadas.add("propiedad distributiva");
                NodoBinario derechoOperacion = (NodoBinario) derecho;
                return new NodoBinario("U", 
                        new NodoBinario("&", izquierdo, derechoOperacion.getIzquierdo()), 
                        new NodoBinario("&", izquierdo, derechoOperacion.getDerecho()));
            }
            break;
    }

    return operacion; // Devolver la operación si no se pudo simplificar más
}


    public void generarJSON(String rutaArchivo) {
        JSONObject json = new JSONObject();

        for (Map.Entry<String, List<String>> entrada : leyesAplicadasPorOperacion.entrySet()) {
            String nombreOperacion = entrada.getKey();
            List<String> leyes = entrada.getValue();

            if (leyes.isEmpty()) {
                json.put(nombreOperacion, "No se puede simplificar la operación");
            } else {
                JSONObject detallesOperacion = new JSONObject();
                detallesOperacion.put("leyes", leyes);

                String notacionOperacion = conjuntoManager.obtenerNotacionOperacion(nombreOperacion);
                if (notacionOperacion == null) {
                    System.err.println("Error: La notación de la operación '" + nombreOperacion + "' es nula.");
                    notacionOperacion = "Notación no disponible";
                }

                detallesOperacion.put("conjunto simplificado", notacionOperacion);
                json.put(nombreOperacion, detallesOperacion);
            }
        }

        try (FileWriter file = new FileWriter(rutaArchivo)) {
            file.write(json.toString(4)); // Indentado para mejor legibilidad
            System.out.println("Archivo JSON generado exitosamente en: " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
