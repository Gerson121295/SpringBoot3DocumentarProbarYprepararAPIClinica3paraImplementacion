package med.voll.api.domain.usuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Usuario implements UserDetails { //implementa UserDetail para decir que este es un usuario de Spring
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String clave;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //getAutorities indica el rol que tendra el usuario en la aplicacion, Si no tiene ningún rol asignado automáticamente Spring le bloquea el acceso y me dice: “no puedes entrar porque tú no tienes ningún tipo de rol asignado para ti.”
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));//Por defecto el ROLE_USER sera el rol que tendran los usarios que entren.
    }

    @Override
    public String getPassword() {
        return clave; //para decirle a spring que cuando valide la password use el campo clave.
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
