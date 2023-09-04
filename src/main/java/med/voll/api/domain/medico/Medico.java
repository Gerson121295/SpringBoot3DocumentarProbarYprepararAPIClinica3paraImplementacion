package med.voll.api.domain.medico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;


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
    private Boolean activo; // Para eliminar un registro de forma logico sin eliminarlo de la BD.

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo = true; //Porque yo llamo al constructor cada que estoy creando este objeto, todo médico que se esté creando por defecto está activo. Cada ves que llamo al constructor estoy creando un medico y este se asigna como true(activo).
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
            this.direccion = direccion.actualizarDireccion(datosActualizarMedico.direccion());
        }
     }

     //Para eliminar un medico de forma logica, No elimina el medico de la BD
    public void desactivarMedico() {
        this.activo = false;
    }
}
