package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosRegistroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //Para decirle a spring, que esos es un controller
@RequestMapping("/medicos") //mapear el path médicos en este controller
public class MedicoController {

    @Autowired(required = false) //Para definir la interfaz - No recomendable usar @Autowired ya que tendremos problemas al hacer pruebas unitarias
    private MedicoRepository medicoRepository; //Instanciar nuestra interfaz MedicoRepository


    //valid lo que él nos dice es él va a validar que en DatosRegistroMédico todo sea válido.
    @PostMapping //recibe datos (JSON) desde Insomnia.
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){ //Para indicar a spring que es un parametro se usa requestBody
        medicoRepository.save(new Medico(datosRegistroMedico));
    }

    //Metodo Obtiene un listado de todos los medicos
    @GetMapping
    public List<Medico> listadoMedico(){
       return medicoRepository.findAll(); //trae toda la lista de medicos. El metodo findAll viene de la extension JpaRepository de la interfaz MedicoRepository
    }
}
