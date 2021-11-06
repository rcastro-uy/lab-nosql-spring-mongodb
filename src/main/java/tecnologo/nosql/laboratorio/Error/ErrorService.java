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

    public List<Error> listarErrores(){
        //aca se retornan los errores
        return null;
    }
}
