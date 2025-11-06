package org.groupfive.gymapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.dto.AttendanceRequest;
import org.groupfive.gymapi.service.EntrenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final EntrenadorService entrenadorService;

    @PostMapping
    public ResponseEntity<java.util.Map<String, String>> registrarAsistencia(@Valid @RequestBody AttendanceRequest registro) {

        entrenadorService.registrarAsistencia(registro.getClaseId(), registro.getMiembroId());

        return new ResponseEntity<>(
                java.util.Map.of("estado", "OK", "mensaje", "Asistencia registrada correctamente."),
                HttpStatus.CREATED
        );
    }
}
