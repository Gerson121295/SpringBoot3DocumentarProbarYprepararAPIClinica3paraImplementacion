

<h1 align="center"> Curso de Spring Boot 3: aplique las mejores prácticas y proteja una API Rest para una Clinica en Java</h1>

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
# Curso de Spring Boot 3: aplique las mejores prácticas y proteja una API Rest
- 01 - Aprenda buenas practicas en diseño de API
- 02 - Aprenda fundamentos basicos de autenticación y autorización
- 03 - Aprenda a retornar codigos de error HTTP
- 04 - Aprenda fundamentos de Spring Security
	
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

# Desarrollo del proyecto
* Generando proyecto con Spring Initializr. https://start.spring.io/

![StartProyectSpringInitializr.jpg](src/img-readme/StartProyectSpringInitializr.jpg)

- Revisar y Descargar Codigo del Proyecto Anterior -  API Rest Clinica:
  - https://github.com/alura-es-cursos/1952-spring-boot-3-rest-api/tree/clase-5
  - https://github.com/alura-cursos/2770-spring-boot/tree/projeto_inicial
  - https://github.com/Gerson121295/SpringBoot3DesarrollarAPIRestJavaClinica
  

# Curso de Spring Boot 3: Aplique las mejores prácticas y proteja una API Rest

## 01 - Aprenda buenas practicas en diseño de API

### Para saber más: códigos de protocolo HTTP
- El protocolo HTTP (Hypertext Transfer Protocol, RFC 2616) es el protocolo encargado de realizar la comunicación entre el cliente, que suele ser un navegador, y el servidor. De esta forma, para cada “solicitud” realizada por el cliente, el servidor responde sí tuvo éxito o no. Si no tiene éxito, la mayoría de las veces, la respuesta del servidor será una secuencia numérica acompañada de un mensaje. Si no sabemos qué significa el código de respuesta, difícilmente sabremos cuál es el problema, por eso es muy importante saber qué son los códigos HTTP y qué significan.

#### Categoría de código
Los códigos HTTP (o HTTPS) tienen tres dígitos, y el primer dígito representa la clasificación dentro de las cinco categorías posibles.

- 1XX: Informativo: la solicitud fue aceptada o el proceso aún está en curso;
- 2XX: Confirmación: la acción se completó o se comprendió;
- 3XX: Redirección: indica que se debe hacer o se debió hacer algo más para completar la solicitud;
- 4XX: Error del cliente: indica que la solicitud no se puede completar o contiene una sintaxis incorrecta;
- 5XX: Error del servidor: el servidor falló al concluir la solicitud.

##### Principales códigos de error.
Como se mencionó anteriormente, conocer los principales códigos de error HTTP lo ayudará a identificar problemas en sus aplicaciones, además de permitirle comprender mejor la comunicación de su navegador con el servidor de la aplicación a la que intenta acceder.

###### Error 403
El código 403 es el error "Prohibido". Significa que el servidor entendió la solicitud del cliente, pero se niega a procesarla, ya que el cliente no está autorizado para hacerlo.

###### Error 404
Cuando ingresa una URL y recibe un mensaje de Error 404, significa que la URL no lo llevó a ninguna parte. Puede ser que la aplicación ya no exista, que la URL haya cambiado o que haya ingresado una URL incorrecta.

###### Error 500
Es un error menos común, pero aparece de vez en cuando. Este error significa que hay un problema con una de las bases que hace que se ejecute una aplicación. Básicamente, este error puede estar en el servidor que mantiene la aplicación en línea o en la comunicación con el sistema de archivos, que proporciona la infraestructura para la aplicación.

###### Error 503
El error 503 significa que el servicio al que se accede no está disponible temporalmente. Las causas comunes son un servidor que está fuera de servicio por mantenimiento o sobrecargado. Los ataques maliciosos como DDoS causan mucho este problema.

###### Un consejo final:
Difícilmente podemos guardar en nuestra cabeza lo que significa cada código, por lo que hay sitios web en Internet que tienen todos los códigos y significados para que podamos consultar cuando sea necesario. Hay dos sitios muy conocidos que usan los desarrolladores, uno para cada preferencia: 
- Si te gustan los gatos, puedes usar HTTP Cats; https://http.cat/
- Si prefieres perros, usa HTTP Dogs. https://http.dog/

##### implementar ResponseEntity para las funcionalidades del CRUD de los pacientes.
- Cambiar todos los métodos de la clase PacienteController para que devuelvan el objeto ResponseEntity, de la misma manera que se demostró en clase para la clase MedicoController:
```java
@PostMapping
@Transactional
public ResponseEntity registrar(@RequestBody @Valid DatosRegistroPaciente datos, UriComponentsBuilder uriBuilder) {
    var paciente = new Paciente(datos);
    repository.save(paciente);

    var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
    return ResponseEntity.created(uri).body(new DatosDetalladoPaciente(paciente));
}

@GetMapping
public ResponseEntity<Page<DatosListadoPaciente>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
    var page = repository.findAllByAtivoTrue(paginacion).map(DatosListadoPaciente::new);
    return ResponseEntity.ok(page);
}

@PutMapping
@Transactional
public ResponseEntity actualizar(@RequestBody @Valid DatosActualizacionPaciente datos) {
    var paciente = repository.getReferenceById(datos.id());
    paciente.actualizarInformacion(datos);

    return ResponseEntity.ok(new DatosDetalladoPaciente(paciente));
}

@DeleteMapping("/{id}")
@Transactional
public ResponseEntity eliminar(@PathVariable Long id) {
    var paciente = repository.getReferenceById(id);
    paciente.eliminar();

    return ResponseEntity.noContent().build();
}
```
- Crear un método más en este Controller, que se encargará de devolver los datos de un paciente:
```java 
@GetMapping("/{id}")
public ResponseEntity detallar(@PathVariable Long id) {
    var paciente = repository.getReferenceById(id);
    return ResponseEntity.ok(new DatosDetalladoPaciente(paciente));
}
```
- Crear el DTO DatosDetalladoPaciente:
```java 
 public record DatosDetalladoPaciente(String nombre, String email, String telefono, String documentoIdentidad, Direccion direccion) {
	public DatosDetalladoPaciente(Paciente paciente) {
		this(paciente.getNombre(), paciente.getEmail(), paciente.getTelefono(), paciente.getDocumentoIdentidad(), paciente.getDireccion());
	}
} 
```
## 02 - Tratando Errores
##### Spring boot Properties
- Sitio web para configuraciones de Spring boot. Buscar en google el sitio Spring boot properties luego server Properties.
  - #Spring boot Properties --> Server Properties -> server.error.include-stacktrace

- server.error.include-stacktrace = never, Se agrega en el archivo application.properties,  para evitar que el stacktracer de la excepción sea devuelto en el cuerpo de la respuesta, para evitar mostrar el StackTrages en los logs ya que representa problemas de seguridad al mostrar demasiada informacion.
- El archivo application.properties sirve para hacer configuraciones en el proyecto.

### personalización de mensajes de error
- Bean Validation tiene un mensaje de error para cada una de sus anotaciones. Por ejemplo, cuando la validación falla en algún atributo anotado con @NotBlank, el mensaje de error será: must not be blank.
- Estos mensajes de error no se definieron en la aplicación, ya que son mensajes de error estándar de Bean Validation. Sin embargo, si lo desea, puede personalizar dichos mensajes.
- Una de las formas de personalizar los mensajes de error es agregar el atributo del mensaje a las anotaciones de validación:

```java
public record DatosCadastroMedico(
    @NotBlank(message = "Nombre es obligatorio")
    String nombre,

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Formato de email es inválido")
    String email,

    @NotBlank(message = "Teléfono es obligatorio")
    String telefono,

    @NotBlank(message = "CRM es obligatorio")
    @Pattern(regexp = "\\d{4,6}", message = "Formato do CRM es inválido")
    String crm,

    @NotNull(message = "Especialidad es obligatorio")
    Especialidad especialidad,

    @NotNull(message = "Datos de dirección son obligatorios")
    @Valid DatosDireccion direccion) {}
```

- Otra forma es aislar los mensajes en un archivo de propiedades, que debe tener el nombre ValidationMessages.properties y estar creado en el directorio src/main/resources:
```properties
nombre.obligatorio=El nombre es obligatorio
email.obligatorio=Correo electrónico requerido
email.invalido=El formato del correo electrónico no es válido
phone.obligatorio=Teléfono requerido
crm.obligatorio=CRM es obligatorio
crm.invalido=El formato CRM no es válido
especialidad.obligatorio=La especialidad es obligatoria
address.obligatorio=Los datos de dirección son obligatorios
```
- Y, en las anotaciones, indicar la clave de las propiedades por el propio atributo message, delimitando con los caracteres { e }:
```java
public record DatosRegistroMedico(
    @NotBlank(message = "{nombre.obligatorio}")
    String nombre,

    @NotBlank(message = "{email.obligatorio}")
    @Email(message = "{email.invalido}")
    String email,

    @NotBlank(message = "{telefono.obligatorio}")
    String telefono,

    @NotBlank(message = "{crm.obligatorio}")
    @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
    String crm,

    @NotNull(message = "{especialidad.obligatorio}")
    Especialidad especialidad,

    @NotNull(message = "{direccion.obligatorio}")
    @Valid DatosDireccion direccion) {}
```























- Codigo del Proyecto Actual:
  - https://github.com/alura-es-cursos/1979-spring-boot-buenas-practicas-security/tree/clase-1
- Presentaciones: https://drive.google.com/drive/folders/1eNnXuuPuxIi70toLvNzjDG2Joet70Kt7


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
Excelente curso para aprender SpringBoot.



