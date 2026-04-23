package co.edu.distrital.fis.vivienda_backend.controllers;

import co.edu.distrital.fis.vivienda_backend.dto.reserva.ReservaRequest;
import co.edu.distrital.fis.vivienda_backend.dto.reserva.ReservaResponse;
import co.edu.distrital.fis.vivienda_backend.services.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaResponse> crearReserva(@RequestBody ReservaRequest request) {
        return new ResponseEntity<>(reservaService.crearReserva(request), HttpStatus.CREATED);
    }

    @GetMapping("/mis-reservas")
    public ResponseEntity<List<ReservaResponse>> obtenerMisReservas() {
        return ResponseEntity.ok(reservaService.obtenerMisReservas());
    }
}