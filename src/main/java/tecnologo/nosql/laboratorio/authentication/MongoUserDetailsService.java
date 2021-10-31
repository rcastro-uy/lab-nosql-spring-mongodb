package tecnologo.nosql.laboratorio.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tecnologo.nosql.laboratorio.usuario.Usuario;
import tecnologo.nosql.laboratorio.usuario.UsuarioRepository;

@Component
public class MongoUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByCorreo(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return usuario;
/*        List authorities = Arrays.asList(new SimpleGrantedAuthority(“user”));
        return new User(user.getUsername(), user.getPassword(), authorities);*/
    }
}
