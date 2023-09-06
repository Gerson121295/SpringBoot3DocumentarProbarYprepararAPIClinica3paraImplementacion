package med.voll.api.domain.consulta.validaciones;


import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Regla de negocio: No permita programar más de una consulta en el mismo día para el mismo paciente.

@Component //indica a spring ya esta operativa para no estar instanciandola
public class PacienteSinConsulta implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository repository;
    public void validar(DatosAgendarConsulta datos){

        //Fecha en la que el paciente puede asignar la consulta.
        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        //Verificar pacienteConConsulta: Verificar en consultas si existe un id para ese paciente en la BD, con una fecha entre ese intervalo que sería las 7:00 y las 18:00 de la tarde.
        var pacienteConConsulta = repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(),primerHorario,ultimoHorario);

        if(pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta para ese dia");
        }
    }
}
