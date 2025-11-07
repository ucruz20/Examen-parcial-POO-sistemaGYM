package org.groupfive.gymapi.service;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.Repository.ClaseRepository;
import org.groupfive.gymapi.Repository.MiembroRepository;
import org.groupfive.gymapi.dto.AsistenciaResponseDTO;
import org.groupfive.gymapi.model.Asistencia;
import org.groupfive.gymapi.Repository.AsistenciaRepository;
import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.model.Miembro;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;
    private final MiembroRepository miembroRepository;
    private final ClaseRepository claseRepository;

    @Transactional
    public void registrarAsistencia(Long claseId, Long miembroId) {

        Miembro miembro = miembroRepository.findById(miembroId)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado."));

        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new RuntimeException("Clase no encontrada."));


        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(23, 59, 59);
        if (asistenciaRepository.existsByMiembro_IdAndClase_IdAndFechaHoraBetween(
                miembroId, claseId, startOfDay, endOfDay)) {
            throw new RuntimeException("Error: La asistencia del miembro ya fue registrada para esta clase hoy.");
        }

        Asistencia asistencia = new Asistencia();
        asistencia.setMiembro(miembro);
        asistencia.setClase(clase);
        asistencia.setPresente(true);

        asistenciaRepository.save(asistencia);
    }


    @Transactional(readOnly = true)
    public AsistenciaResponseDTO obtenerAsistenciaPorId(Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de asistencia no encontrado."));
        return mapToDTO(asistencia);
    }

    @Transactional(readOnly = true)
    public List<AsistenciaResponseDTO> obtenerTodasLasAsistencias() {
        return asistenciaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private AsistenciaResponseDTO mapToDTO(Asistencia asistencia) {
        AsistenciaResponseDTO dto = new AsistenciaResponseDTO();
        dto.setIdAsistencia(asistencia.getIdAsistencia());
        dto.setIdMiembro(asistencia.getMiembro().getId());
        dto.setIdClase(asistencia.getClase().getId());
        dto.setFechaHora(asistencia.getFechaHora());
        dto.setPresente(asistencia.isPresente());
        return dto;
    }
}