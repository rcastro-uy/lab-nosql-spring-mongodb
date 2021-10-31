package tecnologo.nosql.laboratorio.usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/usuario")
public class UsuarioController {

    // private final UsuarioService usuarioService;

    @GetMapping("/test")
    public ResponseEntity<?> testApi() {
        return ResponseEntity.ok("Test exitoso");
    }

    @GetMapping("/secured-test")
    public ResponseEntity<?> testSecuredApi() {
        return ResponseEntity.ok("Acceso autorizado");
    }
}
