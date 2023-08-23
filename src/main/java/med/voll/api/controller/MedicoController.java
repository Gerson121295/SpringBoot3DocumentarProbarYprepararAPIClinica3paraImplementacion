package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DatosListadoMedico;
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

/*
    //Metodo Obtiene un listado de todos los medicos con todos los parametros de la entidad medicos.
    @GetMapping
    public List<Medico> listadoMedico(){
       return medicoRepository.findAll(); //trae toda la lista de medicos. El metodo findAll viene de la extension JpaRepository de la interfaz MedicoRepository
    }
/*

    /*
    - Consideraciones:
  - Informacion Requerida del medico: Nombre, Especialidad, Documento y Email.
  - Reglas de negocio: Ordenado ascendentemente, paginado, maximo 10 registros por paginas.
     */

    // Metodo lista medicos con los parametros Nombre, Especialidad, Documento y Email.
    @GetMapping
    //Para mostrar los datos requeridos se usa un DTO llamado DatosListadoMedico en el cual se define los parametros a mostrar.
    public List<DatosListadoMedico> listadoMedico(){
        return medicoRepository.findAll().stream().map(DatosListadoMedico::new).toList();
    }

    //medicoRepository.findAll retorna un listado de médico como entidad, no de DatosListadoMedico. Para esto se hace uso del API Stream de Java, entonces aquí se le da stream.map y se le dice que
    //use los datos de listadoMedicos y que me cree un nuevo DatosListadoMedico con cada médico que traiga de la BD. Y se agrega un toList para que retorne en un objeto del tipo de lista.





}
