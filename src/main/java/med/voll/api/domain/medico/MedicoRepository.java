package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/*
Medico Repository - Con esta interfaz vamos a ser capaces de hacer todo el proceso de gestión con la BD a nivel del CRUD: crear, guardar objetos, listar, actualizar, etc, pero automáticamente. ¿Por qué?
Porque vamos a extender de otra interfaz llamada JpaRepository y está interfaz es propia de Spring data también, pero JpaRepository necesita dos parámetros.
El primer parámetro es el tipo de objeto que yo voy a guardar aquí, por ejemplo, en este caso es Médico, que es la entidad que yo voy a guardar, es el tipo de entidad con el que yo voy a trabajar en este repositorio.
Segundo, necesito el tipo de objeto del id. Entonces en este caso sería un Long.

*/

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {//Recibe la entidad a guardar y su tipo de Id de esa entidad (clase)

    //Metodo devuelve un listado de medicos donde su campo Activo sea = a true
    Page<Medico> findByActivoTrue(Pageable paginacion); //Retorna una Pagina de Medico ////Este tipo metodo no necesita hacerle test automatico ya que spring framework lo valida automaticamente.

    //Escribir consultas en la BD
      //m.especialidad=:especialidad  --> especialidad sea igual a la enviada en el parametro
        //m.id not in(   --> que el id no se encuentre en la sig. consulta
        //select c.medico.id from Consulta c where c.fecha=:fecha  --> Todos los medicos que correspondan a esa fecha ya estan asignado, Por eso debemos seleccionar a los que no esten asignagos para solicitar la cita

    @Query("""
            select m from Medico m
                        where m.activo= true
                        and
                        m.especialidad=:especialidad
                        and
                        m.id not in( 
                            select c.medico.id from Consulta c
                            where
                            c.fecha=:fecha
                        )
                        order by rand()
                        limit 1
            """)
    Medico seleccionarMedicoConEspecialidadEnFecha(Especialidad especialidad, LocalDateTime fecha);
    //codigo original =   where m.activo = 1
//La consulta seleccionarMedicoConEspecialidadEnFecha se encarga de verificar si en la BD ese médico con esa especialidad y esa fecha se encuentra disponible. En caso que no se encuentre disponible retorna nulo y en caso de que se encuentre disponible, va a retornar el médico.


    @Query("""
            select m.activo 
            from Medico m
            where m.id=:idMedico
            """)
    Boolean findActivoById(long idMedico);

}



/*
JpaRepository tiene muchos metodos en los cuales seran heredados por la interfaz MedicoRepository y esta interfaz al ser implementada en el controlador
tendra acceso a todos los metodos de JpaRepository, metodos como: save, findAll, getById, etc.
 */


