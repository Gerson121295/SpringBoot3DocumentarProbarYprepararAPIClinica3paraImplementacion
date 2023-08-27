package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController //Para decirle a spring, que esos es un controller
@RequestMapping("/medicos") //mapear el path médicos en este controller
public class MedicoController{

    @Autowired(required = false) //Para definir la interfaz - No recomendable usar @Autowired ya que tendremos problemas al hacer pruebas unitarias
    private MedicoRepository medicoRepository; //Instanciar nuestra interfaz MedicoRepository


   //Buenas practicas para Metodo Post: Return 201 - Created - Registro Creado, Return URL donde encontrar al medico.
    @PostMapping //recibe datos (JSON) desde Insomnia.
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico, //Para indicar a spring que es un parametro se usa requestBody y @Valid valida que los datos en DatosRegistroMédico todo sea válido, lleguen correctamente
                                                                UriComponentsBuilder uriComponentsBuilder){ //genera la URL URI a retornar donde esta el registro creado
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(
                medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()));

        //URI url = "http://localhost:8080/medicos/"+ medico.getId();  - Se puede hacer pero si al desplegarlo a un servidor va la url del server
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri(); //URl creada dinamicamente para mostrar donde esta el registro creado.
        return ResponseEntity.created(url).body(datosRespuestaMedico);
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
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedico(@PageableDefault(size = 5) Pageable paginacion){ //Para establecer valores por default a mostrar al ejecutarse la app se utiliza @PageableDefault(size = 2) //muestra 2 registros por default al iniciar la app.
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedico::new); //Retorna todos los medicos
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedico::new)); //Retorna solo a los medicos que en su campo activo sea = a true.
    }



    //Metodos para Actualizar medico: Utilizando JPA puro
/* Requerimientos:
- Informacion permitida para actualizacion: Nombre, Documento y Direccion.
- Reglas de negocio: No permitir actualizar email, especialidad y documento.
*/
    @PutMapping //Como buena practica se retorna el objeto que se actualizo
    @Transactional //Cuando termine el metodo la transaccion se va a liberar por lo que se actualizará el registro
    public ResponseEntity actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){ //Para actualizar se creo un nuevo DTO DatosActualizarMedico ya que tiene informacion especifica para actualizacion Nombre, Documento y Direccion.
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id()); //Instanciamos la entidad Medico
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(
                medico.getId(), medico.getNombre(), medico.getEmail(),
                medico.getTelefono(), medico.getEspecialidad().toString(),
                new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento())));
        //Se envia un records DTO DatosRespuestaMedico con los datos a mostrar, no es recomendable mandar todos la entidad medico ya que contiene todos los datos.
    }
    // Si existe un error en la ejecucion al actualizar medico, Con @Transactional la transacción no va a hacer un commit en la BD. Va a hacer un rollback y no sucedió nada.

//Probar metodo PUT: http://localhost:8080/medicos
/*
{
	"id": 1,
	"nombre": "Raul Lopez"
}
 */


    //Metodo Delete en BD - Borrar completamente de la BD a un medico enviando su id. Por medio de PathVariable que es una variable que va en la URL
    // Probar metodo DELETE: http://localhost:8080/medicos/3

 /* @DeleteMapping("/{id}")// /3  o  /{id} - va una variable id la cual se recibe para eliminar el medico
    @Transactional
    public void eliminarMedico(@PathVariable Long id){ //@PathVariable especifica que la variable viene del Path es tipo Long id
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico); //mandamos el médico como entidad que queremos borrar.
    }
*/

/* Exclusion de Médicos
  - Reglas de Negocio:
  - El registro no debe ser borrado de la base de datos.
  - El listado debe retornar solo médicos activos.

  Para cumplir esta regla de negocio:
  - Agregar un campo más a la entidad médico, agregar un flag, una bandera, característica a estos médicos que digan que están activos.
*/

    //Metodo Delete logico - Muestra los medicos donde su campo activo sea = 1, los que tengan el campo activo = 0 no serán mostrados.
    //Probar metodo Delete: DEL ->  http://localhost:8080/medicos/7
    @DeleteMapping("/{id}")// /3  o  /{id} - va una variable id la cual se recibe para eliminar el medico
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id){ //@PathVariable especifica que la variable viene del Path es tipo Long id
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build(); //agregar response al hacer la consulta exitosa retorna un 204 Content
    }

    //Metodo para mostrar registro Medico creado con todos sus datos, Por medio de url que genera al crear el registro Medico
    //Probar metodo GET ->  http://localhost:8080/medicos/7
    @GetMapping("/{id}")// /3  o  /{id} - variable id la cual se recibe al crear el registro medico
    @Transactional
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id){ //@PathVariable especifica que la variable viene del Path es tipo Long id
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedico= new DatosRespuestaMedico(
                        medico.getId(), medico.getNombre(), medico.getEmail(),
                        medico.getTelefono(), medico.getEspecialidad().toString(),
                        new DatosDireccion(medico.getDireccion().getCalle(), medico.getDireccion().getDistrito(),
                                medico.getDireccion().getCiudad(), medico.getDireccion().getNumero(),
                                medico.getDireccion().getComplemento()));
        return ResponseEntity.ok(datosMedico); //agregar response al hacer la consulta exitosa retorna un 200 Content y los datos del medico.
    }//ResponseEntity es un wrapper para encapsular la respuesta que le vamos a dar a nuestro servidor


}
