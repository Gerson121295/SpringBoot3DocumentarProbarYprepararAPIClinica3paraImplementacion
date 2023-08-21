package med.voll.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Contiene @Controller
@RequestMapping("/hello") //Para decirle qué ruta de HTTP está siguiendo este método
public class HelloController {

    @GetMapping //GetMapping vamos a mapear el método en esta ruta("/hello") para esta aplicación y lo que debería darnos es un hello world!. metodo Get(obtener).
    public String helloWord(){
        return "Hello world from Guatemala";
    }
}
