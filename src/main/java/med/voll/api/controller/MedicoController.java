package med.voll.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController //Para decirle a spring, que esos es un controller
@RequestMapping("/medicos") //mapear el path médicos en este controller
public class MedicoController {

    @PostMapping
    public void registrarMedico(@RequestBody String parametro){ //Para indicar a spring que es un parametro se usa requestBody
        System.out.println("El request llega correctamente");
        System.out.println(parametro);

    }
}
