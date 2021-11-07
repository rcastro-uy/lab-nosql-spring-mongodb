package tecnologo.nosql.laboratorio.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tecnologo.nosql.laboratorio.Error.CodigoError;
import tecnologo.nosql.laboratorio.Error.CodigoErrorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final CodigoErrorRepository codigoErrorRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, CodigoErrorRepository codigoErrorRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.codigoErrorRepository = codigoErrorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Crear Usuario - Se pasará el correo electrónico del usuario a crear, contraseña,
    // nombre y apellido.El correo debe ser único en el sistema,el sistema genera un
    // usuario. En caso de existir el usuario se retornará el error con código 101.
    public CodigoError crearUsuario(String correo, String nombre, String apellido, String contrasenia) {
        if (usuarioRepository.findByCorreo(correo) != null) {
            // Devuelve Error con codigo 101
            return codigoErrorRepository.findById(101);
        }
        contrasenia = passwordEncoder.encode(contrasenia);
        Usuario user = new Usuario(correo, nombre, apellido, contrasenia);
        usuarioRepository.save(user);
        return null;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Agregar Roles a Usuario - Dado un identificador de usuario (mail) y contraseña, se
    // puede agregar una lista de roles al usuario.El rol es un string, como por ejemplo
    // Rol1, Rol2, etc. En caso del usuario no existir se retorna el error con código 102, en
    // caso de la contraseña no coincidir se enviará el error 104. En caso de ya tener
    // asociado uno de los roles que se pasan por parámetros no se genera un error
    public CodigoError agregarRoles(String correo, String password, List<String> roles) {
        Usuario user = usuarioRepository.findByCorreo(correo);
        if (user == null) {
            return codigoErrorRepository.findById(102);
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            return codigoErrorRepository.findById(104);
        }
        System.out.println("User es: " + user.getUsername());
        for (String rol:roles) {
            if (!user.getRol().contains(rol))
                System.out.println("Agregando rol: " + rol);
                user.addRol(rol);
        }
        usuarioRepository.save(user);
        return null;
    }

    // Eliminar Roles a Usuario - Dado un identificador de usuario (mail) y contraseña, se
    // puede eliminar un conjunto de roles pasando como parámetros una lista de roles del
    // usuario. En caso del usuario no existir se retorna el error con código 102, en caso de
    // no coincidir la contraseña se enviará un error con código 104.
    // En caso de no
    // tener asociado uno de los roles que se pasan por parámetros se genera un error.
    // con código 103 y en la descripción del error se debe indicar el nombre del rol que no
    // se encuentra asignado al usuario.
    public CodigoError eliminarRoles(String correo, String password, List<String> roles) {
        Usuario user = usuarioRepository.findByCorreo(correo);
        CodigoError error = null;
        if (user == null) {
            return codigoErrorRepository.findById(102);
        } else if (!passwordEncoder.matches(password, user.getPassword())) {
            return codigoErrorRepository.findById(104);
        }
        for(int i=0;i < roles.size();i++) {
            // Si el usuario contiene el rol lo elimina
            if(user.getRol().contains(roles.get(i))) {
                user.deleteRol(roles.get(i));
            } else {
                // Si el usuario no contiene el rol se devuelve 103 con informacion
                error = codigoErrorRepository.findById(103);
                error.setInfo(roles.get(i));
                return error;
            }
        }
        usuarioRepository.save(user);
        return error;
    }

}
