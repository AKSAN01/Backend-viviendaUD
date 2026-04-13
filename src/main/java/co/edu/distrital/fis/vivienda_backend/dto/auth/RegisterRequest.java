package co.edu.distrital.fis.vivienda_backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    // Por ahora solo pediremos esto para el usuario base.
    // Más adelante podemos añadir nombre, universidad, etc.
}