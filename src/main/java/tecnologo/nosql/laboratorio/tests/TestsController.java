package tecnologo.nosql.laboratorio.tests;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/tests")
public class TestsController {
    
    @GetMapping("/test")
    public ResponseEntity<?> testApi() {
        return ResponseEntity.ok("Test exitoso");
    }

    @GetMapping("/secured-test")
    public ResponseEntity<?> testSecuredApi() {
        return ResponseEntity.ok("Acceso autorizado");
    }
}
