

<h1 align="center"> Curso de Spring Boot 3: documentar, probar y preparar una API para su implementación</h1>

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
# Curso de Spring Boot 3: documentar, probar y preparar una API para su implementación - API Clinica v3
Este curso es ideal para desarrollar API´s basadas en un framework de inversion de control para plataforma en JAVA permitiendo agilizar el desarrollo y enfocar en las reglas de negocio del proyecto.

- Aprenda a aislar código de reglas de negocio en una aplicación
- Implemente princípios SOLID
- Documente una API seguindo el protocolo OpenAPI
- Aprenda a escribir pruebas automatizadas en una aplicación con Spring Boot
- Realize el build de una aplicação con Spring Boot
- Use variables de entorno/ambiente y prepare una aplicación para su implementación/deploy

## Clases: 
- 01: Agendamiento de consultas.
- 02: Reglas de negocio.
- 03: Documentación de la API.
- 04: Tests automatizados.
- 05: Build del proyecto.

### Proyecto de una Clinica Medica.
Clínica médica - Nuestra Voll clinic. En una clínica médica intervienen muchas cosas: pacientes, doctores, consultas, historias clínicas, etc.
y hay interacciones interesantes entre estos, por ejemplo, un paciente puede tener muchos doctores así como un doctor puede tener muchos pacientes.

Este tipo de relaciones y mapeamientos lo vamos a ver con Hybernate, por ejemplo. Podemos listar las historias clínicas, podemos listar los pacientes, 
podemos registrar nuevos pacientes, etc.

![clinicaVoll.jpg](src/img-readme/clinicaVoll.jpg)

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

### Reglas de Negocio: Requerimientos

#### Request POST - Registrar Medicos
- Registrar médico con los siguientes datos:
```json
{
	"nombre": "Gerson Ep",
	"email": "Gerson.ep@voll.med",
	"documento":"881114",
	"telefono":"51656222",
	"especialidad": "ORTOPEDIA",
	"direccion":{
		"calle": "calle 6",
		"distrito": "distrito 6",
		"ciudad": "Lima",
		"numero": "1",
		"complemento": "e"
	}
}
```
#### Request GET - Produciendo Datos
- Consideraciones:
  - Informacion Requerida del medico: Nombre, Especialidad, Documento y Email.
  - Reglas de negocio: Ordenado ascendentemente, paginado, maximo 10 registros por paginas.

#### Request PUT - Actualizacion de Médicos
- Se solicita:
  - Informacion permitida para actualizacion: Nombre, Documento y Direccion.
  - Reglas de negocio: No permitir actualizar email, especialidad y documento.

#### Request DELETE - Exclusion de Médicos
- Reglas de Negocio:
  - El registro no debe ser borrado de la base de datos.
  - El listado debe retornar solo médicos activos.
- Los medicos no deben ser borrados de la BD, por lo que se realizar un delete logico. Se quiere que el médico quede desactivado a nivel de BD, por lo que no se debe eliminar, para resolverlo
debemos crear un campo flag(bandera) llamado activo en la tabla medico, al instanciarse cada medico en activo sera = 1, al eliminar el medico activo será = 0, al hacer el listado solo se retornarán los medicos
activos donde Activo es = 1. Y asi los registro medicos no se eliminarán de la BD. Ya que obviamente siempre es bueno mantener un histórico, qué médicos han trabajado en la clinica.

## Desarrollo del proyecto
* Generando proyecto con Spring Initializr. https://start.spring.io/

![StartProyectSpringInitializr.jpg](src/img-readme/StartProyectSpringInitializr.jpg)

## Resultados 
- Para Ejecutar un método exitosamente necesitaremos enviar el token generado del login.
- Método Post: Enviamos login y clave y al logearse se obtiene el token el cual será necesario para ejecutar exitosamente los metodos.
  ![LoginToken.jpg](src/img-readme/loginPruebaToken.jpg)
- ![LoginToken2.jpg](src/img-readme/loginPruebaToken2.jpg)

- Método Mostrar registro de Medicos - Método Get sin Token: no permite mostrar los registros medicos.
![GetMedicoSinToken.jpg](src/img-readme/GetMedicoSinToken.jpg)

- Método Mostrar registro de Medicos - Método Get con Token: Permite mostrar los registros medicos.  Debemos elegir la autenticacion Bearer y en la opcion token pegar el token generado en el login. 
  ![GetMedicoConToken.jpg](src/img-readme/GetMedicoConToken.jpg)

- Método Eliminar un Registro Médico - Método DELETE sin Token no permite eliminar el registro médico.
  ![DeleteSinToken.jpg](src/img-readme/DeleteSinToken.jpg)

- Método Eliminar un Registro Médico - Método DELETE con Token Permite eliminar el registro médico.
  ![DeleteConToken.jpg](src/img-readme/DeleteConToken.jpg)

- Revisar y Descargar Codigo del Proyecto Anterior -  API Rest Clinica v2:
  - https://github.com/alura-es-cursos/1979-spring-boot-buenas-practicas-security/tree/clase-5
  - https://github.com/Gerson121295/SpringBoot3MejoresPracticasYProtejaAPIRestClinica2
  - Presentaciones: https://drive.google.com/drive/folders/1eNnXuuPuxIi70toLvNzjDG2Joet70Kt7

## Curso de Spring Boot 3: documentar, probar y preparar una API para su implementación - API Clinica v3


















- Codigo del Proyecto Actual:
  - https://github.com/alura-es-cursos/Spring-Boot-3/tree/stage-1
- Enlaces donde podemos encontrar el soporte visual de la aplicación mobile front end y las diferentes actividades que fueron realizadas:
  - Aplicación mobile Front End:https://www.figma.com/file/vgn35i1ErivIN8LJYEqxGZ/Untitled?type=design&node-id=0-1&mode=design&t=0KPDVGatVlzK13xz-0
  - Solicitud del cliente para la API: https://trello.com/b/yGQuuyVV/api-voll-med
  
    
    

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
Excelente curso para aprender SpringBoot Security.



