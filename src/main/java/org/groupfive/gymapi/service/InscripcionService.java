package org.groupfive.gymapi.service;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.model.Inscripcion;
import org.groupfive.gymapi.Repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscripcionService {
    private final InscripcionRepository inscripcionRepository;

    public List<Inscripcion> listar() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> obtenerPorId(Long id) {
        return inscripcionRepository.findById(id);
    }

    public Inscripcion guardar(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
