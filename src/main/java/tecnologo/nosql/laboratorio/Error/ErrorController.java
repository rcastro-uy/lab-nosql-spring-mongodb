package tecnologo.nosql.laboratorio.Error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/error")
public class ErrorController {

    private final ErrorService errorService;

    @Autowired
    ErrorController(ErrorService errorService) {
        this.errorService = errorService;
    }

    @GetMapping("/errores")
    public List<Error> getErrores() {
        return errorService.listarErrores();
    }

}
