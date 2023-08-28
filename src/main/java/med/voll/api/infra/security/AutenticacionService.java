package med.voll.api.infra.security;

//Esta clase tiene la logica de autenticacion de la app.

import med.voll.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //anotacion para decirle a Spring, escanea esta clase porque es un servicio que yo quiero abastecer para mi aplicación.
public class AutenticacionService implements UserDetailsService {//UserDetailsService: Interfaz propia de Spring que Spring utiliza para efectuar su login, "Proceso de autenticación del usuario"

    @Autowired
    private UsuarioRepository usuarioRepository; //inyecta el UsuarioRepository

    /*
    Un objeto de tipo UserDetails, dice loadUserByUsername. "cargar usuario por nombre de usuario" lo que pide en este método es decirle de qué forma yo voy a cargar ese usuario y de dónde, porque a Spring no le interesa el datasource o la fuente de datos de la cual yo obtengo el usuario.
    A Spring le interesa, que yo le dé el usuario, dependiendo del username que estaba buscando.
    Por lo que tengo que inyectar UsuarioRepository.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }
}
