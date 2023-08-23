

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

![StartProyectSpringInitializr.jpg](src/img-readme/StartProyectSpringInitializr.jpg)

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

#### Revisamos los archivos del proyecto
- Archivo pom.xml:
  - Parent: el spring-boot-starter-parent, Nosotros no tenemos ninguna dependencia que sea, explícitamente Spring Boot como dependencia. ¿Dónde está Spring Boot? Bueno, Spring Boot viene del parent. El parent es como que el padre en el como que el padre de este pom. Por lo tanto al especificar el parent, Spring Boot, esto quiere decir van a heredar todas las características que existen en el pom de Spring Boot. Si hay alguna otra dependecia que quieras que herede a todo el proyecto se puede definir aqui.
  - Resources: En la parte de recursos o resources tenemos la carpeta static, que es para guardar archivos, JavaScript o .CSS para hojas de estilos en caso que estés creando una aplicación web. La parte de templates es para guardar todo lo que son páginas HTML.
  - application.properties: Es para almacenar propiedades del archivo. Por ejemplo, si tú quieres en tu código especificar una versión, alguna característica predeterminada nos puede ser útil crear en un archivo properties.

#### Ejecutamos el proyecto:
- Si no tenemos el modulo del proyecto con Java 17, Debemos actualizar el proyecto a Java 17.
  - Cambiar el proyecto a java 17: Seleccionar proyecto --> Clic en File --> Project Structure --> Clic en Project --> en SDK --> Seleccionar: Java17 --> clic en Apply y Aceptar.
    ![ChangeProjectToJava17.jpg](src/img-readme/projectwithJava17.jpg)
- Configurar el proyecto para que pueda usar Devtools: con DevTools no deberíamos reiniciar la aplicación para que funcione.
  - Seleccionar el Proyecto --> Clic en file --> clic en Settings --> desplegar Build, Execution, Deployment --> Seleccionar Compiler --> y dar clic o seleccionar Build project automatically --> clic en Apply
    ![ConfigDevTools1.jpg](src/img-readme/ConfDevTools1.jpg)
  - luego buscar y clicar Advanced Settings: --> dar clic y Seleccionar Allow auto-make to start even if developed aplication is currently running: Clic en Aply y Ok.
    ![ConfigDevTools2.jpg](src/img-readme/ConfDevTools2.jpg)
  - Esto va a habilitar que nuestra aplicación recargue automáticamente apenas guardamos, sin que tengamos que reiniciar el servidor. Solo recargamos el navegador.

## Requests POST
### Enviando datos:
- Para las pruebas utilizaremos Insomnia por lo que debemos descargarla: https://insomnia.rest/download
- Utilizando Insomnia - Este es el cuerpo JSON que va a llegar o debería llegar a mi endpoint de médicos aquí en mi controller.
```
{
  "nombre": "Rodrigo Lopez",
  "email": "rodrigo.lopez@voll.med",
  "documento":"123456",
  "especialidad": "ortopedia",
  "direccion":{
  "calle": "calle 1",
  "distrito": "distrito 1",
  "ciudad": "lima",
  "numero": "1",
  "complemento": "a"
  }
  }
```
- El body que enviamos normalmente de request post, se llama payload

### Recibiendo datos:
- JSON (JavaScript Object Notation) es un formato utilizado para representar información, al igual que XML y CSV.
- Una API necesita recibir y devolver información en algún formato, que representa los recursos que administra. JSON es uno de estos posibles formatos, habiéndose popularizado por su ligereza, sencillez, facilidad de lectura por personas y máquinas, así como por su soporte para diferentes lenguajes de programación.
- Un ejemplo de representación de información en formato XML sería:
```
<producto>
    <nombre>Mochila</nombre>
    <precio>89.90</precio>
    <descripcion>Mochila para notebooks de hasta 17 pulgadas</descripcion>
</producto>
```

- La misma información podría representarse en formato JSON de la siguiente manera:
```
{
“nombre” : “Mochila”,
“precio” : 89.90,
“descripcion” : “Mochila para notebooks de hasta 17 pulgadas”
}
```
- Observe cómo el formato JSON es mucho más compacto y legible. Precisamente por eso, se ha convertido en el formato universal utilizado en la comunicación de aplicaciones, especialmente en el caso de las API REST.
- Se pueden encontrar más detalles sobre JSON en el sitio web JSON.org.

##### Tratando con CORS
Cuando desarrollamos una API y queremos que todos sus recursos estén disponibles para cualquier cliente HTTP, una de las cosas que nos viene a la mente es CORS (Cross-Origin Resource Sharing), en Español, “Intercambio de recursos con diferentes orígenes” Si aún no te ha pasado, no te preocupes, es normal tener errores de CORS al consumir y poner a disposición las APIs.

Pero al fin y al cabo, ¿qué es CORS, qué provoca errores y cómo evitarlos en nuestras APIs con Spring Boot?

##### CORS
CORS es un mecanismo utilizado para agregar encabezados HTTP que le indican a los navegadores que permitan que una aplicación web se ejecute en un origen y acceda a los recursos desde un origen diferente. Este tipo de acción se denomina cross-origin HTTP request. En la práctica, les dice a los navegadores si se puede acceder o no a un recurso en particular.

Pero, ¿por qué ocurren los errores? ¡Es hora de entender!

- Same-origin policy
Por defecto, una aplicación Front-end, escrita en JavaScript, solo puede acceder a los recursos ubicados en el mismo origen de la solicitud. Esto sucede debido a la política del mismo origen (same-origin policy), que es un mecanismo de seguridad de los navegadores que restringe la forma en que un documento o script de un origen interactúa con los recursos de otro. Esta política tiene como objetivo detener los ataques maliciosos.

- Dos URL comparten el mismo origen si el protocolo, el puerto (si se especifica) y el host son los mismos. Comparemos posibles variaciones considerando la URL https://cursos.alura.com.br/category/programacao:
  - URL		
  https://cursos.alura.com.br/category/front-end	
  - Resultado: Mismo origen	
  - Motivo: Solo camino diferente


Ahora, la pregunta sigue siendo: ¿qué hacer cuando necesitamos consumir una API con una URL diferente sin tener problemas con CORS? Como, por ejemplo, cuando queremos consumir una API que se ejecuta en el puerto 8000 desde una aplicación React que se ejecuta en el puerto 3000. ¡Compruébalo!
Al enviar una solicitud a una API de origen diferente, la API debe devolver un header llamado Access-Control-Allow-Origin. Dentro de ella es necesario informar los diferentes orígenes que serán permitidas de consumir la API, en nuestro caso: Access-Control-Allow-Origin: http://localhost:3000.
Puede permitir el acceso desde cualquier origen utilizando el símbolo * (asterisco): Access-Control-Allow-Origin: *. Pero esta no es una medida recomendada, ya que permite que fuentes desconocidas accedan al servidor, a menos que sea intencional, como en el caso de una API pública. Ahora veamos cómo hacer esto en Spring Boot correctamente.
##### Habilitación de diferentes orígenes en Spring Boot
- Para configurar el CORS y permitir que un origen específico consuma la API, simplemente cree una clase de configuración como la siguiente:
```
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}
```
http://localhost:3000 sería la dirección de la aplicación Front-end y .allowedMethods los métodos que se permitirán ejecutar. Con esto, podrás consumir tu API sin problemas desde una aplicación front-end.

##### DTO Java Record:
El patrón DTO, Data Transfer Object, que es básicamente usar a nivel de controller un objeto como intermediario para que mapee la información que nos llega desde nuestro cliente hacia nuestro API.

## 03 - Spring Data JPA(Java Persistence API)
#### Preparando el ambiente: MySQL
- Se utilizará MySQL como sistema de gestión de base de datos: Descargarlo de la pagina oficial https://www.mysql.com/

#### Agregando dependencias
- Utilizando Spring Initializr https://start.spring.io/ : Seleccionar Maven, Spring Boot version: 3.1.2 y buscar las siguientes dependencias, luego clic en Explore(para ver el proyecto que se genera) y Copiar las dependencias y pegarlas en el archivo pom.xml de nuestro proyecto y guardar o clic en el Boton de maven y clic en la rueda de refresh:
  - Spring Data JPA
  - MySQL Driver
  - Flyway Migration (Gestor de migracion de BD, Para mantener la BD como codigo versionado.)
    ![addDependencySpringInitializr.jpg](src/img-readme/addDependencySpringInitializr.jpg)
- Otra forma de agrgegar las dependencias es por medio del Maven Repository (https://mvnrepository.com/) el cual el proceso es ir buscar la dependencia y pegarla en el archivo pom.xml del proyecto.
#### Conexion a la Base de Datos:
##### configurar un data source: 

- Ir a la carpeta de resources en el archivo application.properties: Aquí se necesita agregar tres properties para que pueda funcionar el springdata esta son: URL del dataSourse, el nombre del usuario, la contraseña del usuario.
```#Database:
#url+direccion(localhost)+puerto(3306)+vollmed_api(nombre de la BD)
spring.datasource.url=jdbc:mysql://localhost/vollmed_api
spring.datasource.username=root
spring.datasource.password=admin 
```
##### Crear la Base de datos en MySQL(usando workbeanch)
- Utilize el comando:
```
  create database vollmed_api;
  use vollmed_api;
```
- Luego ejecute la aplicacion de springBoot deberá correr.
##### Para saber más: ¿Archivo properties o yaml?
- La configuración de una aplicación Spring Boot se realiza en archivos externos, y podemos usar el archivo de propiedades o el archivo YAML. En este “Para saber más”, abordaremos las principales diferencias entre ellos.

###### Archivo de propiedades
De forma predeterminada, Spring Boot accede a las configuraciones definidas en el archivo application.properties, que utiliza un formato clave=valor:
```java
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/clinica
spring.datasource.username=root
spring.datasource.password=root
```
Cada fila es una configuración única, por lo que necesitamos expresar datos jerárquicos usando los mismos prefijos para nuestras claves, es decir, necesitamos repetir los prefijos, en este caso spring y datasource.

###### Configuración YAML
- YAML es otro formato muy utilizado para definir datos de configuración jerárquicos, como se hace en Spring Boot.
- Tomando el mismo ejemplo de nuestro archivo application.properties, podemos convertirlo a YAML cambiando su nombre a application.yml y modificando su contenido a:
```java
spring:
datasource:
driver-class-name: com.mysql.cj.jdbc.Driver
url: jdbc:mysql://localhost:3306/clinica
username: root
password: root
```
- Con YAML, la configuración se ha vuelto más legible ya que no contiene prefijos repetitivos. Además de la legibilidad y la reducción de repeticiones, el uso de YAML facilita el almacenamiento de variables de configuración del entorno, como lo recomienda 12 Factor App, https://12factor.net/es/ una metodología conocida y utilizada que define 12 mejores prácticas para crear una aplicación moderna, escalable y de sencillo mantenimiento.

###### Pero después de todo, ¿qué formato usar?
- A pesar de las ventajas que nos aportan los archivos YAML frente al archivo properties, la decisión de elegir uno u otro es una cuestión de gusto personal. Además, no se recomienda tener ambos tipos de archivos en el mismo proyecto al mismo tiempo, ya que esto puede generar problemas inesperados en la aplicación.
- Si elige usar YAML, tenga en cuenta que escribirlo al principio puede ser un poco laborioso debido a sus reglas de tabulación.

#### Entidades JPA
- Usamos Lombok para reducir codigo, lo que hace es generar automáticamente el código que no tiene sentido escribirlo por ti mismo, por ejemplo:
  - Si tú queremos generar getters, agregamos arriba de la clase: @Getter    - Y automáticamente una vez que compile, Lombok va a generar automáticamente los getters para todos estos atributos.   
  - Si Necesito un constructor sin atributos, agregamos arriba de la clase: @NoArgsConstructor para un constructor default. 
  - Y para un constructor con todos los atributos poner @AllArgsConstructor.
    ![useLombok.jpg](src/img-readme/useLombok.jpg)

#### Interfaces repository
- Medico Repository - Con esta interfaz vamos a ser capaces de hacer todo el proceso de gestión con la BD a nivel del CRUD: crear, guardar objetos, listar, actualizar, etc, pero automáticamente. ¿Por qué?
  Porque vamos a extender de otra interfaz llamada JpaRepository y está interfaz es propia de Spring data también, pero JpaRepository necesita dos parámetros.
  - El primer parámetro es el tipo de objeto que yo voy a guardar aquí, por ejemplo, en este caso es Médico, que es la entidad que yo voy a guardar, es el tipo de entidad con el que yo voy a trabajar en este repositorio.
  - Segundo, necesito el tipo de objeto del id. Entonces en este caso sería un Long.
```java
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> { //Recibe la entidad a guardar y su tipo de Id de esa entidad (clase)
}
```

#### Para saber más: ¿Qué hay de las clases DAO?
En algunos proyectos Java, dependiendo de la tecnología elegida, es común encontrar clases que siguen el patrón DAO, usado para aislar el acceso a los datos. Sin embargo, en este curso usaremos otro patrón, conocido como Repositorio.

Pero entonces pueden surgir algunas preguntas: ¿cuál es la diferencia entre los dos enfoques y por qué esta elección?

##### Patrón DAO
El patrón de diseño DAO, también conocido como Data Access Object, se utiliza para la persistencia de datos, donde su objetivo principal es separar las reglas de negocio de las reglas de acceso a la base de datos. En las clases que siguen este patrón, aislamos todos los códigos que se ocupan de conexiones, comandos SQL y funciones directas a la base de datos, para que dichos códigos no se esparzan a otras partes de la aplicación, algo que puede dificultar el mantenimiento del código y también el intercambio de tecnologías y del mecanismo de persistencia.

###### Implementación
Supongamos que tenemos una tabla de productos en nuestra base de datos. La implementación del patrón DAO sería la siguiente:

Primero, será necesario crear una clase básica de dominio Producto:
```java
public class Producto {
private Long id;
private String nombre;
private BigDecimal precio;
private String descripcion;

    // constructores, getters y setters
}
```

A continuación, necesitaríamos crear la clase ProductoDao, que proporciona operaciones de persistencia para la clase de dominio Producto:

```java
public class ProductoDao {

    private final EntityManager entityManager;

    public ProductoDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Producto producto) {
        entityManager.persist(producto);
    }

    public Producto read(Long id) {
        return entityManager.find(Producto.class, id);
    }

    public void update(Producto producto) {
        entityManger.merge(producto);
    }

    public void remove(Producto producto) {
        entityManger.remove(producto);
}

}
```

En el ejemplo anterior, se utilizó JPA como tecnología de persistencia de datos de la aplicación.

###### Padrón Repository
Según el famoso libro Domain-Driven Design de Eric Evans:

- El repositorio es un mecanismo para encapsular el almacenamiento, recuperación y comportamiento de búsqueda, que emula una colección de objetos.

En pocas palabras, un repositorio también maneja datos y oculta consultas similares a DAO. Sin embargo, se encuentra en un nivel más alto, más cerca de la lógica de negocio de una aplicación. Un repositorio está vinculado a la regla de negocio de la aplicación y está asociado con el agregado de sus objetos de negocio, devolviéndolos cuando es necesario.

Pero debemos estar atentos, porque al igual que en el patrón DAO, las reglas de negocio que están involucradas con el procesamiento de información no deben estar presentes en los repositorios. Los repositorios no deben tener la responsabilidad de tomar decisiones, aplicar algoritmos de transformación de datos o brindar servicios directamente a otras capas o módulos de la aplicación. Mapear entidades de dominio y proporcionar funcionalidades de aplicación son responsabilidades muy diferentes.

- Un repositorio se encuentra entre las reglas de negocio y la capa de persistencia:

  - Proporciona una interfaz para las reglas comerciales donde se accede a los objetos como una colección;
  - Utiliza la capa de persistencia para escribir y recuperar datos necesarios para persistir y recuperar objetos de negocio.

Por lo tanto, incluso es posible utilizar uno o más DAOs en un repositorio.

##### ¿Por qué el padrón repositorio en lugar de DAO usando Spring?
El patrón de repositorio fomenta un diseño orientado al dominio, lo que proporciona una comprensión más sencilla del dominio y la estructura de datos. Además, al usar el repositorio de Spring, no tenemos que preocuparnos por usar la API de JPA directamente, simplemente creando los métodos, que Spring crea la implementación en tiempo de ejecución, lo que hace que el código sea mucho más simple, pequeño y legible.

#### Migraciones flyway
- Es un sistema de version de BD, las tablas esta versionadas. Almacenará las tablas de la BD, otra opcion es crear las tablas de la BD por Workbeanch MySQL
- Clic en carpeta Resource -> clic en New -> clic en Directory -> escribir nombre: DB --> Luego dentro de esta carpeta crear una carpeta llamada migrations: Clic en la Carpeta db -->clic en New -> Clic en Directory --> Escribir nombre: migration
- Los migration se gestionan con archivos .sql, lo que hace por principio ¿qué es? Ve el archivo sql y lo va a ejecutar sobre la base de datos con la que ya tiene conexión.
- Crear archivo SQL
  - Clic derecho sobre la carpeta migration -> Clic en New -> clic en File -> dar nombre al archivo: V1__create-table-medicos.sql    
    - V1 y los dos guiones bajos es el patrón de Flyway para identificar qué es una migration y él va a decidir si se ejecuta o no.
    - create-table-medicos la segunda parte del nombre es algo autoexplicativo sobre lo que esta migration está haciendo. En este caso estamos creando una tabla llamada médicos.
    - Y la tercera parte es la extensión del archivo .sql.
    - Al ejecutarla, Flyway maneja las versiones en su propia tabla de modo que él identifica que V1 ya fue ejecutado por lo tanto se pueden ver las tablas que se crean en la BD.
  - Flyway se llama un sistema de versión de base de datos, porque si necesitas aplicar más migrations, por ejemplo, creas un V2, Flyway va a detectar que no la tiene y simplemente va a ejecutarla. En caso de ya la tuviera al ejecutar nuevamente
  el servidor nos dira que esta al dia y no creará la tabla.
 
#### Validacion
- Para validar a nivel de la API. Ir a Spring initializr, buscar y agregar la dependencia Validation.
```java
  <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-validation</artifactId>
	</dependency>
```
- Se recomienda hacer validaciones en la tabla de BD y en los parametros de entrada de la API.

- Tenemos los parámetros aquí, por ejemplo, nombre, ya sabemos que no puede llegar vacío, entonces bean validation a través de anotaciones nos da facilidades, como por ejemplo si le quiero poner aquí @NotNull va a validar que nombre nunca llegue null.
```java
public record DatosRegistroMedico(
        //@NotNull //no lleguen valores nulos
        @NotBlank //valida que no llegue valores blanco y nulos
        String nombre,
        @NotBlank
        @Email //valida que el formato ingresado sea un email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d(4,6)") //con expresiones regulares acepta, numeros de de 4 a 6 digitos
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid //valide que la direccion que se recibe contenga toda la informacion. Todo sea válido.
        DatosDireccion direccion) {
}
```
- En la tabla medicos vemos que el email y el documento, deben de ser unicos se les agrega 'unique'.
```SQL
create table medicos(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documento varchar(6) not null unique,
    especialidad varchar(100) not null,
    calle varchar(100) not null,
    distrito varchar(100) not null,
    complemento varchar(100),
    numero varchar(20),
    ciudad varchar(100) not null,
    primary key(id)
);
```
- Tambien la validacion debe darse en el controlador. Sin esta anotación, Spring no activará el proceso de Bean Validation.
  - @valid lo que él nos dice es él va a validar que en DatosRegistroMédico todo sea válido.
```java
  @PostMapping //recibe datos (JSON) desde Insomnia.
  public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){ //Para indicar a spring que es un parametro se usa requestBody
  medicoRepository.save(new Medico(datosRegistroMedico));
  }
```
###### validación con Bean Validatión
- El Bean Validation se compone de varias anotaciones que se deben agregar a los atributos en los que queremos realizar las validaciones. Hemos visto algunas de estas anotaciones, como @NotBlank, que indica que un atributo String no puede ser nulo o vacío.
- Sin embargo, existen decenas de otras anotaciones que podemos utilizar en nuestro proyecto, para los más diversos tipos de atributos. Puede consultar una lista de las principales anotaciones de Bean Validation en la documentación oficial de la especificación. https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#builtinconstraints

#### Nueva migración
- Una nueva migracion agregar el campo telefono a la tabla medicos.
  - Crear un archivo llamado: V2__alter-table-medicos-add-telefono.sql sql en la carpeta db.migration dentro de la carpeta resources.
  - Dentro del archivo V2__alter-table-medicos-add-telefono.sql se agrega el codigo sql para agregar una nueva columna a la table medicos de la BD.
```sql
alter table medicos add telefono varchar(20) not null;
```
- Agregar el atributo telefono a la entidad Medico y en DatosRegistroMedico, asi tambien se debe agregar en los constructores de las entidades.

- Al ejecutar la app se creará la version 2 en nuestra app y en la BD se vera reflejado.
  ![versionCreadasFlyway.jpg](src/img-readme/versionCreadasFlyway.jpg)

##### Error en la migración
- Como se indicó a lo largo de esta clase, es importante detener siempre el proyecto al crear los archivos de migración, para evitar que Flyway los ejecute antes de tiempo, con el código aún incompleto, lo que podría causar problemas.
- Sin embargo, eventualmente puede ocurrir que nos olvidemos de detener el proyecto y se produzca un error al intentar inicializar la aplicación. En este caso, se mostrará el siguiente error al intentar inicializar la aplicación:
```
Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'flywayInitializer' defined in class path resource [org/springframework/boot/autoconfigure/flyway/FlywayAutoConfiguration$FlywayConfiguration.class]: Validate failed: Migrations have failed validation
```
- Observe en el mensaje de error que se indica que alguna migración falló, lo que impide que el proyecto se inicie correctamente. Este error también puede ocurrir si el código de migración no es válido y contiene algún fragmento de SQL escrito incorrectamente.
- Para solucionar este problema será necesario acceder a la base de datos de la aplicación y ejecutar el siguiente comando sql:
```
delete from flyway_schema_history where success = 0;
```
- El comando anterior se usa para eliminar de la tabla Flyway todas las migraciones cuya ejecución falló. Después de eso, simplemente corrija el código de migración y ejecute el proyecto nuevamente.
##### Para saber más: Lombok
- Lombok, como se dijo anteriormente, es una biblioteca de Java especialmente enfocada en la reducción de código y en la productividad en el desarrollo de proyectos en ese lenguaje.
- Él utiliza la idea de anotaciones (familiar a Spring ¿no?) para generar códigos en el tiempo de compilación. Pero recuerde que no vemos el código generado, y tampoco es posible cambiar lo que se ha generado.
- Puede ser una buena herramienta aliada a la hora de escribir clases complejas, siempre que el desarrollador tenga conocimiento sobre ella. Para más información vea la documentación de Lombok: https://projectlombok.org/
##### Para saber más: Anotación @Autowired en Spring
- Traducido del inglés, la palabra Autowired sería ''un cable automático''. En el contexto del framework Spring, que utiliza como una de sus bases el patrón de diseño “Inyección de Dependencias”, la idea sirve para definir una inyección automática en un determinado componente del proyecto Spring, ese componente puede ser atributos, métodos e incluso constructores.
- Esta anotación se permite con la ayuda de la anotación @SpringBootApplication, en el archivo de configuración de Spring, disponible cada vez que se crea un proyecto Spring.
- Al marcar un componente con la anotación @Autowired le estamos diciendo a Spring que el componente es un punto donde se debe inyectar una dependencia, en otras palabras, el componente se inyecta en la clase que lo posee, estableciendo una colaboración entre componentes.
- Para más información sobre la anotación, echa un vistazo a la documentación oficial: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html

####   Proceso de creacion de la funcionalidad de registro de pacientes

- Deberá crear la entidad Paciente:
```java
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;

    @Embedded
    private Direccion direccion;

    public Paciente(DatosRegistroPaciente datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documentoIdentidad = datos.documentoIdentidad();
        this.direccion = new Direccion(datos.direccion());
    }
}
```

- A continuación, deberá crear un repositorio:
```java
public interface PacienteRepository extends JpaRepository<Paciente, Long> {}
```
- Luego deberá cambiar las clases Controller y DTO:
```java
@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void registrar(@RequestBody @Valid DatosRegistroPaciente datos) {
        repository.save(new Paciente(datos));
    }
```
```java
public record DatosRegistroPaciente(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotBlank String telefono,
        @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}") String documentoIdentidad,
        @NotNull @Valid DatosDireccion direccion
) {
}
```

- Y, por último, deberá crear una migración (¡Atención! ¡Recuerde detener el proyecto antes de crear la migración!):
```sql
create table pacientes(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    documentoIdentidad varchar(14) not null unique,
    telefono varchar(20) not null,
    urbanización varchar(100) not null,
    distrito varchar(100) not null,
    codigoPostal varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    provincia varchar(100) not null,
    ciudad varchar(100) not null,

    primary key(id)
);
```
### Request GET
#### Produciendo Datos
- Consideraciones:
  - Informacion Requerida del medico: Nombre, Especialidad, Documento y Email.
  - Reglas de negocio: Ordenado ascendentemente, paginado, maximo 10 registros por paginas.


#### Para saber más: ¿DTO o entidades?

- Usamos DTO para representar los datos que recibimos y devolvemos a través de la API, pero probablemente se esté preguntando "¿Por qué, en lugar de crear un DTO, no devolvemos directamente la entidad JPA en el Controller?". Para hacer esto, simplemente cambie el método list en el Controller a:
```java
@GetMapping
public List<Medico> listar() {
return repository.findAll();
}
```
- De esa forma, el código sería más ligero y no necesitaríamos crear el DTO en el proyecto.
- Pero, ¿es esto realmente una buena idea?

###### Problemas de recepción/devolución de la entidad JPA
- De hecho, es mucho más simple y cómodo no usar DTO, sino tratar directamente con entidades JPA en los Controllers. Sin embargo, este enfoque tiene algunas desventajas, incluida la vulnerabilidad de la aplicación a los ataques de Mass Assignment.
- Uno de los problemas es el hecho de que, al devolver una entidad JPA en un método del Controller, Spring generará el JSON que contiene todos sus atributos, y este no siempre es el comportamiento que queremos.
- Eventualmente podemos tener atributos que no queremos que sean devueltos en el JSON, ya sea por razones de seguridad, en el caso de datos sensibles, o incluso porque no son utilizados por clientes API.

###### Uso de la anotación @JsonIgnore
- En esta situación, podríamos usar la anotación @JsonIgnore, que nos ayuda a ignorar ciertas propiedades de una clase Java cuando se serializa en un objeto JSON.
- Su uso consiste en agregar la anotación a los atributos que queremos ignorar cuando se genera el JSON. Por ejemplo, supongamos que tenemos una entidad JPA 'Empleado', en la que queremos ignorar el atributo 'salario':
```java
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Empleado")
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    @JsonIgnore
    private BigDecimal salario;

    //restante del código omitido…
}
```
- En el ejemplo anterior, el atributo 'salario' de la clase 'Empleado' no se mostrará en las respuestas JSON y el problema estaría resuelto.
- Sin embargo, puede haber algún otro endpoint de la API en el que necesitemos enviar el salario de los empleados en el JSON, en cuyo caso tendríamos problemas, ya que con la anotación @JsonIgnore tal atributo nunca se enviará en el JSON, y al eliminar la anotación se enviará el atributo siempre. Por lo tanto, perdemos la flexibilidad de controlar cuándo se deben enviar ciertos atributos en el JSON y cuándo no.

##### DTO
- El patrón DTO (Data Transfer Object) es un patrón arquitectónico que se usó ampliamente en aplicaciones Java distribuidas (arquitectura cliente/servidor) para representar los datos que eran enviados y recibidos entre aplicaciones cliente y servidor.
- El patrón DTO puede (y debe) usarse cuando no queremos exponer todos los atributos de alguna entidad en nuestro proyecto, una situación similar a los salarios de los empleados que discutimos anteriormente. Además, con la flexibilidad y la opción de filtrar qué datos se transmiten, podemos ahorrar tiempo de procesamiento.

##### Bucle infinito que causa StackOverflowError
- Otro problema muy recurrente cuando se trabaja directamente con entidades JPA ocurre cuando una entidad tiene alguna auto-relación o relación bidireccional. Por ejemplo, considere las siguientes entidades JPA:
```java
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Producto")
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = “id_categoria”)
    private Categoria categoria;

    //restante del código omitido…
}
```
```java
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Categoria")
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @OneToMany(mappedBy = “categoria”)
    private List<Producto> productos = new ArrayList<>();

    //restante del código omitido…
}
```
- Al devolver un objeto de tipo 'Producto' en el Controller, Spring tendría problemas para generar el JSON de este objeto, lo que provocaría una excepción de tipo 'StackOverflowError'. Este problema ocurre porque el objeto producto tiene un atributo de tipo Categoría, que a su vez tiene un atributo de tipo Lista<Producto>, lo que provoca un bucle infinito en el proceso de serialización a JSON.
- Este problema se puede resolver usando la anotación @JsonIgnore o usando las anotaciones @JsonBackReference y @JsonManagedReference, pero también se puede evitar usando un DTO que represente solo los datos que se deben devolver en el JSON. 










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



