package med.voll.api.domain.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> { //parametros: Usuario que es el tipo de objeto a manejar y Long que es tipo de id de la clase Usuario. //JpaRepository tiene metodos mas optimos que CrudRepository pero igual funciona CrudRepository
    UserDetails findByLogin(String username); //Encontrar por el Login login es el campo de la clase Usuario

}
