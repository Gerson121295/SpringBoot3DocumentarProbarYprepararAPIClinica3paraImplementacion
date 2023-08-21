

<h1 align="center"> Curso de Spring Boot 3 desarrollar una API Rest para una Clinica en Java</h1>

## Índice

* [Descripción del proyecto](#descripción-del-proyecto)
* [Tecnologías utilizadas](#tecnologías-utilizadas)
* [Desarrollo del proyecto](#Desarrollo)
* [Estado del proyecto](#estado-del-proyecto)

* [Características y demostración del Proyecto](#características-y-demostracion-Proyecto)

* [Acceso al proyecto](#acceso-proyecto)

* [Personas Contribuyentes](#personas-contribuyentes)

* [Licencia](#licencia)

* [Conclusión](#conclusión)


# Descripción del proyecto

- Realice este curso para Java y:
  - Cree una API Rest de Java desde cero con Spring Boot
  - Desarrolle CRUD usando la base de datos MySQL
  - Use Flyway como una herramienta de migración de API
  - Realizar validaciones usando Bean Validation
  - Realizar paginación de datos API

- Objetivos Creacion de una API Rest
  - CRUD (create, Read, Update, Delete)
  - Validaciones
  - Paginacion y orden

	
### Proyecto de una Clinica Medica.
Clínica médica - Nuestra Voll clinic. En una clínica médica intervienen muchas cosas: pacientes, doctores, consultas, historias clínicas, etc.
y hay interacciones interesantes entre estos, por ejemplo, un paciente puede tener muchos doctores así como un doctor puede tener muchos pacientes.

Este tipo de relaciones y mapeamientos lo vamos a ver con Hybernate, por ejemplo. Podemos listar las historias clínicas, podemos listar los pacientes, 
podemos registrar nuevos pacientes, etc.

# Tecnologías utilizadas
- Tecnologias
	- Spring Boot 3
	- Java 17
	- Lombok: (herramienta para ayudar a reducir codigo, autogenera getter and setters, constructorses, etc).
	- MySQL/Flyway: (Flyway es un gestor de base de datos a nivel de la estructura y las tablas, declarar tus tablas como Scribd de SQL y
	  el motor de Flyway lo ejecuta y va a crear tu estructura de datos en MySQL de tal forma que es mantenible en el futuro,
	  es versionable y bueno puedes habilitar colaboración entre muchos desarrolladores.)
	- JPA/Hibernate: (JPA es la especificación de Java para lo que es persistencia y Hibernate es la implementación de esta especificación.)
	- Maven: (Maven es un gestor de dependencias, al igual que Gradle. Con esto tú declaras tus dependencias en el archivo pom.xml,
	  y puedes controlar mejor las versiones, actualizar y no tienes que necesariamente tener el archivo jar y pegarlo en tu proyecto.)
	- Insomnia: (Para probar nuestra API).


# Desarrollo del proyecto
* Generando proyecto con Spring Initializr. https://start.spring.io/

![StartProyectSpringInitializr.jpg](C:\Users\Hp01\OneDrive\Escritorio\Cursos\Alura\JavaBackend-SpringBoot\SpringBoot3DesarrollarAPIRestJavaClinica\api\img-readme\StartProyectSpringInitializr.jpg)

* Dependencias. Qué cosas va usar nuestro proyecto externas a lo que tenemos (las que ya se eligieron como maven, java17 jara).
- Spring Boot DevTools: Nos da la facilidad de modificar nuestro código y no tener que reiniciar el servidor. Para que veamos los cambios en tiempo real.
- Lombok: Para reducir codigo.
- Spring Web: Que si bien no vamos hacer una aplicación web, pero Spring Web nos da las librerías que necesitamos para exponer nuestros métodos de API Rest a través de post, get, put, delete, etcétera.

### Java y sus Versiones:
Estamos utilizando Java 17 en este curso, sin embargo, generalmente Oracle sugiere instalar la versión más actual y no tiene problema usar la versión más reciente pues así evita los problemas de compatibilidad.

Además, puede instalar una versión Java y utilizar otra versión en el IDE de su máquina. Vamos a mostrar dos ejemplos de cómo cambiar la versión de Java en un proyecto.

#### - Un ejemplo con Intellij IDEA:

Seleccione la opción 'File' en el menú principal.
Seleccione 'Project Structure'.
En 'Project Settings' en la parte 'Project' seleccione la opción deseada de Java en 'SDK' Y seleccione Ok.
Recuerde que es posible añadir nuevas versiones y utilizar como desee

#### - Un ejemplo con Eclipse:

Seleccione el proyecto con el botón derecho y seleccione la opción ‘Properties’.
Seleccione la sección ‘Java Compiler’ y desmarque la opción de la parte ‘JDK Compliance’.
Después es posible cambiar la versión de Java en la parte ‘Compiler compliance level’.
Seleccione la sección ‘Java Compiler'y desmarque la opción de la parte ‘JDK Compliance’.- Después es posible cambiar la versión de Java en la parte ‘Compiler compliance level’.


## Que es Spring y Spring Boot

Spring y Spring Boot no son lo mismo con diferentes nombres.

Spring es un framework para desarrollar aplicaciones en Java, creado a mediados de 2002 por Rod Johnson, que se ha vuelto muy popular y adoptado en todo el mundo debido a su simplicidad y facilidad de integración con otras tecnologías.
El framework se desarrolló de forma modular, en el que cada recurso que proporciona está representado por un módulo, que se puede agregar a una aplicación según sea necesario. Con esto, en cada aplicación podemos agregar solo los módulos que tengan sentido, haciéndola así más liviana. Hay varios módulos en Spring, cada uno con un propósito diferente, tales como: el módulo MVC, para desarrollar aplicaciones Web y API's Rest; el módulo de Security, para manejar el control de autenticación y autorización de las aplicaciones; y el módulo Transactions, para gestionar el control transaccional.
Sin embargo, uno de los mayores problemas de las aplicaciones que usaban Spring era la parte de configuración de sus módulos, que se hacía íntegramente con archivos XML, y después de unos años el framework también comenzó a soportar configuraciones a través de clases Java, utilizando principalmente anotaciones. En ambos casos, dependiendo del tamaño y complejidad de la aplicación, así como de la cantidad de módulos Spring utilizados en ella, dichas configuraciones eran bastante extensas y difíciles de mantener.

Además, iniciar un nuevo proyecto con Spring era una tarea bastante complicada, debido a la necesidad de realizar este tipo de configuraciones en el proyecto.

Precisamente para solventar tales dificultades, a mediados de 2014 se creó un nuevo módulo Spring, denominado Boot, con el objetivo de agilizar la creación de un proyecto que utilice Spring como framework, así como simplificar las configuraciones de sus módulos.

El lanzamiento de Spring Boot fue un hito para el desarrollo de aplicaciones Java, ya que hizo más simple y ágil esta tarea, facilitando mucho la vida de las personas que utilizan el lenguaje Java para desarrollar sus aplicaciones.

- La versión 3 de Spring Boot se lanzó en noviembre de 2022 y trae algunas características nuevas en comparación con la versión anterior. Entre las principales novedades se encuentran:
  - Compatibilidad con Java 17
  - Migración de especificaciones Java EE a Jakarta EE
  - Compatibilidad con imágenes nativas
  - Puede ver la lista completa de las novedades de Spring Boot versión 3 en el sitio web: Spring Boot 3.0 Release Notes

#### Abrimos el proyecto en El Editor de codigo Intellij IDEA
- Clic en File --> Open --> Elegir api (archivoDescargado generado en Spring Initalizr) -> clic en Trust Project. y listo.



























# Estado del proyecto
<p>
   <img src="https://img.shields.io/badge/STATUS-ESTA%20EN PROCESO-yellow">
</p>

# Personas Contribuyentes
## Autores

| [<img src="https://avatars.githubusercontent.com/u/79103450?v=4" width=115><br><sub>Gerson Escobedo</sub>](https://github.com/gerson121295)

# Licencia
![GitHub](https://img.shields.io/github/license/dropbox/dropbox-sdk-java)

License: [MIT](License.txt)

# Personas Desarrolladores del Proyecto
![GitHub Org's stars](https://img.shields.io/github/stars/GERSON121295?style=social)


# Conclusión



