package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController //Para decirle a spring, que esos es un controller
@RequestMapping("/medicos") //mapear el path médicos en este controller
public class MedicoController {

    @Autowired(required = false) //Para definir la interfaz - No recomendable usar @Autowired ya que tendremos problemas al hacer pruebas unitarias
    private MedicoRepository medicoRepository; //Instanciar nuestra interfaz MedicoRepository


    //valid lo que él nos dice es él va a validar que en DatosRegistroMédico todo sea válido.
    @PostMapping //recibe datos (JSON) desde Insomnia.
    public void registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico){ //Para indicar a spring que es un parametro se usa requestBody y @Valid valida que los datos lleguen correctamente
        medicoRepository.save(new Medico(datosRegistroMedico));
    }

//Probar metodo POST: http://localhost:8080/medicos
/*    {
	"nombre": "julia elias",
	"email": "juli.car@voll.med",
	"documento":"881114",
	"telefono":"51656222",
	"especialidad": "ORTOPEDIA",
	"direccion":{
		"calle": "calle 6",
		"distrito": "distrito 6",
		"ciudad": "Lima",
		"numero": "1",
		"complemento": "e"
	}
}
*/


/*
//Metodo Obtiene un listado de todos los medicos con todos los parametros de la entidad medicos.
 // Path a enviar para probar metodo: http://localhost:8080/medicos

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

/*
    // Metodo lista medicos con los parametros Nombre, Especialidad, Documento y Email.
    @GetMapping
    //Para mostrar los datos requeridos se usa un DTO llamado DatosListadoMedico en el cual se define los parametros a mostrar.
    public List<DatosListadoMedico> listadoMedico(){
        return medicoRepository.findAll().stream().map(DatosListadoMedico::new).toList();
    }
*/
    //medicoRepository.findAll retorna un listado de médico como entidad, no de DatosListadoMedico. Para esto se hace uso del API Stream de Java, entonces aquí se le da stream.map y se le dice que
    //use los datos de listadoMedicos y que me cree un nuevo DatosListadoMedico con cada médico que traiga de la BD. Y se agrega un toList para que retorne en un objeto del tipo de lista.


// Metodo Con paginacion lista medicos con los parametros Nombre, Especialidad, Documento y Email.
    //Path a enviar desde Insomnia para Probar Metodo GET:
    //Cantidad de registros: http://localhost:8080/medicos?size=2
    //Cantidad de registros y la pagina: http://localhost:8080/medicos?size=2&page=2
   // Paginacion y orden por nombre: http://localhost:8080/medicos?size=4&page=0&sort=nombre    //El atributo a ordenar "nombre" debe ser igual al definido en la tabla de la BD.
    @GetMapping
    //Para mostrar los datos requeridos se usa un DTO llamado DatosListadoMedico en el cual se define los parametros a mostrar.
    //public Page<DatosListadoMedico> listadoMedico(Pageable paginacion){ //Antes era una lista y ahora debe ser una página elegimos "Page" Page Spring, El parametro Pageable viene del frontend para la paginacion
    public Page<DatosListadoMedico> listadoMedico(@PageableDefault(size = 2) Pageable paginacion){ //Para establecer valores por default a mostrar al ejecutarse la app se utiliza @PageableDefault(size = 2) //muestra 2 registros por default al iniciar la app.
        return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new);
    }


    //Metodos para Actualizar medico: Utilizando JPA puro
/* Requerimientos:
- Informacion permitida para actualizacion: Nombre, Documento y Direccion.
- Reglas de negocio: No permitir actualizar email, especialidad y documento.
*/
    @PutMapping
    @Transactional //Cuando termine el metodo la transaccion se va a liberar por lo que se actualizará el registro
    public void actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){ //Para actualizar se creo un nuevo DTO DatosActualizarMedico ya que tiene informacion especifica para actualizacion Nombre, Documento y Direccion.
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id()); //Instanciamos la entidad Medico
        medico.actualizarDatos(datosActualizarMedico);
    }
    // Si existe un error en la ejecucion al actualizar medico, Con @Transactional la transacción no va a hacer un commit en la BD. Va a hacer un rollback y no sucedió nada.

//Probar metodo PUT: http://localhost:8080/medicos
/*
{
	"id": 1,
	"nombre": "Raul Lopez"
}
 */





}
