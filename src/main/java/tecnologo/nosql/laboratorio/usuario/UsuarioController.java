package tecnologo.nosql.laboratorio.usuario;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.codehaus.jettison.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tecnologo.nosql.laboratorio.Error.CodigoError;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> testApi() {
        return ResponseEntity.ok("Test exitoso");
    }

    @GetMapping("/secured-test")
    public ResponseEntity<?> testSecuredApi() {
        return ResponseEntity.ok("Acceso autorizado");
    }

    @PostMapping(path = "/altaUsuario")//CREAR Usuario
    public ResponseEntity<?> crearUsuario(@RequestBody String data) {
        try {
            JsonObject jsonUsuario = new Gson().fromJson(data, JsonObject.class);

            usuarioService.crearUsuario(
                    new String(Base64.getDecoder().decode(jsonUsuario.get("correo").getAsString())),
                    jsonUsuario.get("nombre").getAsString(),
                    jsonUsuario.get("apellido").getAsString(),
                    new String(Base64.getDecoder().decode(jsonUsuario.get("password").getAsString()))
            );

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CodigoError e) {
            return ResponseEntity.status(e.getId()).body(e.getInfo());
        }
    }

    @PutMapping(path = "/agregarRoles")
    public ResponseEntity<?> agregarRoles(@RequestBody String data) {
        JsonObject jsonRoles = new Gson().fromJson(data, JsonObject.class);
        JsonArray jsonArrayRoles = jsonRoles.get("roles").getAsJsonArray();
        List<String> listaRoles = new ArrayList<String>();
        //cargo la lista de roles
        for (JsonElement r: jsonArrayRoles) {
            listaRoles.add(r.getAsString());
        }
        try{
            usuarioService.agregarRoles(
                    new String(Base64.getDecoder().decode(jsonRoles.get("correo").getAsString())),
                    new String(Base64.getDecoder().decode(jsonRoles.get("password").getAsString())),
                    listaRoles
            );
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(CodigoError e){
            return ResponseEntity.status(e.getId()).body(e.getInfo());
        }
    }

    @DeleteMapping(path = "/eliminarRoles")
    public ResponseEntity<?> eliminarRoles(@RequestBody String data) {
        JsonObject jsonRoles = new Gson().fromJson(data, JsonObject.class);
        JsonArray jsonArrayRoles = jsonRoles.get("roles").getAsJsonArray();
        List<String> listaRoles = new ArrayList<String>();
        //cargo la lista de roles
        for (JsonElement r: jsonArrayRoles) {
            listaRoles.add(r.getAsString());
        }
        try{
            usuarioService.agregarRoles(
                    new String(Base64.getDecoder().decode(jsonRoles.get("correo").getAsString())),
                    new String(Base64.getDecoder().decode(jsonRoles.get("password").getAsString())),
                    listaRoles
            );
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(CodigoError e){
            return ResponseEntity.status(e.getId()).body(e.getInfo());
        }
    }
}
