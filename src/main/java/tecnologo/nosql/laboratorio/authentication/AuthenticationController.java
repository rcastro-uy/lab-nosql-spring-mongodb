package tecnologo.nosql.laboratorio.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenHelper tokenHelper;

    @Autowired
    private UserDetailsService customService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails usuario = customService.loadUserByUsername(authenticationRequest.getEmail());

        String jwtToken=tokenHelper.generateToken(usuario.getUsername());
        System.out.println("el token es: " + jwtToken);

        //falta generar el refreshToken y agregarselo a la response
        AuthenticationResponse response=new AuthenticationResponse();
        response.setToken(jwtToken);

        return ResponseEntity.ok(response);
    }

}
