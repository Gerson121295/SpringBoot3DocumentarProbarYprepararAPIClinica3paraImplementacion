package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuarios.DatosAutenticacionUsuario;
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

    //Probar el metodo POST: http://localhost:8080/login  enviar:    {  "login":"gerson.ep","clave":"123456" }
    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){ //Necesitamos un DTO porque vamos a recibir parametros del cliente.

        Authentication token = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
                datosAutenticacionUsuario.clave()); //Genero con los datosAutenticacionUsuario el token
        authenticationManager.authenticate(token); //El token es usado por el authenticationManger para autenticar.
        return ResponseEntity.ok().build();
    }

}
