package org.groupfive.gymapi.service;

import java.util.ArrayList;
import java.util.List;

import org.groupfive.gymapi.model.Clase;
import org.springframework.stereotype.Service;

@Service
public class ClaseService {

    private List<Clase> clases = new ArrayList<>(); // TODO: utilizar repositorio ClaseRepository

    // TODO: crear clase utilizando ClaseDTO
    public Clase crearClase(Long id, String nombre, int cupoMaximo, String horario, String entrenador) {
        Clase clase = new Clase(id, nombre, cupoMaximo, horario, entrenador, new ArrayList<>());
        clases.add(clase);
        return clase;
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

}
