package med.voll.api.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter  //Lombok agrega todos los Getters para todos los atributos
@NoArgsConstructor  //Lombok agrega un constuctor por default sin atributos.
@AllArgsConstructor //Lombok agrega un constructor con todos los atributos
@Embeddable
public class Direccion {
    private String calle;
    private Integer numero;
    private String complemento;
    private String distrito;
    private String ciudad;
}
