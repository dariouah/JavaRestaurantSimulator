
# JavaRestaurantSimulator

## Descripción
JavaRestaurantSimulator es un simulador de restaurante interactivo desarrollado en Java. Este proyecto te permite explorar la gestión de un restaurante, abarcando desde la atención al cliente hasta la administración del inventario. Destaca por implementar mecanismos de control de concurrencia, asegurando un manejo eficaz de múltiples hilos de ejecución.

## Ejecución del Proyecto
Para trabajar con este proyecto, necesitarás:

- **Java Development Kit (JDK):** Esencial para compilar y ejecutar el código fuente. Puedes descargarlo desde [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) o [OpenJDK](https://jdk.java.net/).
- **Un IDE recomendado:** Aunque puedes compilar y ejecutar el proyecto desde la línea de comandos, usar un IDE como [Eclipse](https://www.eclipse.org/downloads/), [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), o [NetBeans](https://netbeans.apache.org/download/index.html) facilitará significativamente el desarrollo, la compilación y la ejecución.

### Compilar y Ejecutar desde la Línea de Comandos
Una vez descargado y descomprimido el código fuente, puedes seguir estos pasos para compilar y ejecutar el programa:

**Para compilar el programa:**
```bash
cd <ruta al directorio del proyecto>/src/main/java
javac -d <ruta al directorio del proyecto>/target/classes <ruta al directorio del proyecto>/com/mycompany/proyecto/Proyecto.java
```

**Para ejecutar el programa una vez compilado:**
```bash
cd <ruta al directorio del proyecto>/target/classes
java com.mycompany.proyecto.Proyecto
```

Recuerda reemplazar `<ruta al directorio del proyecto>` según el directorio en el que decidas alojar el proyecto.

### Compilar y Ejecutar a través de IDEs con Soporte para Maven
**Si deseas compilar y ejecutar este proyecto desde un Entorno de Desarrollo Integrado (IDE)**, el archivo pom.xml facilita este proceso. Este archivo permite que el IDE detecte automáticamente el proyecto **como un proyecto Maven**, lo que simplifica significativamente la gestión de dependencias, la configuración del entorno de desarrollo, y las tareas de construcción y ejecución. Las aplicaciones compatibles con proyectos Maven, como **Eclipse, IntelliJ IDEA y NetBeans, reconocerán el archivo pom.xml** y configurarán el proyecto adecuadamente con poco esfuerzo adicional por parte del usuario.

## Registro de Eventos (Log)
Este proyecto genera un archivo de log que registra los eventos más relevantes durante la ejecución. Este mecanismo permite a los usuarios y desarrolladores rastrear y analizar el comportamiento del programa, facilitando la identificación de problemas y el seguimiento de operaciones críticas.

### Ubicación del Archivo de Log
El archivo de log, denominado `evolucionRestaurante.txt`, se crea y actualiza automáticamente en el directorio de trabajo del programa. Dentro de este archivo, encontrarás información detallada sobre los eventos que ocurren durante la ejecución, incluyendo fechas y horas específicas de cada evento.
```bash
cd <ruta al directorio del proyecto>/target/classes
```
Para revisar los eventos registrados, simplemente abre el archivo `evolucionRestaurante.txt` con cualquier editor de texto. Este archivo se actualiza continuamente con cada ejecución del programa, proporcionando una historia persistente de las actividades realizadas.

## Cómo Usar

Cuando se ejecuta el programa, se abrirá una ventana donde se representarán los eventos más importantes. La única interacción del usuario durante la ejecución se realiza a través de dos botones en la ventana, con los cuales podemos controlar la ejecución de todos los hilos del programa:

- **Parar:** Al hacer clic en este botón, el programa pausará todos los procesos en ejecución. Esto es útil para inspeccionar el estado actual del programa sin el flujo continuo de nuevos eventos. Puede servir para analizar detenidamente el comportamiento hasta el momento, realizar capturas de pantalla sin cambios en la interfaz, o simplemente pausar la actividad si se requiere atención en otro asunto.

- **Reanudar:** Este botón reactiva todos los procesos previamente pausados con el botón "Parar". Al utilizarlo, el programa continúa su ejecución normal, reanudando todas las operaciones y generación de eventos en tiempo real. Es la manera de seguir interactuando con el programa después de haber hecho una pausa para cualquier análisis o interrupción necesaria.

Estos controles ofrecen al usuario una manera sencilla pero poderosa de interactuar con la ejecución del programa, permitiendo un nivel básico de control sobre el flujo de procesos y la capacidad de detenerse a observar los resultados de ciertas acciones o comportamientos del sistema sin finalizar por completo la ejecución del programa.

## Documentación Adicional
Para proporcionar una comprensión más profunda de los métodos de sincronización utilizados, así como de la arquitectura y el diseño del proyecto, se adjunta una memoria técnica en formato PDF. Este documento es esencial para aquellos interesados en los detalles técnicos del manejo de la concurrencia y la interacción entre las diferentes clases del proyecto.

### Contenidos de la Memoria:
- **Descripción Detallada de los Métodos de Sincronización:** Explicaciones sobre cómo se gestionan los problemas de concurrencia en el proyecto, incluyendo técnicas específicas para evitar condiciones de carrera, deadlocks, y otros desafíos comunes.
- **Análisis de las Clases Diseñadas:** Una visión completa de las clases implementadas en el proyecto, su propósito, y cómo contribuyen a la funcionalidad general del simulador.
- **Diagrama de Clases:** Para facilitar la comprensión de la estructura del proyecto y la relación entre sus componentes, se incluye un diagrama de clases. Este esquema visual es una herramienta invaluable para visualizar la interacción entre las diferentes clases.

Este documento es una guía indispensable no solo para entender mejor el proyecto, sino también como un recurso educativo sobre programación concurrente y diseño de software orientado a objetos.
Recomendamos la lectura de la [memoria](/MEMORIA.pdf) para obtener una visión completa del diseño y las decisiones técnicas detrás de este simulador de restaurante.




## Video Tutorial de Uso

Para facilitar la comprensión y el manejo del programa, hemos preparado un video tutorial que muestra el sencillo uso de los botones **Parar** y **Reanudar**. Puedes acceder al video tutorial a través del siguiente enlace: [Video Tutorial de Uso](/ejemplo-como-usar.mp4)

## Contribuciones

Si deseas contribuir a mejorar este simulador, por favor, sientete libre de hacer un fork del repositorio, realizar cambios y enviar un pull request con tus mejoras.

## Licencia

Este proyecto se comparte de manera libre y abierta. Se permite el uso, distribución y modificación sin restricciones. Sin embargo, se agradece el crédito al autor original en caso de uso público.

