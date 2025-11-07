package org.groupfive.gymapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.groupfive.gymapi.dto.ClaseResponse;
import org.groupfive.gymapi.dto.ClaseRequest;
import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.model.Inscripcion;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final Map<Long, Clase> clases; // TODO: utilizar repositorio ClaseRepository
    private long clasesIndex = 0L;

    public List<ClaseResponse> obtenerClases() {
        List<ClaseResponse> clasesDTO = new ArrayList<>();
        for (Clase clase : clases.values()) {
            clasesDTO.add(convertirClaseAClaseDTO(clase));
        }
        return clasesDTO;
    }

    public ClaseResponse crearClase(ClaseRequest crearClaseDTO) {
        Clase clase = new Clase();
        clase.setId(clasesIndex);
        clase.setNombre(crearClaseDTO.getNombre());
        clase.setHorario(crearClaseDTO.getHorario());
        clase.setCupoMaximo(crearClaseDTO.getCupoMaximo());
        clase.setEntrenadorId(crearClaseDTO.getEntrenadorId());
        clase.setInscritos(new ArrayList<Inscripcion>());
        clasesIndex++;
        clases.put(clase.getId(), clase);
        return convertirClaseAClaseDTO(clase);
    }

    public ClaseResponse editarInfo(Long idClase, ClaseRequest clase) {
        Clase claseActual = clases.get(idClase);
        if (claseActual == null) return null;
        claseActual.setNombre(clase.getNombre());
        claseActual.setHorario(clase.getHorario());
        claseActual.setCupoMaximo(clase.getCupoMaximo());
        claseActual.setEntrenadorId(clase.getEntrenadorId());
        return convertirClaseAClaseDTO(claseActual);
    }

    public boolean eliminar(Long idClase) {
        if (!clases.containsKey(idClase))
            return false;
        clases.remove(idClase);
        return true;
    }

    private ClaseResponse convertirClaseAClaseDTO(Clase clase) {
        ClaseResponse claseDTO = new ClaseResponse();
        claseDTO.setId(clase.getId());
        claseDTO.setNombre(clase.getNombre());
        claseDTO.setCupoMaximo(clase.getCupoMaximo());
        claseDTO.setHorario(clase.getHorario());
        claseDTO.setEntrenadorId(clase.getEntrenadorId());
        return claseDTO;
    }

}

