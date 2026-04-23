package co.edu.distrital.fis.vivienda_backend.dto.reserva;

import co.edu.distrital.fis.vivienda_backend.entities.Reserva.EstadoReserva;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaResponse {
    private UUID id;
    private UUID viviendaId;
    private String viviendaTitulo;
    private String estudianteEmail;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoReserva estado;
    private LocalDateTime fechaSolicitud;
}