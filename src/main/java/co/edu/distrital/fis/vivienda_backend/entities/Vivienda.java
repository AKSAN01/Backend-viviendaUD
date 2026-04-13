package co.edu.distrital.fis.vivienda_backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "viviendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vivienda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_arrendador", nullable = false)
    private Usuario arrendador;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    @Column(name = "precio_mensual", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioMensual;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alojamiento", nullable = false)
    private TipoAlojamiento tipoAlojamiento;

    private Double latitud;
    private Double longitud;

    public enum TipoAlojamiento {
        INDIVIDUAL, COMPARTIDA, COMPLETO
    }
}
