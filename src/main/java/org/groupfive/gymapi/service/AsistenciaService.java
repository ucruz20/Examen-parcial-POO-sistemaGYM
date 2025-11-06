package org.groupfive.gymapi.service;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.dto.AsistenciaResponseDTO;
import org.groupfive.gymapi.model.Asistencia;
import org.groupfive.gymapi.repository.AsistenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    @Transactional(readOnly = true)
    public AsistenciaResponseDTO obtenerAsistenciaPorId(Long id) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro de asistencia no encontrado."));
        return mapToDTO(asistencia);
    }

    @Transactional(readOnly = true)
    public List<AsistenciaResponseDTO> obtenerTodasLasAsistencias() {
        return asistenciaRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AsistenciaResponseDTO mapToDTO(Asistencia asistencia) {
        AsistenciaResponseDTO dto = new AsistenciaResponseDTO();
        dto.setIdAsistencia(asistencia.getIdAsistencia());
        dto.setIdMiembro(asistencia.getMiembro().getId());
        dto.setIdClase(asistencia.getSesion().getIdSesion());
        dto.setFechaHora(asistencia.getFechaHora());
        dto.setPresente(asistencia.isPresente());
        return dto;
    }
}