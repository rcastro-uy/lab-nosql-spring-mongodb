package tecnologo.nosql.laboratorio.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodigoErrorService {

    private final CodigoErrorRepository codigoErrorRepository;

    @Autowired
    public CodigoErrorService(CodigoErrorRepository codigoErrorRepository) {
        this.codigoErrorRepository = codigoErrorRepository;
    }

    // Obtener códigos de error - Se invoca la operación sin parámetros y se devuelve una
    // lista con los códigos de error del sistema junto con una descripción de los mismos.
    public List<CodigoError> listarErrores() {
        return codigoErrorRepository.findAll();
    }

    public CodigoError getError(int id) {
        return codigoErrorRepository.findById(id);
    }

}
