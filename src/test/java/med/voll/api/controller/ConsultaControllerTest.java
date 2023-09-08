package med.voll.api.controller;

import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.medico.Especialidad;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest //@SpringBootTest es una anotación que nos permite trabajar con todos los componentes dentro del contexto de Spring, entonces acá podemos utilizar repositorios, servicios y los controladores.
@AutoConfigureMockMvc //esta anotación se encarga de configurar todos los componentes necesarios para realizar una simulación de una petición para ese controlador.
@AutoConfigureJsonTesters //Anotacion relacionada a JacksonTester de convertir objetos java a Json o viceversa.
@ActiveProfiles("test")
@SuppressWarnings("all")
class ConsultaControllerTest {

    /*
     Tenemos que verificar los diferentes estados de que nosotros podemos retornar cuando realizamos una petición.
    Entonces, cuando nosotros realizamos una requisición, tenemos el estado 400 en caso de que los valores sean inválidos,
    el estado 404 en el caso de que el usuario no haya sido encontrado, el estado 403 cuando la autorización no ha sido pasada
    que es cuando nosotros no pasamos el token y el estado 200, que es cuando el usuario ha sido encontrado exitosamente.
     */

    /*
    Hay dos estrategias para realizar las pruebas para este controlador, una donde nosotros inyectamos un servidor y realizamos una requisición de verdad dentro de la aplicación, para eso tendríamos que utilizar
    la anotación @RestAndPlay donde vamos a tener un servidor creando una petición en nuestra aplicación. El controlador va a recibir esa petición, va a realizar la búsqueda en el repositorio, va a realizar las
    validaciones con el componente de service y luego va a retornar el estado en caso de que sea encontrada o en caso de que los datos sean inválidos. Esa es una primera estrategia.

    La segunda estrategia es donde nosotros vamos a simular que se realizó la petición y nos vamos a enfocar únicamente en este componente, ignorando el resto de los componentes.
    Entonces vamos a simular esa petición, no vamos a crear un servidor sino que es vamos a simularlo y vamos a ignorar el restante de los componentes que serían los repositorios, los servicios o cualquier
    otro componente, y vamos a ver el Estado que retorna cuando realizamos la petición con esos datos.

    Entonces como nosotros vamos a hacer una simulación, vamos a hacer un mock, vamos a inyectar dentro de la clase de prueba el atributo MockMvc que lo vamos a llamar mvc.
     */

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosAgendarConsulta> agendarConsultaJacksonTester; //Convertir objeto java a objeto Json o viceversa

    @Autowired
    private JacksonTester<DatosDetalleConsulta> detalleConsultaJacksonTester; //Transformacion de Objeto Json a objeto Java.

    @MockBean //Simular esa clase de servicio para que no realice validaciones a la BD real.
    private AgendaDeConsultaService agendaDeConsultaService;

    //Escenario 1:
    @Test
    @DisplayName("Deberia retornar estado http 400 cuando los datos ingresados sean invalidos.")
    @WithMockUser
    void agendarEscenario1() throws Exception {

        //Given (dados los valores iniciales  //When (cuando se realiza la peticion)
        var response = mvc.perform(post("/consultas")).andReturn().getResponse(); //utilizar MockMvc para realizar la petición tipo POST que es la que se esta probando.

        //Then  //Tenemos que verificar que el estado, la respuesta que estamos recibiendo de esta requisición, de esta petición, sea el estado 400. Que es BAD_REQUEST.
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    //Escenario 2:
    //Probar lo que sería el estado 404, que es el caso en el que nosotros recibimos los valores, pero ese usuario o ese ese médico no se encuentra registrado dentro de la base de datos.
    //Vamos a probar el caso 200, que es cuando se envia un JSON y vamos a retornar un JSON a la aplicación que está haciendo la petición.  La descripción para este caso, debería retornar http 200 cuando los datos ingresados son válidos.
    //enviar una respuesta, realizada por MockMvc, que es una simulación de lo que sería la consulta, la petición a través de una API externa.

    @Test
    @DisplayName("Deberia retornar estado http 200 cuando los datos ingresados son validos")
    @WithMockUser
    void agendarEscenario2() throws Exception {
        //given
        var fecha = LocalDateTime.now().plusHours(1);//colocar 1 hora después, haciendo la especificación de que la consulta que se va a realizar va a ser 1 hora después del momento actual.
        var especialidad = Especialidad.CARDIOLOGIA; //coloca la especialidad dentro de un parametro
        var datos = new DatosDetalleConsulta(null, 2l, 5l, fecha); //Convertir este objeto java a objeto Json agregar la inyeccion private JacksonTester<DatosAgendarConsulta> AgendarConsultaJacksonTester;

        // when
        when(agendaDeConsultaService.agendar(ArgumentMatchers.any())).thenReturn(datos);//nueva forma que descubri //any() sig cualquier consulta va a agendar y retorna datos(DatosDetalleConsulta).
        //when(agendaDeConsultaService.agendar(new DatosAgendarConsulta(2l,5l,fecha, especialidad))).thenReturn(datos); //Dunciona enviandole los datos de la consulta a agendar.
        //when(agendaDeConsultaService.agendar(any())).thenReturn(datos);//code del profesor

        //Esa aplicación va a enviar los datos que se encuentran en un archivo del tipo DatosAgendarConsulta, que son transformados a través de JacksonTester. Él va a retornar una respuesta y vamos a validar esa respuesta con el primer assert, la primera verificación. verifica que esa respuesta sea igual al estado 200.
        var response = mvc.perform(post("/consultas")
                .contentType(MediaType.APPLICATION_JSON)//Se envia una aplicacion JSON
                .content(agendarConsultaJacksonTester.write(new DatosAgendarConsulta(2l,5l,fecha, especialidad)).getJson()) //Contenido JSON- Con esto le indicamos a JacksonTester que escriba un elemento del tipo DatosAgendarConsulta y que obtenga el JSON que es el que se va a enviar dentro de la respuesta, dentro de la aplicación simulada para obtener una respuesta.
        ).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value()); //verificar que esa respuesta sea igual al estado 200.

        //Verificar el cuerpo del JSON que estamos retornando, para eso tenemos que escribir cuál es el cuerpo del JSON que deberíamos recibir. Sería JsonEsperado, y vamos a transformar ese elemento de tipo Java, un elemento del tipo JSON para poder compararlo con el JSON que estamos retornando en la respuesta de la aplicación simulada.
        var jsonEsperado = detalleConsultaJacksonTester.write(datos).getJson();

        //obtener el contenido de la respuesta como string que sea igual al JsonEsperado.
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

        }

}


