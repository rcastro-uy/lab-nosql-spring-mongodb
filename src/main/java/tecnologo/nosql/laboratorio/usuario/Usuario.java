package tecnologo.nosql.laboratorio.usuario;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
public class Usuario implements UserDetails {

    @Id
    private String correo;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private List<String> rol = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(String correo, String nombre, String apellido, String contrasenia) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
    }

    public Usuario(String correo, String nombre, String apellido, String contrasenia, List<String> rol) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public void addRol(String rol) {
        this.rol.add(rol);
    }

    public void deleteRol(String rol) {
        this.rol.removeIf(r -> r.equals(rol));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.contrasenia;
    }

    @Override
    public String getUsername() {
        return this.correo;
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
