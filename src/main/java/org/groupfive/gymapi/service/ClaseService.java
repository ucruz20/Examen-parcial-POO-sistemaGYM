package org.groupfive.gymapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.groupfive.gymapi.dto.ClaseDTO;
import org.groupfive.gymapi.dto.CrearClaseDTO;
import org.groupfive.gymapi.model.Clase;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClaseService {

    private final Map<Long, Clase> clases; // TODO: utilizar repositorio ClaseRepository
    private long clasesIndex = 0L;

    public List<ClaseDTO> obtenerClases() {
        List<ClaseDTO> clasesDTO = new ArrayList<>();
        for (Clase clase : clases.values()) {
            clasesDTO.add(convertirClaseAClaseDTO(clase));
        }
        return clasesDTO;
    }

    public ClaseDTO crearClase(CrearClaseDTO crearClaseDTO) {
        Clase clase = new Clase();
        clase.setId(clasesIndex);
        clase.setNombre(crearClaseDTO.getNombre());
        clase.setHorario(crearClaseDTO.getHorario());
        clase.setCupoMaximo(crearClaseDTO.getCupoMaximo());
        clase.setEntrenador(crearClaseDTO.getEntrenador());
        clase.setInscritos(new ArrayList<String>());
        clasesIndex++;
        clases.put(clase.getId(), clase);
        return convertirClaseAClaseDTO(clase);
    }

    public boolean inscribirMiembro(Long idClase, Long idMiembro) {
        String miembro = idMiembro.toString();
        Clase clase = clases.get(idClase);
        
        if (clase == null) return false;

        if (clase.getInscritos().contains(miembro))
            return false;

        if (clase.getInscritos().size() >= clase.getCupoMaximo())
            return false;
        
        clase.getInscritos().add(miembro);
        return true;
    }

    public ClaseDTO editarInfo(Long idClase, CrearClaseDTO clase) {
        Clase claseActual = clases.get(idClase);
        if (claseActual == null) return null;
        claseActual.setNombre(clase.getNombre());
        claseActual.setHorario(clase.getHorario());
        claseActual.setCupoMaximo(clase.getCupoMaximo());
        claseActual.setEntrenador(clase.getEntrenador());
        return convertirClaseAClaseDTO(claseActual);
    }

    public boolean eliminar(Long idClase) {
        if (!clases.containsKey(idClase))
            return false;
        clases.remove(idClase);
        return true;
    }

    private ClaseDTO convertirClaseAClaseDTO(Clase clase) {
        ClaseDTO claseDTO = new ClaseDTO();
        claseDTO.setId(clase.getId());
        claseDTO.setNombre(clase.getNombre());
        claseDTO.setCupoMaximo(clase.getCupoMaximo());
        claseDTO.setHorario(clase.getHorario());
        claseDTO.setEntrenador(clase.getEntrenador());
        return claseDTO;
    }
}
