package co.edu.distrital.fis.vivienda_backend.services;

import co.edu.distrital.fis.vivienda_backend.dto.reserva.ReservaRequest;
import co.edu.distrital.fis.vivienda_backend.dto.reserva.ReservaResponse;
import co.edu.distrital.fis.vivienda_backend.entities.Reserva;
import co.edu.distrital.fis.vivienda_backend.entities.Reserva.EstadoReserva;
import co.edu.distrital.fis.vivienda_backend.entities.Usuario;
import co.edu.distrital.fis.vivienda_backend.entities.Vivienda;
import co.edu.distrital.fis.vivienda_backend.repositories.ReservaRepository;
import co.edu.distrital.fis.vivienda_backend.repositories.UsuarioRepository;
import co.edu.distrital.fis.vivienda_backend.repositories.ViviendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ViviendaRepository viviendaRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaResponse crearReserva(ReservaRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Usuario estudiante = usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Vivienda vivienda = viviendaRepository.findById(request.getViviendaId())
                .orElseThrow(() -> new RuntimeException("Vivienda no encontrada"));

        Reserva reserva = Reserva.builder()
                .vivienda(vivienda)
                .estudiante(estudiante)
                .fechaInicio(request.getFechaInicio())
                .fechaFin(request.getFechaFin())
                .estado(EstadoReserva.PENDIENTE)
                .fechaSolicitud(LocalDateTime.now())
                .build();

        Reserva reservaGuardada = reservaRepository.save(reserva);

        return mapToResponse(reservaGuardada);
    }

    private ReservaResponse mapToResponse(Reserva reserva) {
        return ReservaResponse.builder()
                .id(reserva.getId())
                .viviendaId(reserva.getVivienda().getId())
                .viviendaTitulo(reserva.getVivienda().getTitulo())
                .estudianteEmail(reserva.getEstudiante().getEmail())
                .fechaInicio(reserva.getFechaInicio())
                .fechaFin(reserva.getFechaFin())
                .estado(reserva.getEstado())
                .fechaSolicitud(reserva.getFechaSolicitud())
                .build();
    }

    public List<ReservaResponse> obtenerMisReservas() {
        // Extraemos el correo del usuario actual desde su Token JWT
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailEstudiante = userDetails.getUsername();

        // Buscamos solo las reservas que le pertenecen a ese correo
        List<Reserva> reservas = reservaRepository.findByEstudianteEmail(emailEstudiante);

        // Convertimos las entidades a DTOs para enviarlas limpias al cliente
        return reservas.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    // Método para que el arrendador acepte o rechace la reserva
    public ReservaResponse cambiarEstadoReserva(UUID reservaId, EstadoReserva nuevoEstado) {
        // 1. Saber quién es el usuario que está haciendo la petición
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailUsuarioActual = userDetails.getUsername();

        // 2. Buscar la reserva en la base de datos
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        // 3. SEGURIDAD: Verificar que el usuario actual sea el dueño de la vivienda
        String emailDuenioVivienda = reserva.getVivienda().getArrendador().getEmail();
        if (!emailDuenioVivienda.equals(emailUsuarioActual)) {
            throw new RuntimeException("Acceso denegado: No eres el propietario de esta vivienda");
        }

        // 4. Actualizar el estado y guardar
        reserva.setEstado(nuevoEstado);
        Reserva reservaActualizada = reservaRepository.save(reserva);

        return mapToResponse(reservaActualizada);
    }

}