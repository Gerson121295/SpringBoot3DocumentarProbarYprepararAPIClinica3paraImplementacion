package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRespuestaMedico(

        //Datos a retornar al realizar el metodo update: actualizarMedico
        long id, String nombre, String email, String telefono,
        String documento, DatosDireccion direccion){
}

