package org.groupfive.gymapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.groupfive.gymapi.dto.ClaseRequest;
import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.service.AsistenciaService;
import org.groupfive.gymapi.service.EntrenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/entrenadores")
@RequiredArgsConstructor
public class EntrenadorController {

    private final EntrenadorService servicioEntrenador;
    private final AsistenciaService servicioAsistencia;

    @PostMapping("/clases")
    public ResponseEntity<Clase> generarClase(@Valid @RequestBody ClaseRequest datosClase) {
        Clase claseCreada = servicioEntrenador.crearClase(datosClase);
        return ResponseEntity.status(HttpStatus.CREATED).body(claseCreada);
    }

    @PostMapping("/asistencias")
    public ResponseEntity<?> registrarAsistencia(@RequestParam Long claseId, @RequestParam Long miembroId) {
        servicioAsistencia.registrarAsistencia(claseId, miembroId);
        return ResponseEntity.ok(Map.of("message", "Asistencia registrada exitosamente"));
    }
}