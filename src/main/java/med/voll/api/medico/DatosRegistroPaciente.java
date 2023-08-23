package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;

public record DatosRegistroPaciente(
    @NotBlank
    String nombre,
    @NotBlank
    @Email
    String email,
    @NotBlank String telefono,
    @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}") String documento_identidad,

   // Acepta: Tres dígitos, un punto opcional. Tres dígitos, un punto opcional. Tres dígitos, un guion opcional. Dos dígitos.

    @NotNull @Valid DatosDireccion direccion
){
}
