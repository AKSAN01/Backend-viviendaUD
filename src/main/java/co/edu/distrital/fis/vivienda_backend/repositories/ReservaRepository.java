package co.edu.distrital.fis.vivienda_backend.repositories;

import co.edu.distrital.fis.vivienda_backend.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    
    // Para ver el historial de un estudiante
    List<Reserva> findByEstudianteId(UUID estudianteId);

    // Para que un arrendador vea las reservas de una propiedad específica
    List<Reserva> findByViviendaId(UUID viviendaId);

    List<Reserva> findByEstudianteEmail(String email);
}
