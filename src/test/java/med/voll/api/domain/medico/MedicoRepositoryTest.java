package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.paciente.DatosRegistroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest //permitir utilizar métodos, relacionadas a la capa de persistencia. Por lo que se debe configurar una BD. puede ser local o usando H2 o una remoto MySQL
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //para indicar que se va a utilizar una BD externa y que no se va a reemplazar la base de datos que se esta utilizando previamente. Indica que no se va a utilizar la BD en memoria h2, que va a instalar, sino una BD externa.
@ActiveProfiles("test") //Indica el perfil de BD a utilizar
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository; //Inyectar

    @Autowired
    private TestEntityManager em;

//El entity manager es el encargado de realizar el puente entre nuestra aplicación y la BD. Cuando utilizamos el entity manager en las aplicaciones, tenemos que instanciarlo a través de Entity Manager Factory.
//Pero acá, Spring framework nos permite utilizar el TestEntityManager, que él se va a autoinstanciar únicamente para realizar las pruebas.

    //Escenario 1:
    //El caso en el que el médico se encuentra ocupado con otro paciente, retornaria nulo.

    @Test
    @DisplayName("Debería retornar nulo cuando el médico se encuentre en consulta con otro paciente en ese horario.") //@DisplayName se coloca la operación que va a realizar este método de prueba.
    //La consulta seleccionarMedicoConEspecialidadEnFecha se encarga de verificar si en la BD ese médico con esa especialidad y esa fecha se encuentra disponible. En caso que no se encuentre disponible retorna nulo y en caso de que se encuentre disponible, va a retornar el médico.
    void seleccionarMedicoConEspecialidadEnFechaEscenario1() {

    //Fases de una prueba:
        //Fase 1: Given (dado un conjunto de valores iniciales)
        //Fase 2: When (cuando se realiza una acción)
        //Fase 3: Then (Entonces tenemos que comparar que ese valor que estamos buscando en la BD efectivamente reciba el resultado de ese dato. (Escenario 1: en este caso sería nulo.)

    //Given
        //Registrar un horario dentro del funcionamiento de la clinica
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)) //Dia en especifico
                .atTime(10,0); //Hora

        //Para prueba se Registra un medico un paciente y una consulta
        var medico = registrarMedico("Jose","j@mail.com","123456",Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("antonio","a@mail.com","111.222.333-46");
        registrarConsulta(medico,paciente,proximoLunes10H); //Medico, paciente, fecha de consulta
    //En la especialidad del medico, los enumeradores vamos a seleccionar CARDIOLOGIA para verificar efectivamente que esa especialidad que estamos buscando en el repositorio coincida con ese médico que estamos registrando en la BD.

    //When
        //Hacer la consulta del médico, seleccionar un médico con especialidad en esa fecha, aquí estamos comparando ese valor de médico que estamos trayendo de la BD utilizando el repositorio, y vamos a verificar si ese valor es nulo.
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA, proximoLunes10H); //Compara con el medico registrado anteriormente.

    //Then
        //Para realizar las pruebas tenemos de JUnit las diferentes clases estáticas de las clases de assertion. Esas clases nos permiten hacer diferentes comparaciones y retorna verdadero o falso.
        assertThat(medicoLibre).isNull(); //Verificar  si ese valor es nulo.
    }

//Test Escenario 2:
// En el caso que el médico sí se encuentra disponible para ese paciente.
//Cuando el médico se encontraba disponible para esa especialidad y en ese horario, nosotros retornábamos el médico que fue algún médico aleatorio, que en el caso sería el único médico que teníamos registrado.

    @Test
    @DisplayName("Deberia retornar un medico cuando realice la consulta en la BD en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaEscenario2() {

        //given
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medico=registrarMedico("Jose","j@mail.com","123456",Especialidad.CARDIOLOGIA);

        //when
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA,proximoLunes10H);

        //then  //cuando realizamos la búsqueda en la base de datos, nosotros verificamos que esa búsqueda era igual al médico, el único médico que teníamos registrado.
        assertThat(medicoLibre).isEqualTo(medico); //a la hora de realizar una búsqueda en la BD, deberíamos encontrar que el médico que estamos buscando, el médico libre, sea igual al médico que estamos instanciando.
    }



    //Metodos para registrar un medico un paciente y una consulta
/*
Entonces hay dos formas de registrar ese médico.
Este caso registramos el médico mediante la construcción de métodos internos en la clase de prueba, nosotros también podríamos utilizar flyway para registrar tanto el paciente como el médico y la consulta y tenerlo interno
dentro de la base de datos de prueba, recordando que el nombre de la base de datos de prueba se guía por vollmed_api_test, diferente de la base de datos vollmed_api.
 */


    //Este metodo recibe el medico, paciente y paciente que se envia en registrarConsulta. -  Se registro arriba en registrarConsulta
    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad){
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
       em.persist(medico);
       return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new DatosRegistroMedico(
                nombre,
                email,
                "61999999999",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento) {
        return new DatosRegistroPaciente(
                nombre,
                email,
                "61999999999",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                " loca",
                "azul",
                "acapulpo",
                "321",
                "12"
        );
    }

}
















