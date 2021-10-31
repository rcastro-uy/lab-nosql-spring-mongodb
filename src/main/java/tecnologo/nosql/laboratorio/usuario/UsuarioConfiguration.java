package tecnologo.nosql.laboratorio.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class UsuarioConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerUsuario(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args ->{
            Usuario user =  new Usuario("usuario@gmail.com","Usuario", "Prueba",
                    passwordEncoder.encode("usuario123"), "Rol1");
            usuarioRepository.saveAll(List.of(user));
            //Alta inicial de Usuario en la DB
        };
    }
}
