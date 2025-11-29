package org.groupfive.gymapi.service;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.exception.BadRequestException;
import org.groupfive.gymapi.exception.NotFoundException;
import org.groupfive.gymapi.model.*;
import org.groupfive.gymapi.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final MiembroRepository miembroRepository;
    private final ClaseRepository claseRepository;

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> obtenerPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    @Transactional
    public Inscripcion guardar(Long miembroId, Long claseId) {
        Miembro miembro = miembroRepository.findById(miembroId)
                .orElseThrow(() -> new NotFoundException("Miembro no encontrado"));

        Clase clase = claseRepository.findById(claseId)
                .orElseThrow(() -> new NotFoundException("Clase no encontrada"));

        boolean yaInscrito = inscripcionRepository.existsByMiembro_IdAndClase_Id(miembroId, claseId);
        if (yaInscrito) {
            throw new BadRequestException("El miembro ya está inscrito en esta clase");
        }

        long inscritos = inscripcionRepository.countByClase_Id(claseId);
        if (inscritos >= clase.getCupoMaximo()) {
            throw new BadRequestException("La clase ya alcanzó su cupo máximo");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setMiembro(miembro);
        inscripcion.setClase(clase);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setEstado("ACTIVA");

        return inscripcionRepository.save(inscripcion);
    }

    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
