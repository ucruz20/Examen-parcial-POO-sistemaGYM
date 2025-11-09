package org.groupfive.gymapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.dto.AsistenciaResponseDTO;
import org.groupfive.gymapi.dto.AttendanceRequest;
import org.groupfive.gymapi.model.Asistencia;
import org.groupfive.gymapi.model.Entrenador;
import org.groupfive.gymapi.service.AsistenciaService;
import org.groupfive.gymapi.service.EntrenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistencias")
@RequiredArgsConstructor
public class AsistenciaController {

    private final AsistenciaService asistenciaService;
    @GetMapping public List<AsistenciaResponseDTO> getAsistencia(){
        return asistenciaService.obtenerTodasLasAsistencias();
    }

    @PostMapping
    public ResponseEntity<java.util.Map<String, String>> registrarAsistencia(@Valid @RequestBody AttendanceRequest registro) {

        asistenciaService.registrarAsistencia(registro.getClaseId(), registro.getMiembroId());

        return new ResponseEntity<>(
                java.util.Map.of("estado", "OK", "mensaje", "Asistencia registrada correctamente."),
                HttpStatus.CREATED
        );
    }
}
