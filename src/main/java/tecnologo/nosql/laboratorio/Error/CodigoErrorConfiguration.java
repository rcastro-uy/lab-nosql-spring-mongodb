package tecnologo.nosql.laboratorio.Error;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CodigoErrorConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerError(CodigoErrorRepository codigoErrorRepository) {
        return args ->{
            CodigoError error;
            error = new CodigoError(101, "El usuario ya existe");
            codigoErrorRepository.saveAll(List.of(error));
            //error 101 cuando ya existe un usuario con ese correo

            error =  new CodigoError(102, "Error al asignar el rol, el usuario no existe");
            codigoErrorRepository.saveAll(List.of(error));
            //error 102 no existe el usuario al que se le asigno o elimino el rol

            error =  new CodigoError(103, "");
            codigoErrorRepository.saveAll(List.of(error));
            //error 103 al eliminar un rol que el usuario no tiene (se le asigna el rol en el mensaje de error)

            error =  new CodigoError(104, "Contraseña incorrecta");
            codigoErrorRepository.saveAll(List.of(error));
            //error 104 contraseña incorrecla al asignarse o eliminarse un roll

        };
    }
}
