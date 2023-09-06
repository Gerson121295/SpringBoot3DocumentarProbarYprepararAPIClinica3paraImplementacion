package med.voll.api.domain.consulta.validaciones;


import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//Regla de negocio: No permitir agendar citas con pacientes inactivos en el sistema.
@Component
public class PacienteActivo implements ValidadorDeConsultas{
//En caso de que el parámetro activo se encuentre falso, tenemos que enviar un mensaje de error indicando que esa consulta no se puede realizar para ese paciente.
    @Autowired
    private PacienteRepository repository;

    public void validar(DatosAgendarConsulta datos){
        if(datos.idPaciente()==null){ //Verificar que el ID de ese paciente no sea nulo.
            return;//Si el ID de ese paciente es igual a nulo, vamos a retornar
        }
        //No sea nulo, buscamos en el repositorio el parametro activo donde le paso el dato del paciente.
        var pacienteActivo = repository.findActivoById(datos.idPaciente());

        if(!pacienteActivo){
            throw new ValidationException("No se puede permitir agendar citas con pacientes inactivos en el sistema");
        }
    }
}
