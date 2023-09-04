package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroPaciente(
    @NotBlank
    String nombre,
    @NotBlank
    @Email
    String email,
    @NotBlank
    @Size(min = 0, max = 15)
    String telefono,
    @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
    String documento,
     //Acepta: Tres dígitos, un punto opcional. Tres dígitos, un punto opcional. Tres dígitos, un guion opcional. Dos dígitos.

    @NotNull @Valid DatosDireccion direccion){
}


