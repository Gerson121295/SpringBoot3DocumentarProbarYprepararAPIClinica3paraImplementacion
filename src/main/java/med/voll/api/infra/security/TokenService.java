package med.voll.api.infra.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import med.voll.api.domain.usuarios.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

//fuente: https://jwt.io/  en libreria JAVA - Auth0 -- COdigo utlizado del repositorio JWT de github: https://github.com/auth0/java-jwt
@Service
public class TokenService {

    @Value("${api.security.token.secret}") //valor para extraer la clave - Usando la anotación @Value Inyectar una propiedad del archivo application.properties en esta clase administrada por Spring.
    private String apiSecret; //variable guarda la clave del usuario

    public String generarToken(Usuario usuario){ //recibe el usuario para validar el token

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //usa un algoritmo HMAC256 y se escribe la contraseña del usuario
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getLogin()) //retorna el nombre del usuario.
                    .withClaim("id", usuario.getId()) //retorna el id
                    .withExpiresAt(generarFechaExpiracion()) //Genera la fecha de expiracion del token
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }

    }

    public String getSubject(String token) {
        if(token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
            verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null){
            throw new RuntimeException("Verifier Invalido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00")); // Horario de surAmerica, expira en 2 horas.
    }

}













