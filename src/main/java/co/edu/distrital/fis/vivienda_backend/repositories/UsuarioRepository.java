package co.edu.distrital.fis.vivienda_backend.repositories;

import co.edu.distrital.fis.vivienda_backend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    
    // Spring Boot entiende este nombre y crea la consulta SQL automáticamente:
    // SELECT * FROM usuarios WHERE email = ?
    Optional<Usuario> findByEmail(String email);
}