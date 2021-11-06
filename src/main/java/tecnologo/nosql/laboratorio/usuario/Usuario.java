package tecnologo.nosql.laboratorio.usuario;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Data
@Builder
public class Usuario implements UserDetails {

    @Id
    private String correo;
    private String nombre;
    private String apellido;
    private String contrasenia;
    private List<String> rol = new ArrayList<String>();

    public Usuario() {
    }

    public Usuario(String correo, String nombre, String apellido, String contrasenia) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public List<String> getRol() {
        return rol;
    }

    public void setRol(List<String> rol) {
        this.rol = rol;
    }

    public void addRol(String rol) {
        this.rol.add(rol);
    }

    public void deleteRol(String rol) {
        for (String r: this.rol) {
            if(r.equals(rol)){
                this.rol.remove(r);
            }
        }
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
