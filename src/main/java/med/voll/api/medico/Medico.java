package med.voll.api.medico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.Direccion;


//Esta clase Medico va a ser usada para hacer la persistencia de datos con nuestro modelo de BD.

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter  //Lombok agrega todos los Getters para todos los atributos
@NoArgsConstructor  //Lombok agrega un constuctor por default sin atributos.
@AllArgsConstructor //Lombok agrega un constructor con todos los atributos
@EqualsAndHashCode(of ="id") //Utiliza el parametro id para las compraraciones entre medicos
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

}
