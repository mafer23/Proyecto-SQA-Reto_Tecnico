## Taller SQA – Selector de Fecha (Datepicker)

Este proyecto corresponde a un taller práctico de automatización de pruebas UI para el componente Datepicker de jQueryUI. Su objetivo es reforzar conocimientos en metodología SQA, BDD y el patrón Screenplay, permitiendo a los participantes automatizar la selección de fechas en un formulario web.

Comenzando 🚀

Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para desarrollo y pruebas.

Mira Despliegue para conocer cómo ejecutar el proyecto en tu entorno.

## Pre-requisitos 📋

```bash
- Java 11
- Gradle 
- IntelliJ IDEA u otro IDE compatible
- Navegadores: Chrome, Edge o Firefox
- Git

```

```bash
java -version
gradle -v

```
## Instalación 🔧

```
git clone https://github.com/tu-usuario/automatizacion-selector-fechas.git
cd automatizacion-selector-fechas

```
Instala dependencias con Gradle:

```
gradle build
```
## Ejecutando las pruebas ⚙️

Ejecuta las pruebas automatizadas con Serenity BDD:

```
gradle clean test aggregate
```

El reporte HTML se generará en target/site/serenity/index.html.

## Analice las pruebas end-to-end 🔩

Las pruebas verifican:

- Que el calendario emergente se muestre al hacer clic en el campo de fecha

- Selección de fecha del mes actual y de un mes diferente

- Persistencia y formato correcto de la fecha seleccionada

## Construido con 🛠️

- Serenity BDD - Framework de automatización
-Selenium WebDriver - Interacción con UI
- Cucumber - BDD / Gherkin
- Gradle
 - Gestión de dependencias y ejecución de pruebas
-  GitHub 
- Control de versiones
- IntelliJ IDEA - IDE recomendado

## Autores ✒️
Maria Fernanda Palencia Cáceres
