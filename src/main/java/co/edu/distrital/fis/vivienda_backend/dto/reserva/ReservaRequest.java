package co.edu.distrital.fis.vivienda_backend.dto.reserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservaRequest {
    private UUID viviendaId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}