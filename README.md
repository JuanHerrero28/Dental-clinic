# Dental-clinic
Este proyecto tiene como objetivo implementar un sistema que administre la reserva de turnos para una clínica dental.

Administración de Datos de Odontólogos: Listar, agregar, modificar y eliminar odontólogos. Se registra el apellido, nombre y matrícula de cada uno.
Administración de Datos de Pacientes: Listar, agregar, modificar y eliminar pacientes. Se almacena el nombre, apellido, dirección, número de identificación y fecha de registro.
Registro de Turnos: Permite asignar a un paciente un turno con un odontólogo en una fecha y hora específicas.
Inicio de Sesión: Valida el acceso al sistema mediante un inicio de sesión con nombre de usuario y contraseña. Los usuarios con el rol ROLE_USER pueden registrar turnos, mientras que solo aquellos con rol administrativo (ROLE_ADMIN) pueden gestionar odontólogos y pacientes. Cada usuario tiene un único rol, registrado directamente en la base de datos.
Requerimientos Técnicos

## La aplicación está estructurada en capas:

Capa de Entidades de Negocio: Clases Java que modelan la lógica empresarial utilizando paradigmas orientados a objetos.
Capa de Acceso a Datos (Repository): Clases responsables del acceso a la base de datos.
Capa de Datos (Base de Datos): Utiliza un modelo entidad-relación. Se elige la base de datos H2 por su practicidad.
Capa de Negocio: Clases de servicio que desacoplan el acceso a datos de la vista.
Capa de Presentación: Interfaces web desarrolladas con el framework Spring Boot MVC, controladores y ya sea HTML+JavaScript u otra vista preferida.
El manejo de excepciones es crucial, con registro de cualquier excepción y desarrollo de pruebas unitarias para garantizar la calidad del software.

## Fases del Proyecto

## El proyecto sigue un enfoque por fases:

Sprint 0: Modelado UML, estructuración de tablas de la base de datos y configuración básica de pantallas HTML.
Sprint 1: Pruebas unitarias de las clases Java.
Sprint 2: Configuración de Maven, creación de clases DAO y service, y pruebas unitarias continuas.
Sprint 3: Refactorización del acceso a datos para el uso de ORM, desarrollo de APIs mediante controladores e integración con pantallas HTML a través de JavaScript.
Sprint 4: Adición de funcionalidad de inicio de sesión utilizando Spring Security.
