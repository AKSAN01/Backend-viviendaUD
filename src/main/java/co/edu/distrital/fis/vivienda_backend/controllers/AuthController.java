package co.edu.distrital.fis.vivienda_backend.controllers;

import co.edu.distrital.fis.vivienda_backend.dto.auth.AuthRequest;
import co.edu.distrital.fis.vivienda_backend.dto.auth.AuthResponse;
import co.edu.distrital.fis.vivienda_backend.dto.auth.RegisterRequest;
import co.edu.distrital.fis.vivienda_backend.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth") // Esta es la ruta base que dejamos pública en SecurityConfig
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}