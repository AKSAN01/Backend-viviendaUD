package co.edu.distrital.fis.vivienda_backend.controllers;

import co.edu.distrital.fis.vivienda_backend.dto.vivienda.ViviendaRequest;
import co.edu.distrital.fis.vivienda_backend.dto.vivienda.ViviendaResponse;
import co.edu.distrital.fis.vivienda_backend.services.ViviendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/viviendas") // Esta ruta SÍ está protegida por el token JWT
@RequiredArgsConstructor
public class ViviendaController {

    private final ViviendaService viviendaService;

    // Endpoint para publicar una nueva vivienda (POST)
    @PostMapping
    public ResponseEntity<ViviendaResponse> crearVivienda(@RequestBody ViviendaRequest request) {
        // Devolvemos un 201 Created cuando se guarda exitosamente
        return new ResponseEntity<>(viviendaService.crearVivienda(request), HttpStatus.CREATED);
    }

    // Endpoint para ver todas las viviendas (GET)
    @GetMapping
    public ResponseEntity<List<ViviendaResponse>> obtenerTodas() {
        return ResponseEntity.ok(viviendaService.obtenerTodas());
    }
}