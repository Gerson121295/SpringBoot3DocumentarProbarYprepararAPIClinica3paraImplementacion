package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

//Regla de negocio: 1.Horario de atencion de la clinica es lunes a sabado de 07:00 a 19:00 horas.
@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas{
    public void validar(DatosAgendarConsulta datos){
    //Verificar que no sea domingo, ya que el horario de atención de la clínica es de lunes a sábado, ni que se encuentre fuera del horario de atención que es de 7:00am y 19:00pm.

        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek()); //si se cumple estamos en domingo.

        //Var guarda el valor de la comparacion, de fecha de la consulta y si es menor a 7 o si es mayor a 19
        var antesDeHoraApertura = datos.fecha().getHour()<7;
        var despuesDeCierre = datos.fecha().getHour()>19;

    //Si la comparación entre domingo y la fecha de la consulta es igual, va a retornar un valor de true. Entonces nos encontramos en el domingo. y la comparacion de hora en <7am o >19pm horario no habil lanza la excepcion
        if(domingo || antesDeHoraApertura || despuesDeCierre) { //si domingo es verdadero enviamos una excepcion
            throw new ValidationException("El horario de atencio de la clínica es de lunes a sábado, de 07:00 a 19:00 horas");
        }
    }
}
