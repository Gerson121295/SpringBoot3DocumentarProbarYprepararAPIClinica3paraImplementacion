package med.voll.api.domain.consulta;


import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ConsultaRepository consultaRepository; //inyeccion

    public void agendar(DatosAgendarConsulta datos){
        //me comunico a la BD por medio del pacienteRepository
        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){ //Si id paciente no fue encontrado lanza la exepcion
            throw new ValidacionDeIntegridad("este id para el paciente no fue encontrado");
        }
// método en la interfaz Repository en Spring Data llamado existsById. Realiza una consulta a la base de datos para verificar si existe un registro con un determinado ID
        if(datos.idMedico()!=null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("este id para el medico no fue encontrado");
        }

        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        //var medico = medicoRepository.findById(datos.idMedico()).get();
        var medico = seleccionarMedico(datos);

        var consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
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
