package med.voll.api.domain.medico;


import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

//Para actualizar se creo un nuevo DTO DatosActualizarMedico
//Basado en los requerimientos solo podemos actualizar: Nombre, Documento y Direccion. por lo que esos serian los parametros.
public record DatosActualizarMedico(
        @NotNull Long id, String nombre, String documento, DatosDireccion direccion//para actualizar necesitamos el ID por lo que es obligatorio se agrega @NotNull.
) {

}
