package med.voll.api.domain.medico;


//Para especificar que parametros va a mostrar el metodo listadoMedicos del MedicoController.
public record DatosListadoMedico(//Especificamos los parametros a mostrar en el listado
        Long id, String nombre, String especialidad, //se  agrego Long id para el metodo Put actualizar medico
        String documento, String email
) {

    public DatosListadoMedico(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(),
                medico.getDocumento(), medico.getEmail());
    }
}
