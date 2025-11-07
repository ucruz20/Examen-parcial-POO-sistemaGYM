package org.groupfive.gymapi.service;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.model.Inscripcion;
import org.groupfive.gymapi.Repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Long idClase = inscripcion.getClase().getId();
        Long idMiembro = inscripcion.getMiembro().getId();

        boolean yaInscrito = inscripcionRepository.existsByMiembro_IdAndClase_Id(idMiembro, idClase);
        if(yaInscrito){
            throw new RuntimeException("El miembro ya existe");
        }
        long inscritos = inscripcionRepository.countByClase_Id(idClase);
        if(inscritos >= inscripcion.getClase().getCupoMaximo()){
            throw new RuntimeException("El cupo maximo ya existe");
        }
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setEstado("ACTIVA");
        return inscripcionRepository.save(inscripcion);
    }

    public void eliminar(Long id) {
        inscripcionRepository.deleteById(id);
    }
}
