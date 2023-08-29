package med.voll.api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.domain.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //Component es el estereotipo más genérico de Spring para definir simplemente un componente de Spring. En Spring todo podria ser Component y funciona pero para una mejor lectura y orden se agrega lo que corresponde: Servicio
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService; //inyectar Token Service

    @Autowired
    private UsuarioRepository usuarioRepository;//Inyeccion para hacer el inicio de sesion

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Obtener el token del header
        var authHeader = request.getHeader("Authorization");  //el nombre del header en específico, es authorization,
       
        if(authHeader != null){ //Si no llega nulo el autHeader entonces ese seria el token
            var token = authHeader.replace("Bearer ", ""); //Remplazar Bearer por espacio vacio, para dejar solo al token.
            var nombreUsuario = tokenService.getSubject(token); // extract username
            if (nombreUsuario != null) {//Si el nombre de usuario no es nulo, si no llega nulo el token es valido
                //Token valido
                var usuario = usuarioRepository.findByLogin(nombreUsuario);//Con esto debe ser capaz de retornar mi usuario por login y hacer el llamado al método para que inicie sesión.
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());  //Forzamos un inicio de sesion
                SecurityContextHolder.getContext().setAuthentication(authentication); //Seteamos manualmente ese inicio de sesion.
            }
        }
        filterChain.doFilter(request, response);
    }
}
