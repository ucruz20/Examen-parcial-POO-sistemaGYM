package org.groupfive.gymapi.service;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.model.Asistencia;
import org.groupfive.gymapi.model.Entrenador;
import org.groupfive.gymapi.repository.*;
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
    public Clase crearClase(CreateClaseRequest req) {

        Entrenador entrenador = entrenadorRepository.findById(req.getEntrenadorId())
                .orElseThrow(() -> new RuntimeException("Error: Entrenador no encontrado para asignar la clase."));

        Clase clase = new Clase();
        clase.setNombre(req.getNombre());
        clase.setHorario(req.getHorario());
        clase.setCupoMax(req.getCupoMax());
        clase.setEntrenador(entrenador);

        return claseRepository.save(clase);
    }

    @Transactional
    public void registrarAsistencia(Long claseId, Long miembroId) {

        Miembro miembro = miembroRepository.findById(miembroId)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado."));

        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada."));

        if (!inscripcionRepository.existsByMiembro_IdAndClase_IdSesion(miembroId, claseId)) {
            throw new RuntimeException("Error: El miembro NO está inscrito en esta clase.");
        }

        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(23, 59, 59);
        if (asistenciaRepository.existsByMiembro_IdAndSesion_IdSesionAndFechaHoraBetween(
                miembroId, claseId, startOfDay, endOfDay)) {
            throw new RuntimeException("Error: La asistencia del miembro ya fue registrada para esta clase hoy.");
        }

        Asistencia asistencia = new Asistencia();
        asistencia.setMiembro(miembro);
        asistencia.setSesion(clase);
        asistencia.setPresente(true);

        asistenciaRepository.save(asistencia);
    }
}