package med.voll.api.domain.consulta.validacionescancelarconsulta;

import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;

public interface ValidadorCancelamientoDeConsulta {

    public void validar(DatosCancelamientoConsulta datos); //firma del metodo

}
