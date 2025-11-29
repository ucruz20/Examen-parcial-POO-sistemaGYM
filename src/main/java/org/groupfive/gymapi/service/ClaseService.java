package org.groupfive.gymapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.groupfive.gymapi.Repository.ClaseRepository;
import org.groupfive.gymapi.Repository.EntrenadorRepository;
import org.groupfive.gymapi.dto.ClaseResponse;
import org.groupfive.gymapi.dto.ClaseRequest;
import org.groupfive.gymapi.dto.EntrenadorResumen;
import org.groupfive.gymapi.exception.BadRequestException;
import org.groupfive.gymapi.exception.NotFoundException;
import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.model.Entrenador;
import org.groupfive.gymapi.model.Inscripcion;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final ClaseRepository claseRepository;
    private final EntrenadorRepository entrenadorRepository;

    public List<ClaseResponse> getClasses() {
        List<ClaseResponse> classesDTO = new ArrayList<>();
        for (Clase clase : claseRepository.findAll()) {
            classesDTO.add(toResponse(clase));
        }
        return classesDTO;
    }

    public ClaseResponse createClass(ClaseRequest createClassDTO) {
        Entrenador trainer = entrenadorRepository.findById(createClassDTO.getEntrenadorId())
                .orElseThrow(()-> new NotFoundException("ENtrenador no encontrado"));
;
            Clase clase = new Clase();
            clase.setNombre(createClassDTO.getNombre());
            clase.setHorario(createClassDTO.getHorario());
            clase.setCupoMaximo(createClassDTO.getCupoMaximo());
            clase.setEntrenador(trainer);
            clase.setInscritos(new ArrayList<Inscripcion>());
            claseRepository.save(clase);
            return toResponse(clase);
    }

    public ClaseResponse editorInfo(Long idClass, ClaseRequest classRequest) {
        return claseRepository.findById(idClass).map(c->{
                    Entrenador newTrainer = entrenadorRepository.findById(classRequest.getEntrenadorId())
                            .orElseThrow(()-> new NotFoundException("Entrenador no encontrado"));
            c.setNombre(classRequest.getNombre());
            c.setHorario(classRequest.getHorario());
            c.setCupoMaximo(classRequest.getCupoMaximo());
            c.setEntrenador(newTrainer);
                    claseRepository.save(c);
                    return toResponse(c);

                }).
                orElseThrow(()-> new NotFoundException("Clase no encontrado"));

    }

    public void eliminate(Long idClass) {
        if (!claseRepository.existsById(idClass))
            throw new NotFoundException("Clase no encontrado");
        claseRepository.deleteById(idClass);

    }

    private ClaseResponse toResponse(Clase clase) {
        return new ClaseResponse(
                clase.getId(),
                clase.getNombre(),
                clase.getCupoMaximo(),
                clase.getHorario(),
                new EntrenadorResumen(
                        clase.getEntrenador().getId(),
                        clase.getEntrenador().getNombre(),
                        clase.getEntrenador().getEspecialidad()
                )
        );
    }


}

