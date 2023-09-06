package med.voll.api.infra.errores;


//EL fin de esta clase es: Tratar los errores globalmente a nivel del controller, de mi proyecto, no a nivel de cada método en específico del controlador.

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/*
Esto actúa como una especie de proxy para todos nuestros controllers, por algo está como rest controller, para interceptar las llamadas en caso suceda algún tipo de excepción.
Esto es suficiente como para interceptar nuestros métodos de controller y atrapar alguna excepción que sea lanzada
 */

@RestControllerAdvice //En las API Rest, las clases de manejo de excepciones deben anotarse con @RestControllerAdvice
public class TratadorDeErrores {


    //Obtener por ID pero id no existe.
//con eso le digo “ExceptionHandler, cuando tú en este RestControllerAdvice identifiques que EntityNotFoundException es lanzado, como estás anotado como @ExceptionHandler, entonces vas a retornar este código de aquí, esta respuesta de aquí, con notFound.
    @ExceptionHandler(EntityNotFoundException.class)  //@ExeptionHandler: //Para indicarle a spring que este va  a ser llamado en caso de que haya una excepcion de algun tipo sea detectada, adentro del parentesis va la excepcion a atrapar.
  public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    //Para el ingreso de medico - Paylod no va completo con todos los campos solicitados.
    @ExceptionHandler(MethodArgumentNotValidException.class)//Cunado la exeption MethodArgumentNotValidException es lanzada.
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    //Muestra el mensaje: "Este id para paciente no fue encontrado." en Postman o Insomnia al ocurrir un error - Id paciente no encontrado
    @ExceptionHandler(ValidacionDeIntegridad.class)//Cunado la exeption MethodArgumentNotValidException es lanzada.
    public ResponseEntity errorHandlerValidacionesDeIntegridad(Exception e){
       return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)//Cunado la exeption MethodArgumentNotValidException es lanzada.
    public ResponseEntity errorHandlerValidacionesDeNegocio(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    //DTO para tratar exception: MethodArgumentNotValidException - Registrar medico, Para devolver los campos que hacen falta cuando no se ingresen todos.
    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }



}













