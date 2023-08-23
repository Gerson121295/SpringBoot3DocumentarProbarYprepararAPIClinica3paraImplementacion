package med.voll.api.medico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
Medico Repository - Con esta interfaz vamos a ser capaces de hacer todo el proceso de gestión con la BD a nivel del CRUD: crear, guardar objetos, listar, actualizar, etc, pero automáticamente. ¿Por qué?
Porque vamos a extender de otra interfaz llamada JpaRepository y está interfaz es propia de Spring data también, pero JpaRepository necesita dos parámetros.
El primer parámetro es el tipo de objeto que yo voy a guardar aquí, por ejemplo, en este caso es Médico, que es la entidad que yo voy a guardar, es el tipo de entidad con el que yo voy a trabajar en este repositorio.
Segundo, necesito el tipo de objeto del id. Entonces en este caso sería un Long.

*/

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> { //Recibe la entidad a guardar y su tipo de Id de esa entidad (clase)

}
/*
JpaRepository tiene muchos metodos en los cuales seran heredados por la interfaz MedicoRepository y esta interfaz al ser implementada en el controlador
tendra acceso a todos los metodos de JpaRepository, metodos como: save, findAll, getById, etc.
 */


