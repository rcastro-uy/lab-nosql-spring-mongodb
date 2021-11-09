package tecnologo.nosql.laboratorio.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class UsuarioConfiguration {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunnerUsuario(UsuarioRepository usuarioRepository) {
        return args ->{
            Usuario user =  new Usuario("usuario@gmail.com","Usuario", "Prueba",
                    passwordEncoder.encode("usuario123"));
            usuarioRepository.saveAll(List.of(user));
            //Alta inicial de Usuario en la DB
        };
    }
}
