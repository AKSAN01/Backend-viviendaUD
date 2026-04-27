package co.edu.distrital.fis.vivienda_backend.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "registro_actividades") // Así se llamará la colección en Mongo
public class RegistroActividad {

    @Id // Ojo: Este import debe ser org.springframework.data.annotation.Id
    private String id; // En Mongo, los IDs suelen ser Strings (ObjectId)
    
    private String usuarioEmail;
    private String accion;        // Ej: "CREACION_RESERVA", "VIVIENDA_PUBLICADA"
    private String entidad;       // Ej: "Reserva", "Vivienda"
    private String descripcion;   // Ej: "El usuario solicitó la vivienda X"
    private LocalDateTime fecha;
}