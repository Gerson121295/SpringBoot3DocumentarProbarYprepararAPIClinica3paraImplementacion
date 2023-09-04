package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.domain.direccion.DatosDireccion;


/*
DatosRegistroMedico es nuestra DTO que esta recibiendo lo que viene del API
 */

/*
Agregada la dependencia de Validation - Entonces, ya estamos listos para validar a nivel del API, no de la BD. Deberíamos validar donde están llegando los datos ¿dónde deberíamos hacer las validaciones? En el DTO.
Por lo que es en DatosRegistroMedico.
*/


//Tenemos los parámetros aquí, por ejemplo, nombre, ya sabemos que no puede llegar vacío, entonces bean validation a través de anotaciones nos da facilidades, como por ejemplo si le quiero poner aquí @NotNull va a validar que nombre nunca llegue null.

public record DatosRegistroMedico(

        //@NotNull //no lleguen valores nulos
        @NotBlank //valida que no llegue valores blanco y nulos
        String nombre,
        @NotBlank
        @Email //valida que el formato ingresado sea un email
        String email,
        @NotBlank
        @Size(min = 0, max = 15)
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //con expresiones regulares acepta, numeros de 4 a 6 digitos
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid //valide que la direccion que se recibe contenga toda la informacion.
        DatosDireccion direccion) {

}
