package org.groupfive.gymapi.service;

import lombok.RequiredArgsConstructor;

import org.groupfive.gymapi.dto.ClaseRequest;
import org.groupfive.gymapi.exception.BadRequestException;
import org.groupfive.gymapi.exception.NotFoundException;

import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.model.Entrenador;

import org.groupfive.gymapi.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;
    private final ClaseRepository claseRepository;
    private final MiembroRepository miembroRepository;
    private final AsistenciaRepository asistenciaRepository;
    private final InscripcionRepository inscripcionRepository;

    @Transactional
    public Clase crearClase(ClaseRequest req) {

        Entrenador entrenador = entrenadorRepository.findById(req.getEntrenadorId())
                .orElseThrow(() -> new BadRequestException("Error: Entrenador no encontrado para asignar la clase."));

        Clase clase = new Clase();
        clase.setNombre(req.getNombre());
        clase.setHorario(req.getHorario());
        clase.setCupoMaximo(req.getCupoMaximo());
        clase.setEntrenador(entrenador);

        return claseRepository.save(clase);
    }


}