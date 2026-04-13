package co.edu.distrital.fis.vivienda_backend.repositories;

import co.edu.distrital.fis.vivienda_backend.entities.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface ViviendaRepository extends JpaRepository<Vivienda, UUID> {
    
    // Para filtrar las viviendas por tipo (ej. Solo habitaciones individuales)
    List<Vivienda> findByTipoAlojamiento(Vivienda.TipoAlojamiento tipo);

    // Para el filtro de "precio máximo" en las búsquedas
    List<Vivienda> findByPrecioMensualLessThanEqual(BigDecimal precioMaximo);
}
