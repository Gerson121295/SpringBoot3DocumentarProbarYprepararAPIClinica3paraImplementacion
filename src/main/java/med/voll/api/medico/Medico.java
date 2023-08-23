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
    private String telefono;
    private String documento;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.documento = datosRegistroMedico.documento();
        this.telefono = datosRegistroMedico.telefono();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion =new Direccion(datosRegistroMedico.direccion());
    }

    //Aquí vamos a decir que el nombre del médico me lo iguale a lo que está llegando del DTO,
    // por ejemplo, entre los datos que yo puedo actualizar están nombre, documento y dirección.
    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if(datosActualizarMedico.nombre() != null){ // si datosActualizarMedico.nombre es diferente de null
            this.nombre = datosActualizarMedico.nombre(); // si es diferente de null actualizar dato nombre
        }
        if (datosActualizarMedico.documento() != null){
            this.documento = datosActualizarMedico.documento();
        }
        if (datosActualizarMedico.direccion() != null){
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
        }
     }
}
