# OCL1_Proyecto1_202100106

## ConjAnalyzer – Analizador de Conjuntos con Visualización Gráfica

**ConjAnalyzer** es una aplicación desarrollada en Java que permite analizar expresiones de conjuntos mediante análisis léxico y sintáctico, aplicar leyes de simplificación, y representar visualmente los resultados a través de **diagramas de Venn**.

Está diseñada con una interfaz gráfica intuitiva que facilita la escritura de código, ejecución del análisis y revisión de resultados mediante reportes generados automáticamente.

---

## Funcionalidades Principales

- Análisis léxico y sintáctico de expresiones de conjuntos.
- Reporte de tokens, errores léxicos y errores sintácticos en formato HTML.
- Visualización gráfica de operaciones con conjuntos mediante **diagramas de Venn**.
- Aplicación de leyes de la teoría de conjuntos para simplificar expresiones.
- Interfaz gráfica con opciones de navegación y control.

---

## Estructura del Proyecto

```plain-text
Analizadores/
├─ Lexer.java               # Lógica del análisis léxico
├─ Parser.java              # Reglas del análisis sintáctico
├─ sym.java                # Símbolos generados por CUP

Arbol/
├─ ArbolExpresion.java     # Árbol general de expresiones
├─ Nodo.java               # Nodo base del árbol
├─ NodoBinario.java        # Nodo para operaciones binarias
├─ NodoConjunto.java       # Nodo para valores de conjuntos
├─ NodoUnario.java         # Nodo para operaciones unarias
├─ SimplificadorOperaciones.java  # Simplificación usando leyes

Componentes/
├─ LexicalError.java       # Clase para representar errores léxicos
├─ SyntaxError.java        # Clase para representar errores sintácticos
├─ Token.java              # Representación de tokens

Conjuntos/
├─ ConjuntoManager.java    # Manejo de conjuntos
├─ GestorOperaciones.java  # Ejecución de operaciones entre conjuntos

Documentacion/
├─ Manual Tecnico
├─ Gramatica.txt
├─ Manual de Usuario

Generador/
├─ GeneradorLexer.java     # Generador del analizador léxico
├─ GeneradorParser.java    # Generador del analizador sintáctico

Interfaz/
├─ inicio.form             # Diseño visual de la interfaz
├─ inicio.java             # Lógica de la ventana principal
├─ OutputManager.java      # Manejo de la salida y consola
├─ VennDiagramPanel.java   # Dibujado del diagrama de Venn

Language/
├─ Lexer.jflex             # Definición léxica (JFlex)
├─ Parser.cup              # Definición sintáctica (CUP)

Leyes/
├─ Absorcion.java
├─ Asociativa.java
├─ Conmutativa.java
├─ DeMorgan.java
├─ Distributiva.java
├─ DobleComplemento.java
├─ Idempotente.java
├─ Ley.java                # Clase base para representar leyes

Pruebas/
Salidas/

conjanalyzer/
├─ conjanalyzer.java       # Clase de ejecución principal

```

---

## Requisitos del Sistema

- **Sistema Operativo:** Windows, Linux o macOS
- **Java Runtime Environment (JRE):** 8 o superior
- **Espacio en Disco:** 50 MB libres
- **Memoria RAM:** Mínimo 512 MB

---

## Interfaz de Usuario

La interfaz gráfica está compuesta por:

- **Menú Principal:** Archivo (nuevo, guardar), ejecutar análisis, reportes.
- **Área de Entrada:** Texto editable para ingresar código de conjuntos.
- **Consola:** Muestra resultados, errores y mensajes del sistema.
- **Panel de Gráficas:** Muestra los diagramas de Venn de las operaciones realizadas.
- **Botones Anterior / Siguiente:** Navegación entre operaciones visualizadas.

---

## Reportes Generados

- **Tokens HTML:** Detalle de los tokens reconocidos.
- **Errores Léxicos HTML**
- **Errores Sintácticos HTML**
- **Resultado en consola**
- **Visualización de operaciones mediante diagramas de Venn**

---

## Tecnologías Utilizadas

- **Java 8+**
- **JFlex** (para el lexer)
- **CUP** (para el parser)
- **Swing/AWT** (interfaz gráfica)
- **HTML/CSS** (para los reportes generados)

---

## Cómo Ejecutar el Proyecto

1. Asegúrate de tener JDK 8 o superior.
2. Compila todos los archivos `.java` dentro del proyecto.
3. Ejecuta el archivo `conjanalyzer.java` como clase principal.
4. Interactúa con la interfaz, escribe o abre un archivo `.ca` y selecciona “Ejecutar > Análisis”.
