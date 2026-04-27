package co.edu.distrital.fis.vivienda_backend.controllers;

import co.edu.distrital.fis.vivienda_backend.dto.reserva.ReservaRequest;
import co.edu.distrital.fis.vivienda_backend.dto.reserva.ReservaResponse;
import co.edu.distrital.fis.vivienda_backend.entities.Reserva.EstadoReserva;
import co.edu.distrital.fis.vivienda_backend.repositories.RegistroActividadRepository;
import co.edu.distrital.fis.vivienda_backend.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;
    private final RegistroActividadRepository registroActividadRepository;

    @PostMapping
    public ResponseEntity<ReservaResponse> crearReserva(@RequestBody ReservaRequest request) {
        return new ResponseEntity<>(reservaService.crearReserva(request), HttpStatus.CREATED);
    }

    @GetMapping("/mis-reservas")
    public ResponseEntity<List<ReservaResponse>> obtenerMisReservas() {
        return ResponseEntity.ok(reservaService.obtenerMisReservas());
    }

    // (Asegúrate de importar co.edu.distrital.fis.vivienda_backend.entities.Reserva.EstadoReserva)

    @PatchMapping("/{id}/estado")
    public ResponseEntity<ReservaResponse> cambiarEstadoReserva(
            @PathVariable UUID id,
            @RequestParam EstadoReserva estado
    ) {
        return ResponseEntity.ok(reservaService.cambiarEstadoReserva(id, estado));
    }

        @GetMapping("/logs")
    public ResponseEntity<?> verLogs() {
        return ResponseEntity.ok(registroActividadRepository.findAll());
    }

    

}