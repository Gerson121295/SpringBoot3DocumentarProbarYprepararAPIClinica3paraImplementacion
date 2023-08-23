package med.voll.api.medico;


//Para especificar que parametros va a mostrar el metodo listadoMedicos del MedicoController.
public record DatosListadoMedico(//Especificamos los parametros a mostrar en el listado
        String nombre, String especialidad, String documento, String email
) {

    public DatosListadoMedico(Medico medico) {
        this(medico.getNombre(), medico.getEspecialidad().toString(),
                medico.getDocumento(), medico.getEmail());
    }
}
