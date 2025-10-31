package org.groupfive.gymapi.service;

import java.util.ArrayList;
import java.util.List;

import org.groupfive.gymapi.dto.ClaseDTO;
import org.groupfive.gymapi.dto.CrearClaseDTO;
import org.groupfive.gymapi.model.Clase;
import org.springframework.stereotype.Service;

@Service
public class ClaseService {

    private List<Clase> clases = new ArrayList<>(); // TODO: utilizar repositorio ClaseRepository

    public List<ClaseDTO> obtenerClases() {
        List<ClaseDTO> clasesDTO = new ArrayList<>();
        for (Clase clase : clases) {
            clasesDTO.add(convertirClaseAClaseDTO(clase));
        }
        return clasesDTO;
    }

    public ClaseDTO crearClase(CrearClaseDTO crearClaseDTO) {
        Clase clase = new Clase();
        clase.setId( Long.valueOf(clases.size()) );
        clase.setNombre(crearClaseDTO.getNombre());
        clase.setHorario(crearClaseDTO.getHorario());
        clase.setCupoMaximo(crearClaseDTO.getCupoMaximo());
        clase.setEntrenador(crearClaseDTO.getEntrenador());
        clase.setInscritos(new ArrayList<String>());
        clases.add(clase);
        return convertirClaseAClaseDTO(clase);
    }

    public boolean inscribirMiembro(Long idClase, Long idMiembro) {
        Clase clase = null;
        String miembro = idMiembro.toString();

        for (int i = 0; i < clases.size(); i++) {
            clase = clases.get(i);
            if (clase.getId() == idClase)
                break;
        }

        if (clase == null) return false;

        if (clase.getInscritos().contains(miembro))
            return false;

        if (clase.getInscritos().size() >= clase.getCupoMaximo())
            return false;
        
        clase.getInscritos().add(miembro);
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
