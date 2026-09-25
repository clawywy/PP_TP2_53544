Trabajo Práctico N° 2: Programación Orientada a Objetos en Java
 Descripción General

Este proyecto implementa y extiende un sistema de gestión de eventos universitarios aplicando los principios de la Programación Orientada a Objetos (POO) en Java. 

El sistema está diseñado de forma **completamente interactiva por consola**, guiando al usuario paso a paso en el registro de estudiantes, configuración del evento, asignación de salas, creación polimórfica de actividades, inscripciones dinámicas, control de cupos mediante excepciones, serialización a disco, emisión selectiva de certificados mediante interfaces y análisis de colecciones con métodos genéricos acotados.


  Organización del Proyecto por Paquetes

Para garantizar la modularidad, la trazabilidad del desarrollo y facilitar la corrección progresiva de cada consigna, el código se encuentra estructurado en paquetes incrementales:

* `EJ1` (Manejo de Excepciones y Persistencia):** Implementación de la excepción de negocio `CupoExcedidoException`, estructura granular `try-catch-finally` y serialización de objetos en archivos binarios `.dat`.
* `EJ2` (Interfaces y Polimorfismo Avanzado):** Incorporación de la interfaz `Certificable`, la nueva entidad `Curso` y el mecanismo de emisión/discriminación de certificados de asistencia.
* `EJ3` (Genéricos y Comodines / Wildcards):** Métodos parametrizados acotados y comodines superiores para el filtrado seguro de actividades y el cálculo diferencial de costos de materiales.

🚀 Instrucciones de Ejecución
Desde IntelliJ IDEA:
1. Clonar el repositorio:
git clone https://github.com/clawywy/PP_TP2_53544
2. Abrir el proyecto en IntelliJ IDEA (asegurando tener configurado un JDK 21 o superior).
3. Navegar hasta src/EJ3/App.java (o el paquete del ejercicio que se desee evaluar).
4. Hacer clic derecho sobre la clase y seleccionar Run 'App.main()'.
