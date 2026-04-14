package co.edu.distrital.fis.vivienda_backend.services;

import co.edu.distrital.fis.vivienda_backend.dto.vivienda.ViviendaRequest;
import co.edu.distrital.fis.vivienda_backend.dto.vivienda.ViviendaResponse;
import co.edu.distrital.fis.vivienda_backend.entities.Usuario;
import co.edu.distrital.fis.vivienda_backend.entities.Vivienda;
import co.edu.distrital.fis.vivienda_backend.repositories.UsuarioRepository;
import co.edu.distrital.fis.vivienda_backend.repositories.ViviendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViviendaService {

    private final ViviendaRepository viviendaRepository;
    private final UsuarioRepository usuarioRepository;

    // Método para crear una nueva vivienda
    public ViviendaResponse crearVivienda(ViviendaRequest request) {
        // 1. Extraemos el usuario actual del Token JWT (¡Magia de Spring Security!)
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailArrendador = userDetails.getUsername();

        // 2. Buscamos al usuario real en la base de datos
        Usuario arrendador = usuarioRepository.findByEmail(emailArrendador)
                .orElseThrow(() -> new RuntimeException("Arrendador no encontrado"));

        // 3. Construimos la entidad Vivienda
        Vivienda nuevaVivienda = Vivienda.builder()
                .titulo(request.getTitulo())
                .descripcion(request.getDescripcion())
                .precioMensual(request.getPrecioMensual())
                .tipoAlojamiento(request.getTipoAlojamiento())
                .latitud(request.getLatitud())
                .longitud(request.getLongitud())
                .arrendador(arrendador) // Vinculamos la vivienda a este usuario
                .build();

        // 4. Guardamos en la base de datos
        Vivienda viviendaGuardada = viviendaRepository.save(nuevaVivienda);

        // 5. Retornamos el DTO de respuesta
        return mapToResponse(viviendaGuardada);
    }

    // Método para listar todas las viviendas disponibles
    public List<ViviendaResponse> obtenerTodas() {
        List<Vivienda> viviendas = viviendaRepository.findAll();
        
        // Convertimos la lista de Entidades a una lista de DTOs usando Streams de Java
        return viviendas.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Método auxiliar para mapear de Entidad a DTO y no repetir código
    private ViviendaResponse mapToResponse(Vivienda vivienda) {
        return ViviendaResponse.builder()
                .id(vivienda.getId())
                .titulo(vivienda.getTitulo())
                .descripcion(vivienda.getDescripcion())
                .precioMensual(vivienda.getPrecioMensual())
                .tipoAlojamiento(vivienda.getTipoAlojamiento())
                .arrendadorEmail(vivienda.getArrendador().getEmail())
                .build();
    }
}