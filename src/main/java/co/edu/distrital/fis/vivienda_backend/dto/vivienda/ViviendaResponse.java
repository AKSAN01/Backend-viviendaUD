package co.edu.distrital.fis.vivienda_backend.dto.vivienda;

import co.edu.distrital.fis.vivienda_backend.entities.Vivienda.TipoAlojamiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViviendaResponse {
    private UUID id;
    private String titulo;
    private String descripcion;
    private BigDecimal precioMensual;
    private TipoAlojamiento tipoAlojamiento;
    // Devolvemos el correo o ID del arrendador para saber de quién es
    private String arrendadorEmail; 
}