package co.edu.distrital.fis.vivienda_backend.dto.vivienda;

import co.edu.distrital.fis.vivienda_backend.entities.Vivienda.TipoAlojamiento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViviendaRequest {
    private String titulo;
    private String descripcion;
    private BigDecimal precioMensual;
    private TipoAlojamiento tipoAlojamiento;
    private Double latitud;
    private Double longitud;
}