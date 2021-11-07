package tecnologo.nosql.laboratorio.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ErrorService {

    private final ErrorRepository errorRepository;

    @Autowired
    public ErrorService(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    // Obtener códigos de error - Se invoca la operación sin parámetros y se devuelve una
    // lista con los códigos de error del sistema junto con una descripción de los mismos.
    public List<Error> listarErrores(){
        List<Error> errors = errorRepository.findAll();
        if (errors != null ) {
            return errors;
        }
        return null;
    }

    public Error getError(int id) {
        Error error = errorRepository.findById(id);
        if (error != null) {
            return error;
        }
        return null;
    }

}
