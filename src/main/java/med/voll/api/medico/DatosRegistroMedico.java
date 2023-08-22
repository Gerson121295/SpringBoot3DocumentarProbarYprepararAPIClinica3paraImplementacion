package med.voll.api.medico;

import med.voll.api.direccion.DatosDireccion;


/*
DatosRegistroMedico es nuestra DTO que esta recibiendo lo que viene del API
 */

public record DatosRegistroMedico(String nombre, String email, String documento, Especialidad especialidad, DatosDireccion direccion) {
}
