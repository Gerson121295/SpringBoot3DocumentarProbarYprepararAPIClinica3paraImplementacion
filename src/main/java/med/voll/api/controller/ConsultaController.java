package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service; //se inyecta el servicio

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos)throws ValidacionDeIntegridad{
        var response = service.agendar(datos); //Response guarda los datos de la consulta

        return ResponseEntity.ok(response); //Se le envia response para mostrar los datos en Postman al crear una consulta.
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos){
        service.cancelar(datos);
        return ResponseEntity.noContent().build();
    }

}


/* // Pruebas Crear citas Metodo POST: http://localhost:8080/consultas
{
	"idPaciente":"1",
	"idMedico":"5",
	"fecha":"2023-10-04T10:15:30"
}
//Enviando la especialidad
{
	"idPaciente":"1",
	"especialidad":"GINECOLOGIA",
	"fecha":"2023-10-06T10:15:30"
}

//Pruebas: Cancelamiento de Consultas Metodo DELETE: http://localhost:8080/consultas
    {
	    "idConsulta":"5",
        "motivo":"PACIENTE_DESISTIO"
    }


 */