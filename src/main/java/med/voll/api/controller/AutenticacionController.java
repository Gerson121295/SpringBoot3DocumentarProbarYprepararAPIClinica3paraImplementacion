package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuarios.DatosAutenticacionUsuario;
import med.voll.api.domain.usuarios.Usuario;
import med.voll.api.infra.security.DatosJwtToken;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {


    @Autowired
    private AuthenticationManager authenticationManager; //Inyeccion //el autenticationManager disparar el proceso de autenticación en Spring

    @Autowired
    private TokenService tokenService; //inyecta

    //Probar el metodo POST: http://localhost:8080/login  enviar:    {  "login":"gerson.ep","clave":"123456" }
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){ //Necesitamos un DTO porque vamos a recibir parametros del cliente.

        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave()); //Genero con los datosAutenticacionUsuario el token
        var usuarioAutenticado = authenticationManager.authenticate(authToken); //El token es usado por el authenticationManger para autenticar. El usuarioAutenticado va a salir si esta autenticacion es exitosa.
        var JWTtoken  = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal()); //El getPrincipal es el usuario o Objeto que ya fue autenticado en nuestro sistema.
        return ResponseEntity.ok(new DatosJwtToken(JWTtoken)); //Devolvera un DTO con los datos user, id y fecha de expiracion
    }


}
