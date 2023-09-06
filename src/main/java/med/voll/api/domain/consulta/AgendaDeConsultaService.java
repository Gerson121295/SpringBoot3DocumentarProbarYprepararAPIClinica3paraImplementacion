package med.voll.api.domain.consulta;


import med.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.domain.consulta.validacionescancelarconsulta.ValidadorCancelamientoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository; //inyeccion


    //Inyeccion de todas las validaciones - Forma no recomendada
    //@Autowired
   // private HorarioDeAnticipacion horarioDeAnticipacion;


/*  //Forma de Inyectar las validaciones: Design Pattern Strategy.
    Una lista que está recibiendo una interfaz.
    la anotación @Autowired, Spring automáticamente sabe que todos los elementos que estén implementando la interfaz ValidadorDeConsultas van a ser inyectados dentro de esta lista y van a encontrarse disponibles.
    De esa forma cuando se agreguen nuevas validaciones o se elimine alguna de esas validaciones, nosotros no tendríamos que intervenir dentro de la clase de servicio, sino simplemente eliminar esa clase y
    Spring automáticamente sabe que ya esa clase no existe dentro de la lista.
*/
    @Autowired
    List<ValidadorDeConsultas> validadores; //La lista recibe la interfaz ValidadorDeConsultas y la nombra como validadores.

    @Autowired
    private List<ValidadorCancelamientoDeConsulta> validadoresCancelamiento;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos){
        //me comunico a la BD por medio del pacienteRepository, si no se encuentra el id del paciente lanza la excepcion
        if(!pacienteRepository.findById(datos.idPaciente()).isPresent()){ //Si id paciente no fue encontrado lanza la exepcion
            throw new ValidacionDeIntegridad("Este id para el paciente no fue encontrado");
        }

        // método en la interfaz Repository en Spring Data llamado existsById. Realiza una consulta a la base de datos para verificar si existe un registro con un determinado ID
        if(datos.idMedico()!=null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("Este id para el medico no fue encontrado");
        }

        //Validaciones
        validadores.forEach(v -> v.validar(datos)); //Con el forEach recorre todos los elementos que se encuentran dentro de esa lista.
        // Lista formada por cada uno de los validadores creados los cuales utilizamos un arrow function para utilizar el método validar pasándole los datos que estamos recibiendo como parámetro.

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        //var medico = medicoRepository.findById(datos.idMedico()).get();
        var medico = seleccionarMedico(datos);

        if(medico==null){
            throw new ValidacionDeIntegridad("No existen medicos disponibles para este horario y especialidad");
        }

        var consulta = new Consulta(medico, paciente, datos.fecha());
        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);
    }

    //Metodo para cancelar consulta
    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())){
            throw new ValidacionDeIntegridad("Id de la consulta informado no exite!");
        }

        validadoresCancelamiento.forEach(v-> v.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }

    //Método permite crear el algoritmo para seleccionar un médico que sea aleatorio y que se encuentre disponible en la fecha y que atienda las necesidades del paciente.
    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico()!=null){//si idMedico no es nulo
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad()==null){
            throw new ValidacionDeIntegridad("Debe seleccionarse una especialidad para el medico");
        }

        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());
    }


}
