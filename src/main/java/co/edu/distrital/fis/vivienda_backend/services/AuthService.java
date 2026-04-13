package co.edu.distrital.fis.vivienda_backend.services;

import co.edu.distrital.fis.vivienda_backend.dto.auth.AuthRequest;
import co.edu.distrital.fis.vivienda_backend.dto.auth.AuthResponse;
import co.edu.distrital.fis.vivienda_backend.dto.auth.RegisterRequest;
import co.edu.distrital.fis.vivienda_backend.entities.Usuario;
import co.edu.distrital.fis.vivienda_backend.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // 1. Creamos el objeto Usuario con los datos que llegaron
        var usuario = Usuario.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword())) // ¡Encriptamos la clave!
                .rol(Usuario.Rol.ESTUDIANTE) // Por defecto lo registramos como estudiante por ahora
                .build();

        // 2. Lo guardamos en PostgreSQL
        usuarioRepository.save(usuario);

        // 3. Le generamos su token JWT de bienvenida
        var jwtToken = jwtService.generateToken(usuario);
        
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse authenticate(AuthRequest request) {
        // 1. Validamos que el correo y la contraseña sean correctos (si no, lanza error automático)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. Buscamos al usuario en la BD
        var usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow();

        // 3. Le generamos un nuevo token
        var jwtToken = jwtService.generateToken(usuario);
        
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}