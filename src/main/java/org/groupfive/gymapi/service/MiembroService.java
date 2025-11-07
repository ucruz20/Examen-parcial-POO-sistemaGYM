package org.groupfive.gymapi.service;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.model.Miembro;
import org.groupfive.gymapi.Repository.MiembroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MiembroService {
    private final MiembroRepository miembroRepository;

    public List<Miembro> listar() {
        return miembroRepository.findAll();
    }

    public Optional<Miembro> obtenerPorId(Long id) {

        return Optional.ofNullable(miembroRepository.findById(id).orElseThrow(() -> new RuntimeException("Miembro no encontrado")));
    }

    public Miembro guardar(Miembro miembro) {
        return miembroRepository.save(miembro);
    }

    public Miembro actualizar(Long id, Miembro datos) {
        return miembroRepository.findById(id)
                .map(m -> {
                    m.setNombre(datos.getNombre());
                    m.setApellido(datos.getApellido());
                    m.setCorreo(datos.getCorreo());
                    m.setTelefono(datos.getTelefono());
                    m.setDireccion(datos.getDireccion());
                    m.setTipoMembresia(datos.getTipoMembresia());
                    return miembroRepository.save(m);
                }).orElseThrow(() -> new RuntimeException("Miembro no encontrado"));
    }

    public void eliminar(Long id) {
        miembroRepository.deleteById(id);
    }
}
