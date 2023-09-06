package med.voll.api.domain.consulta.validaciones;


import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

//Regla de Negocio:Las consultas deben programarse con al menos 30 minutos de anticipacion.

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas{ //Se implementa la interfaz ValidadorDeConsultas

    public void validar(DatosAgendarConsulta datos){
        var ahora = LocalDateTime.now(); //momento actual,
        var horaDeConsulta = datos.fecha(); //hora de la consulta

        //Si la diferencia entre horaDeConsulta es de menos de 30 minutos, entonces, esa consulta no puede ser realizada.
        var diferenciaDe30Min = Duration.between(ahora,horaDeConsulta).toMinutes()<30;

        if(diferenciaDe30Min){//si la diferencia es igual a 30 minutos de la hora actual a la hora de la consulta lanza la excepcion, no se puede realizar la consulta.
            throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipacion");
        }
    }
}
