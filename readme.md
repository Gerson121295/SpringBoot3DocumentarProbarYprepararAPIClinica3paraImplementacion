

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

### 01 - Agendamientos de consultas
#### Anotacion @JsonAlias

- Los nombres de los campos enviados en JSON a la API deben ser idénticos a los nombres de los atributos de las clases DTO, ya que de esta manera Spring puede completar correctamente la información recibida.
- Sin embargo, puede ocurrir que un campo se envíe en JSON con un nombre diferente al atributo definido en la clase DTO. Por ejemplo, imagine que se envíe el siguiente JSON a la API:

```json
{
"producto_id": 12,
"fecha_compra": "01/01/2022"
}
```
- Y la clase DTO creada para recibir esta información se define de la siguiente manera:
```java
public record DatosCompra(Long idProducto, LocalDate fechaCompra){}
```
- Si esto ocurre, tendremos problemas porque Spring instanciará un objeto del tipo DatosCompra, pero sus atributos no se completarán y quedarán como null debido a que sus nombres son diferentes a los nombres de los campos recibidos en JSON.
- Tenemos dos posibles soluciones para esta situación:
  1. Renombrar los atributos en el DTO para que tengan el mismo nombre que los campos en JSON;
  2. Solicitar que la aplicación cliente que envía las solicitudes a la API cambie los nombres de los campos en el JSON enviado.
- La primera opción anteriormente mencionada no es recomendable, ya que los nombres de los campos en JSON no están de acuerdo con el estándar de nomenclatura de atributos utilizado en el lenguaje Java.
- La segunda opción sería la más indicada, pero no siempre será posible "obligar" a los clientes de la API a cambiar el estándar de nomenclatura utilizado en los nombres de los campos en JSON.
- Para esta situación, existe una tercera opción en la que ninguno de los lados (cliente y API) necesita cambiar los nombres de los campos/atributos. Para ello, solo es necesario utilizar la anotación @JsonAlias:

```java
public record DatosCompra(
@JsonAlias("producto_id") Long idProducto,
@JsonAlias("fecha_compra") LocalDate fechaCompra
){}
La anotación @JsonAlias sirve para mapear "alias" alternativos para los campos que se recibirán del JSON, y es posible asignar múltiples alias:
public record DatosCompra(
@JsonAlias({"producto_id", "id_producto"}) Long idProducto,
@JsonAlias({"fecha_compra", "fecha"}) LocalDate fechaCompra
){}
```
- De esta manera, se resuelve el problema, ya que Spring, al recibir el JSON en la solicitud, buscará los campos considerando todos los alias declarados en la anotación @JsonAlias.

#### Dar Formato a las fechas
- Como se demostró en el video anterior, Spring tiene un patrón de formato para campos de tipo fecha cuando se asignan a atributos de tipo LocalDateTime. Sin embargo, es posible personalizar este patrón para utilizar otros formatos que prefiramos.
- Por ejemplo, imagine que necesitamos recibir la fecha/hora de la consulta en el siguiente formato: dd/mm/yyyy hh:mm. Para que esto sea posible, debemos indicar a Spring que este será el formato en el que se recibirá la fecha/hora en la API, lo que puede hacerse directamente en el DTO utilizando la anotación @JsonFormat:

```java
@NotNull
@Future
@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
LocalDateTime data
```
- En el atributo pattern indicamos el patrón de formato esperado, siguiendo las reglas definidas por el estándar de fechas de Java. Puede encontrar más detalles en esta página del JavaDoc.
- Esta anotación también se puede utilizar en las clases DTO que representan la información que devuelve la API, para que el JSON devuelto se formatee de acuerdo con el patrón configurado. Además, no se limita solo a la clase LocalDateTime, sino que también se puede utilizar en atributos de tipo LocalDate y LocalTime.

#### Service Pattern
- El patrón Service es muy utilizado en la programación y su nombre es muy conocido. Pero a pesar de ser un nombre único, Service puede ser interpretado de varias maneras: puede ser un caso de uso (Application Service); un Domain Service, que tiene reglas de su dominio; un Infrastructure Service, que utiliza algún paquete externo para realizar tareas; etc.
- A pesar de que la interpretación puede ocurrir de varias formas, la idea detrás del patrón es separar las reglas de negocio, las reglas de la aplicación y las reglas de presentación para que puedan ser fácilmente probadas y reutilizadas en otras partes del sistema.
- Existen dos formas más utilizadas para crear Services. Puede crear Services más genéricos, responsables de todas las asignaciones de un Controller; o ser aún más específico, aplicando así la S del SOLID: Single Responsibility Principle (Principio de Responsabilidad Única). Este principio nos dice que una clase/función/archivo debe tener sólo una única responsabilidad.
- Piense en un sistema de ventas, en el que probablemente tendríamos algunas funciones como: Registrar usuario, Iniciar sesión, Buscar productos, Buscar producto por nombre, etc. Entonces, podríamos crear los siguientes Services: RegistroDeUsuarioService, IniciarSesionService, BusquedaDeProductosService, etc.
- Pero es importante estar atentos, ya que muchas veces no es necesario crear un Service y, por lo tanto, agregar otra capa y complejidad innecesarias a nuestra aplicación. Una regla que podemos utilizar es la siguiente: si no hay reglas de negocio, simplemente podemos realizar la comunicación directa entre los controllers y los repositories de la aplicación.

### Códigos para nuevas funcionalidades
- Para implementar esta o cualesquiera otras funcionalidades, seguimos una especie de paso a paso. Necesitamos crear siempre los siguientes tipos de códigos:
  - Controller, para mapear la solicitud de la nueva funcionalidad;
  - DTOs, que representan los datos que llegan y salen de la API;
  - Entidad JPA;
  - Repository, para aislar el acceso a la base de datos;
  - Migration, para hacer las alteraciones en la base de datos.
- Estos son los cinco tipos de código que siempre desarrollaremos para una nueva funcionalidad. Esto también se aplica al agendamiento de las consultas, incluyendo un sexto elemento a la lista, las reglas de negocio. En esta clase, entenderemos cómo implementar las reglas de negocio con algoritmos más complejos.

- La clase de consultaController, es la clase encargada de comunicarse con las API externas, recibir la información y guardarla en la base de datos.
- Tenemos algunas reglas que deben cumplirse, entonces nosotros tenemos que validar que ese ID existiera en la BD y tenemos que validar otras reglas de negocio, por lo que se construyo una clase de servicio para evitar colocar todas esas responsabilidades dentro de la clase controlador, ya que es la responsabilidad de la clase controlador únicamente comunicarse con APIs externas.
La clase de servicio la responsabilidad de ella va a ser procesar esa información para poder posteriormente enviarla al repositorio y guardar esa información en la base de datos.

## 02 - Reglas de Negocio
###  Principios SOLID
- SOLID es un acrónimo que representa cinco principios de programación:
  - Principio de Responsabilidad Única (Single Responsibility Principle)
  - Principio Abierto-Cerrado (Open-Closed Principle)
  - Principio de Sustitución de Liskov (Liskov Substitution Principle)
  - Principio de Segregación de Interfaces (Interface Segregation Principle)
  - Principio de Inversión de Dependencia (Dependency Inversion Principle)
- Cada principio representa una buena práctica de programación que, cuando se aplica en una aplicación, facilita mucho su mantenimiento y extensión. Estos principios fueron creados por Robert Martin, conocido como Uncle Bob, en su artículo Design Principles and Design Patterns.

## 03 - Documentacion de la API
### Documentando con SpringDoc
#### OpenAPI Initiative
- La documentación es algo muy importante en un proyecto, especialmente si se trata de una API Rest, ya que en este caso podemos tener varios clientes que necesiten comunicarse con ella y necesiten documentación que les enseñe cómo realizar esta comunicación de manera correcta.
- Durante mucho tiempo no existió un formato estándar para documentar una API Rest, hasta que en 2010 surgió un proyecto conocido como Swagger, cuyo objetivo era ser una especificación open source para el diseño de APIs Rest. Después de un tiempo, se desarrollaron algunas herramientas para ayudar a los desarrolladores a implementar, visualizar y probar sus APIs, como Swagger UI, Swagger Editor y Swagger Codegen, lo que lo convirtió en un proyecto muy popular y utilizado en todo el mundo.
- En 2015, Swagger fue comprado por la empresa SmartBear Software, que donó la parte de la especificación a la fundación Linux. A su vez, la fundación renombró el proyecto a OpenAPI. Después de esto, se creó la OpenAPI Initiative, una organización centrada en el desarrollo y la evolución de la especificación OpenAPI de manera abierta y transparente.
- OpenAPI es actualmente la especificación más utilizada y también la principal para documentar una API Rest. La documentación sigue un patrón que puede ser descrito en formato YAML o JSON, lo que facilita la creación de herramientas que puedan leer dichos archivos y automatizar la creación de documentación, así como la generación de código para el consumo de una API.

- OpenAPI: especificamente OpenAPI Spring doc https://springdoc.org/ es una API que se integra en nuestro proyecto y al ejecutarlo permite al usuario o al cliente tener una APP como postman o Insomnia.
Esta API nos va a permitir crear una documentación interactiva dentro de nuestro proyecto, que va a permitirle al cliente conseguir trabajar con los endpoints y darle una mejor ilustración de los parámetros que se están aplicando.
- Para agregar la documentacion agregamos la dependencia de springdoc al archivo pom.xml.
```xml
<dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.2.0</version>
</dependency>
```
- Luego de agregar la dependencia, This will automatically deploy swagger-ui to a spring-boot application:
  - Documentation will be available in HTML format, using the official swagger-ui jars
  - The Swagger UI page will then be available at http://server:port/context-path/swagger-ui.html and the OpenAPI description will be available at the following url for json format: http://server:port/context-path/v3/api-docs
- Estas urls se configuran en la clase securityConfigurations para permitir el acceso a las paginas.
 ```java
    .requestMatchers("/swagger-ui.html", "/v3/api-docs/**","/swagger-ui/**").permitAll()
```
- Luego de configurlo: Ir al navegador Chrome a la ruta para ver la documentacion: http://localhost:8080/v3/api-docs  para una mejor visualizacion de la data en objetos JSON se deberá descargar la extension: JSON Formatter. (O buscar como 2 opcion: Json Beautifier).
- Entonces en ese archivo JSON, vamos a encontrar todos los récords, todas las clases que nosotros estamos enviando dentro de la API de Postman, aquí vemos que nos indica el tipo que tenemos que pasar.
Y también tenemos los endpoints, tanto para pacientes, médicos, el login del token, consultas, pacientes por ID, médicos por ID, el Hello World, tenemos el servidor donde está corriendo, que es el puerto 8080 o cualquier otro servidor que estemos ejecutando dentro de esa aplicación, informaciones extras de api-doc.

![docJson.jpg](src/img-readme/docJson.jpg)

- Luego vamos a entrar a la interfaz de usuario de Swagger a travez de la siguiente enlace: http://localhost:8080/swagger-ui/index.html  vemos que tenemos una interfaz de usuario realizada en HTML donde vamos a encontrar todos los endpoints para los controladores que nosotros realizamos.
  ![swaggerDoc.jpg](src/img-readme/swaggerDoc.jpg)

- Para poder interactuar con los endpoints de los controladores generados por swagger debemos logearnos en login y copiar el toke y pasarlo a las demas controladores para consultar o registrar pacientes, medicos o consulta y para eso se debe pasar el token, por lo que debemos configurarlo para que estos metodos permitan recibie el token en swagger 
por lo que configuramos en nuestra app. en nuestra clase.

#### 13.34. How do I add authorization header in requests?

- You should add the @SecurityRequirement tags to your protected APIs.
- For example:

```java

@Operation(security = { @SecurityRequirement(name = "bearer-key") })  //Este codigo va en los controladores que lo necesiten, excepto AutenticacionController este no lo necesita ya que se le da acceso desde el control de seguridad.

//And the security definition sample:  //Este Bean va en la clase SpringDocConfigurations
@Bean
public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .components(new Components()
        .addSecuritySchemes("bearer-key",
        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
        }
        
```
- En la documentación de Spring-doc, llegamos a la parte de beare(buscar en la lupa), encontramos que para agregar una autorización en el encabezado de nuestras peticiones, tenemos que agregar un método del tipo bean, colocar la anotación bean para ejecutar ese método de forma automática dentro del contexto de Spring framework. 
Así como colocar la anotación de requerimientos de seguridad dentro de todos los controladores que vayan a tener acceso a ese token. Entonces, lo primero sería colocar, copiar ese método. Vamos a ir de vuelta a nuestra API y en el paquete de infra de infraestructura se agrega un nuevo paquete llamado springdoc que va a ser todo lo relacionado a la documentación.
- Ese paquete springdoc y dentro de el vamos a crear la clase SpringDocConfigurations dentro de esta clase, nosotros vamos a agregar ese bean que estamos copiando.

- Luego de haber configurado
- Logearse: para recibir el Token para poder tener acceso a los metodos y acceder a los datos.
- Ir a autenticacion-controller --> En el metodo POST /login clic en try it out: luego escribir el login y la clave.
![loginSwagger.jpg](src/img-readme/loginSwagger.jpg)

- Copiar el token y Vemos que esta vez nosotros tenemos un campo Available authorizations que nos va a permitir colocar ese token de autorización, entonces acá nos indica que tenemos que colocar el valor.
- Y una vez que nosotros seleccionamos autorizar, ese valor va a quedar guardado dentro de la interfaz de usuario en cuanto se esté ejecutando la aplicación, y clic en cerrar.
![opcionAutorizarToken.jpg](src/img-readme/opcionAutorizarToken.jpg)
![ingresoTokenAutorizar.jpg](src/img-readme/ingresoTokenAutorizar.jpg)
![ingresoTokenAutorizar2.jpg](src/img-readme/ingresoTokenAutorizar2.jpg)

- Vamos a obtener el listado de pacientes, le damos a seleccionar acá try out. Y vamos a colocar acá ejecutar.
  ![obtenerPacienteSwagger.jpg](src/img-readme/obtenerPacienteSwagger.jpg)

#### personalizando la documentación
- Ademas personalizar la documentación generada por SpringDoc para incluir el token de autenticación. Además del token, podemos incluir otras informaciones en la documentación que forman parte de la especificación OpenAPI, como la descripción de la API, información de contacto y su licencia de uso.
Estas configuraciones se deben hacer en el objeto OpenAPI, que se configuró en la clase SpringDocConfigurations de nuestro proyecto:

```java
@Bean
public OpenAPI customOpenAPI() {
return new OpenAPI()
.components(new Components()
.addSecuritySchemes("bearer-key",
new SecurityScheme()
.type(SecurityScheme.Type.HTTP)
.scheme("bearer")
.bearerFormat("JWT")))
.info(new Info()
.title("API Voll.med")
.description("API Rest de la aplicación Voll.med, que contiene las funcionalidades de CRUD de médicos y pacientes, así como programación y cancelación de consultas.")
.contact(new Contact()
.name("Equipo Backend")
.email("backend@voll.med"))
.license(new License()
.name("Apache 2.0")
.url("http://voll.med/api/licencia")));  }
```
- Las ventajas de generar una documentación automatizada de una API Rest.
  - Facilidad para que los clientes se integren a ella.
    - La documentación facilita mucho la vida de quienes desean integrarse a una API Rest, ya que expone sus funcionalidades y cómo deben ser consumidas.
  - Posibilidad de probar la API.
    - La documentación generada siguiendo el estándar OpenAPI permite que herramientas como Swagger UI se puedan utilizar para probar la API.

## 04-Tests automatizados
### Testes con Spring Boot

#### ¿Que vamos a Testear?
- Controller --> API
- Service    --> Regla de Negocio
- Repository --> Queries

#### Tipos de Test
##### Test de caja negra: 
- Testeado usando Postman, Insomnia o swagger con spring doc.
- Las pruebas de caja negra nosotros vamos a imaginar que tenemos una caja negra donde simplemente realizamos una acción y nosotros vamos a recibir una acción de retorno. No vamos a tener acceso a las configuraciones internas de esa caja.

##### Test de caja blanca: 
- Testes automatizados, que son pruebas donde nosotros tenemos acceso a la configuración interna.
- En las pruebas de caja blanca, si tenemos un acceso, tenemos más control sobre qué está haciendo realizado. Entonces, dentro de la estructura de nuestro proyecto, nosotros tenemos los controladores.
la parte de los servicios que fueron todas las validaciones y los repositorios. Dentro de los repositorios hay un conjunto de elementos que ya han sido aprobados por el Spring framework, como es el método findByActivoTrue, findById, findAll.
- Entonces, todos esos métodos ya han sido probados, nosotros nos vamos a enfocar en cómo probar estas consultas que nosotros agregamos dentro del repositorio, que fueron consultas manuales y vamos a probar esta consulta simulando un conjunto de datos y configurando un banco de datos de prueba.
- Otro candidato a las pruebas, a realizar testes automatizados, serían las validaciones, ya que en las validaciones nosotros podemos verificar las reglas de negocio, verificar que se estén cumpliendo, el tiempo en el que se están realizando y que los datos que estamos enviando y recibiendo sean correctos.

### Empezar a hacer tests:
- Ir al MedicoRepository -->  
  - Page<Medico> findByActivoTrue(Pageable paginacion);  //Este tipo metodo no necesita hacerle test automatico ya que spring framework lo valida automaticamente.
  - Crear un test automatico para el siguiente metodo de MedicoRepository: Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);
    - Seleccionar el metodo: "seleccionarMedicoConEspecialidadEnFecha" luego clic derecho --> Generate --> Test --> Elegir la libreria a usar en este caso es JUnit5 y seleccionar el metodo a testar luego clic en OK.
      ![testSeleccionaMedico.jpg](src/img-readme/testSeleccionaMedico.jpg)
    - Automaticamente genera una clase llamada MedicoRepositoryTest en la carpeta test dentro de domain.medico
    - Dentro de la carpeta test por defecto se encuetra la clase ApiApplicationTests para test como no se va a utilizar se elimina.
    - la anotacion @DataJpaTest sobre la clase MedicoRepositoryTest permite utilizar algunos métodos, relacionadas a la capa de persistencia. Por lo que se debe configurar una BD. puede ser local usando H2 o una remoto MySQL

- Cuando se hacen Test se debe separar la BD de produccion y la BD de Debug o de pruebas.

##### Realizando Test - Trabajando con una BD Externa MySQL
- Configurar la BD de Pruebas creando un archivo application-test.properties donde guarde la data de conexion a la BD:
```properties
#Para utilizar BD exterma MySQL
#url+direccion(localhost)+puerto(3306)+vollmed_api(nombre de la BD)
spring.datasource.url=jdbc:mysql://localhost/vollmed_api_test?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=admin
```
- Anotaciones a agregar en la clase a testear: MedicoRepositoryTest
```java
@DataJpaTest //permitir utilizar métodos, relacionadas a la capa de persistencia. Por lo que se debe configurar una BD. puede ser local o usando H2 o una remoto MySQL
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para indicar que se va a utilizar una BD externa y que no se va a reemplazar la base de datos que se esta utilizando previamente. Indica que no se va a utilizar la BD en memoria h2, que va a instalar, sino una BD externa.
@ActiveProfiles("test") //Indica el perfil de BD a utilizar
class MedicoRepositoryTest {
    @Test
    void seleccionarMedicoConEspecialidadEnFecha() {
    }
}
```
- Correr la Clase MedicoRepositoryTest debe dar ok.

##### Realizando Test - Trabajando con una BD Local H2 "en memria"
- Es posible realizar pruebas de interfaces de repositorio utilizando una base de datos en memoria, como H2, en lugar de utilizar la misma base de datos de la aplicación.
- Se debe agregar la dependencia de H2
```xml
        <dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>
```
- Configurar la BD de Pruebas creando un archivo application-test.properties donde guarde la data de conexion a la BD:
```properties
#Para utilizar BD H2 Local
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```
- Anotaciones a agregar en la clase a testear: MedicoRepositoryTest
```java
@DataJpaTest //permitir utilizar métodos, relacionadas a la capa de persistencia. Por lo que se debe configurar una BD. puede ser local o usando H2 o una remoto MySQL
@ActiveProfiles("test") //Indica el perfil de BD a utilizar
class MedicoRepositoryTest {
    @Test
    void seleccionarMedicoConEspecialidadEnFecha() {
    }
}
```
- Correr la Clase MedicoRepositoryTest debe dar ok.
- Si da error, Probar:
  - Se debe eliminar las anotaciones @AutoConfigureTestDatabase y @ActiveProfiles en la clase de prueba, dejándola solo con la anotación @DataJpaTest:
  - También puede eliminar el archivo application-test.properties, ya que Spring Boot realiza la configuración de la URL, el nombre de usuario y la contraseña de la base de datos H2 de manera automática.
  
### Testando el repository - MedicoRepository
- Ver en la carpeta test la clase MedicoRepositoryTest los 2 escenarios de pruebas realizados.
### Testando los controladores 
#### Test a ConsultaController - Testando Error 400
- Entrar a la clase ConsultaController
- Clic derecho dentro de la clase y clicar Generate --> Tests -> Y seleccionar la libreria a usar JUnit5 y el metodo agendar para el test --> automaticamente genera la clase ConsultaControllerTest para las pruebas.


### Codigo Testables
- Afirmaciones verdaderas en relación con las pruebas automatizadas.
  - Podemos combinar pruebas unitarias con pruebas de integración. 
    - Para algunos componentes, como las clases controller, podemos escribir pruebas unitarias; mientras que para otros, como las interfaces repository, se recomiendan pruebas de integración.
  - Algunos componentes de Spring no necesitan ser probados. 
    - Algunos componentes, como las interfaces repository que no tienen métodos de consulta, no necesitan pruebas automatizadas.
  
## 05-Build del proyecto
### Build con Maven
- Una vez que nosotros desarrollamos la aplicación en su totalidad, realizamos todas las pruebas, todas las verificaciones y la ejecutamos, nosotros vamos a querer realizar un deploy o en algún servidor o en alguna nube.
- para eso, nosotros tenemos que entregar un archivo ejecutable, y eso lo vamos a hacer a través Maven. Entonces Maven, además de ayudarnos con la descarga de las dependencias para nuestra aplicación, él nos ayuda a realizar el empaquetamiento de todo lo que hemos desarrollado a lo largo del curso para hacer el deploy posteriormente dentro de un servidor.
- Tenemos que tener un archivo con las clases compiladas, que es el que vamos a colocar dentro del servidor, y de esa forma va a ejecutar la aplicación para que esté disponible para otros usuarios.
- Vamos a realizar el empaquetamiento de nuestra aplicación, vamos a trabajar con los perfiles, en los ambientes donde vamos a trabajar, nosotros ya mostramos que estábamos trabajando con el ambiente de prueba únicamente para las pruebas donde teníamos una base de datos aparte de la base de datos que estábamos utilizando para el desarrollo de la aplicación.
- Entonces nosotros vamos a tener un tercer perfil que es el perfil de producción. Ese perfil es el perfil donde nosotros vamos a colocar las configuraciones que van a ir al servidor final. Entonces, para eso nosotros vamos a copiar este archivo que es el archivo de aplicaciones. Lo vamos a pegar dentro de la carpeta de recursos.
- A este archivo le vamos a colocar application-prod.properties, indicando que va a hacer el perfil de producción. Realizamos las siguientes configuraciones en el perfil de produccion:
- Usar variables de ambiente para evitar exponer la ruta, así como el usuario y la contraseña, dentro de nuestra aplicación. Para eso vamos a utilizar el siguiente comando. ${DATASOURCE_URL} De la misma forma lo vamos a hacer para el usuario. DATASOURCE_USERNAME. 
Y colocar $DATASOURE_PASSWORD. Entonces, de esta forma estamos indicando a Spring framework, que este va a ser el formato con el que vamos a pasar la URL, entonces así protegemos la aplicación de dejar las contraseñas de usuarios y la ruta de la base de datos expuestos.
- Adicionalmente, se coloca acá el perfil, el perfil que se encuentra activo. Entonces, ya que este es el perfil de producción, vamos a colocar profile.active: prod. Entonces, ya con eso, nosotros estamos indicando acá que estamos activando el perfil de producción para esta configuración.
- Y dentro application.properties de la aplicación convencional, vamos a activar los perfiles profiles.active. Acá vamos a activar todos los perfiles, que sería el perfil de desarrollo, el perfil de test y el perfil de producción.

#### Configuracion del perfil de produccion prod application-prod.properties
```properties
spring.profile.active=prod
spring.datasource.url=${DATASOURCE_URL}
spring.datasource.username=${DATASOURCE_USERNAME}
spring.datasource.password=${DATASOURCE_PASSWORD}
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false
server.error.include-stacktrace=never
api.security.secret=${JWT_SECRET:123456}
```

#### Configuracion del perfil convencional: application.properties
```properties
spring.profiles.active=dev, test, prod
spring.datasource.url=jdbc:mysql://localhost/vollmed_api
spring.datasource.username=root
spring.datasource.password=admin
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.error.include-stacktrace=never
api.security.token.secret=${JWT_SECRET:123456}
```

#### Configuracion del perfil test: application-test.properties
```properties
## Database:
## url+direccion(localhost)+puerto(3306)+vollmed_api(nombre de la BD)
spring.datasource.url=jdbc:mysql://localhost/vollmed_api_test?createDatabaseIfNotExist=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=admin
```
- Para que el Build.maven sea exitosa debemos tener bien estructurados los application.properties sin tantos comentarios ni espacios.

-  Una vez toda la configuración realizada, realizar lo siguiente:
  - Ir a la pestaña de Maven y en la parte de API vamos a ir a donde dice Lifecycle(ciclo de vida) y en package clic derecho y vamos a correr, Run Maven build.
  - Sí se ejecutó correctamente, vemos que el realizó el compilado de forma exitosa, ejecutó las cuatro pruebas y realizó en el lado izquierdo, en el panel izquierdo.
    ![buildMaven1.jpg](src/img-readme/buildMaven1.jpg)
    ![buildMaven2.jpg](src/img-readme/buildMaven2.jpg)

  - Nosotros vamos a encontrar ahora una carpeta llamada target con todas las clases que fueron generadas y un archivo ejecutable "api-0.0.1-SNAPSHOT.jar" que es el que nosotros vamos a tomar y vamos a descargar dentro de alguno de los servicios Cloud, como Amazon, Azure o Google Cloud.
    - Solo se necesitas agregar el archivo JAR principal, que es "api-0.0.1-SNAPSHOT.jar", al servidor para realizar el despliegue.
  ![buildSuccessMaven.jpg](src/img-readme/buildSuccessMaven.jpg)

- En la siguiente parte vamos a ver cómo se realizan estas actividades dentro de la consola.

### Ejecutando via Terminal
- Luego de haber compilado la aplicación, ahora vamos a intentar simular el deploy en un servicio de nube o en un servidor local. Entonces lo primero que tenemos que hacer es revisar dónde se encuentra ese archivo dentro de la consola. Para eso vamos a utilizar el comando ls target.
- Dentro de Intellij teniendo abierto el proyecto, abrimos la consola de intellij y escribimos el comando: ls target
- Al ejecutar ls target obtenemos el archivo "api-0.0.1-SNAPSHOT.jar" que debemos compilara para desplegar en el servidor.
- Pero antes de compilar debemos ver la version de java con la que se esta trabajando, ejecutamos: java -version //tenemos la version 17 si queremos cambiarla vamos a la pestaña de File --> Project Structure --> project --> y cambiar la version.
``` java
java -version
```
- compilamos ese archivo "api-0.0.1-SNAPSHOT.jar" con el comando java –jar, pasando la ruta donde se encuentra y el nombre del archivo a desplegar.
``` java
java -jar target/api-0.0.1-SNAPSHOT.jar
``` 
- Compilando estableciendo el perfil de Produccion enviandole los datos de la BD, por lo que es acá luego de la palabra java, se coloca todos los valores para esas variables de ambiente. 
En los servicios de cloud, podemos encontrar en alguna parte como Google o en Amazon, alguna parte donde se puede configurar esas variables de ambiente o si se encuentra en un escritorio como Windows pueden configurar las variables de ambiente en la parte de propiedades del sistema.:  

``` java
java -DDATASOURCE_URL=jdbc:mysql://localhost/vollmed_api -DDATASOURCE_USERNAME=root -DDATASOURCE_PASSWORD=admin -jar target/api-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```
![deployLocalSuccess.jpg](src/img-readme/deployLocalSuccess.jpg)
![deployLocalSuccess2.jpg](src/img-readme/deployLocalSuccess2.jpg)

- Él buscó el perfil de producción, y no ha encontró ningún error. Entonces, las variables de ambiente fueron configuradas, él ejecutó la aplicación y ya encontramos la aplicación corriendo dentro de nuestro ambiente del escritorio con las variables previamente pasadas dentro de la consola.
- Este es el mismo proceso en un sistema de cloud y de esta forma, tenemos nuestra aplicación desarrollada y aplicada dentro del servidor exitosamente.

### Build con archivo .war
- Proyectos que utilizan Spring Boot generalmente usan el formato jar para empaquetar la aplicación, como se demostró durante esta lección. Sin embargo, Spring Boot brinda soporte para empaquetar la aplicación en formato war, que era ampliamente utilizado en aplicaciones Java antiguas.
- Si desea que el build del proyecto empaquete la aplicación en un archivo en formato war, deberá realizar los siguientes cambios:

1) Agregue la etiqueta <packaging>war</packaging> al archivo pom.xml del proyecto, y esta etiqueta debe ser hija de la etiqueta raíz <project>:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
<modelVersion>4.0.0</modelVersion>
<parent>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-parent</artifactId>
<version>3.0.0</version>
<relativePath/> <!-- lookup parent from repository -->
</parent>
<groupId>med.voll</groupId>
<artifactId>api</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>api</name>
<packaging>war</packaging>
```
2) En el archivo pom.xml, agregue la siguiente dependencia:

```dependency
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-tomcat</artifactId>
  <scope>provided</scope>
</dependency>
```
3) Cambie la clase principal del proyecto (ApiApplication) para heredar de la clase SpringBootServletInitializer, y sobrescriba el método configure:

```java
@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer {
@Override
protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
return application.sources(ApiApplication.class);
}
public static void main(String[] args) {
SpringApplication.run(ApiApplication.class, args);
}
}
```
- ¡Listo! Ahora, al realizar el build del proyecto, se generará un archivo con la extensión .war en el directorio target, en lugar del archivo con la extensión .jar.

### GraalVM Native Image
- Una de las características más destacadas de la versión 3 de Spring Boot es el soporte para imágenes nativas, algo que reduce significativamente el consumo de memoria y el tiempo de inicio de una aplicación. Algunos otros frameworks competidores de Spring Boot, como Micronaut y Quarkus, ya ofrecían soporte para esta función.
- De hecho, era posible generar imágenes nativas en aplicaciones con Spring Boot antes de la versión 3, pero esto requería el uso de un proyecto llamado Spring Native que agregaba soporte para ello. Con la llegada de la versión 3 de Spring Boot, ya no es necesario utilizar este proyecto.

#### Native Image
- Una imagen nativa es una tecnología utilizada para compilar una aplicación Java, incluyendo todas sus dependencias, generando un archivo binario ejecutable que puede ser ejecutado directamente en el sistema operativo sin necesidad de utilizar la JVM. Aunque no se ejecute en una JVM, la aplicación también contará con sus recursos, como la gestión de memoria, el recolector de basura y el control de la ejecución de hilos.
- Para obtener más detalles sobre la tecnología de imágenes nativas, consulte la documentación en el sitio web:<a>https://www.graalvm.org/native-image </a>
#### Native Imagem com Spring Boot 3
- Una forma muy sencilla de generar una imagen nativa de la aplicación es mediante un plugin de Maven, que debe incluirse en el archivo pom.xml:
``` dependency
<plugin>
  <groupId>org.graalvm.buildtools</groupId>
  <artifactId>native-maven-plugin</artifactId>
</plugin>
```
- Listo! Esta es la única modificación necesaria en el proyecto. Después de esto, la generación de la imagen debe hacerse a través de la terminal, con el siguiente comando de Maven que se ejecuta en el directorio raíz del proyecto: ./mvnw -Pnative native:compile
- Este comando puede tardar varios minutos en completarse, lo cual es completamente normal.
- ¡Atención! Para ejecutar el comando anterior y generar la imagen nativa del proyecto, es necesario que tenga instalado en su computadora GraalVM <a>https://www.graalvm.org/22.0/docs/getting-started/ </a> (una máquina virtual Java con soporte para la función Native Image) en una versión igual o superior a 22.3.
Después de que el comando anterior termine, se generará en la terminal un registro como el siguiente:
```
Top 10 packages in code area:           Top 10 object types in image heap:
   3,32MB jdk.proxy4                      19,44MB byte[] for embedded resources
   1,70MB sun.security.ssl                16,01MB byte[] for code metadata
   1,18MB java.util                        8,91MB java.lang.Class
 936,28KB java.lang.invoke                 6,74MB java.lang.String
 794,65KB com.mysql.cj.jdbc                6,51MB byte[] for java.lang.String
 724,02KB com.sun.crypto.provider          4,89MB byte[] for general heap data
 650,46KB org.hibernate.dialect            3,07MB c.o.s.c.h.DynamicHubCompanion
 566,00KB org.hibernate.dialect.function   2,40MB byte[] for reflection metadata
 563,59KB com.oracle.svm.core.code         1,30MB java.lang.String[]
 544,48KB org.apache.catalina.core         1,25MB c.o.s.c.h.DynamicHu~onMetadata
  61,46MB for 1482 more packages           9,74MB for 6281 more object types
--------------------------------------------------------------------------------
    9,7s (5,7% of total time) in 77 GCs | Peak RSS: 8,03GB | CPU load: 7,27
--------------------------------------------------------------------------------
Produced artifacts:
 /home/rodrigo/Desktop/api/target/api (executable)
 /home/rodrigo/Desktop/api/target/api.build_artifacts.txt (txt)
================================================================================
Finished generating 'api' in 2m 50s.
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  03:03 min
[INFO] Finished at: 2023-01-17T12:13:04-03:00
[INFO] ------------------------------------------------------------------------
```
- La imagen nativa se genera en el directorio target, junto con el archivo .jar de la aplicación, como un archivo ejecutable de nombre api-0.0.1-SNAPSHOT.jar
- texto alternativo: Lista de archivos y directorios ubicados dentro del directorio target del proyecto, incluido el archivo de la imagen nativa, cuyo nombre es api.

- La diferencia del archivo .jar, que se ejecuta en la JVM mediante el comando java -jar, la imagen nativa es un archivo binario y debe ejecutarse directamente desde la terminal:
```
target/api
```
- Al ejecutar el comando anterior se generará el registro de inicio de la aplicación, que al final muestra el tiempo que tardó la aplicación en iniciarse:
```
INFO 127815 --- [restartedMain] med.voll.api.ApiApplication : Started ApiApplication in 0.3 seconds (process running for 0.304)
```
- Observe que la aplicación tardó menos de medio segundo en iniciarse, algo realmente impresionante, ya que cuando se ejecuta en la JVM, a través del archivo .jar, este tiempo aumenta a alrededor de 5 segundos.
- Para obtener más detalles sobre la generación de una imagen nativa con Spring Boot 3, consulte la documentación en el sitio: Soporte de imagen nativa GraalVM <a> https://www.graalvm.org/22.0/reference-manual/native-image/ </a>.

#### Ventajas de utilizar variables de entorno en las configuraciones de una aplicación.
- Flexibilizar las configuraciones de la aplicación.
  - Con variables de entorno, es posible modificar configuraciones en la aplicación sin tener que realizar cambios en el código.
- Evitar vulnerabilidades en la aplicación.
  - Con variables de entorno, evitamos exponer información sensible en el código de la aplicación, como el inicio de sesión y la contraseña de la base de datos.


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



