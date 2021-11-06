package tecnologo.nosql.laboratorio.Error;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ErrorConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerUsuario(ErrorRepository ErrorRepository) {
        return args ->{
            Error error;
            error = new Error(101, "Usuario creado con éxito");
            ErrorRepository.saveAll(List.of(error));
            //error 101 al crear correctamente un usuario

            error =  new Error(102, "Error al asignar el rol, el usuario no existe");
            ErrorRepository.saveAll(List.of(error));
            //error 102 no existe el usuario al que se le asigno o elimino el rol

            error =  new Error(103, "");
            ErrorRepository.saveAll(List.of(error));
            //error 103 al eliminar un rol que el usuario no tiene (se le asigna el rol en el mensaje de error)

            error =  new Error(104, "Contraseña incorrecta");
            ErrorRepository.saveAll(List.of(error));
            //error 104 contraseña incorrecla al asignarse o eliminarse un roll

            error =  new Error(104, "Contraseña incorrecta");
            ErrorRepository.saveAll(List.of(error));
            //error 104 contraseña incorrecla al asignarse un roll

        };
    }
}
