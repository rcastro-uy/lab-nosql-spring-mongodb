package tecnologo.nosql.laboratorio.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/error")
public class CodigoErrorController {

    private final CodigoErrorService codigoErrorService;

    @Autowired
    CodigoErrorController(CodigoErrorService codigoErrorService) {
        this.codigoErrorService = codigoErrorService;
    }

    @GetMapping("/getErrores")
    public List<CodigoError> getErrores() {
        return codigoErrorService.listarErrores();
    }

}
