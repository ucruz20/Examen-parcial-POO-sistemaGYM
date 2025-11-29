package org.groupfive.gymapi.service;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.dto.*;
import org.groupfive.gymapi.exception.NotFoundException;
import org.groupfive.gymapi.model.*;
import org.groupfive.gymapi.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final EntrenadorService entrenadorService;
    private final EntrenadorRepository entrenadorRepository;
    private final AsistenciaService asistenciaService;
    private final ClaseService claseService;
    private final InscripcionService inscripcionService;
    private final MiembroService miembroService;

    // -------------------- MIEMBROS --------------------
    public Miembro crearMiembro(Miembro miembro) {
        return miembroService.guardar(miembro);
    }

    public Miembro editarMiembro(Long id, MiembroRequestDto miembroRequestDto) {
        return miembroService.actualizar(id, miembroRequestDto);
    }

    public List<Miembro> listarMiembros() {
        return miembroService.listar();
    }

    public void eliminarMiembro(Long idMiembro) {
        miembroService.eliminar(idMiembro);
    }

    // -------------------- ENTRENADORES --------------------
    public Entrenador crearEntrenador(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public Entrenador actualizarEntrenador(Long id, EntrenadorRequestDTO entrenadorRequestDTO) {
        return entrenadorRepository.findById(id)
                .map(m -> {
                    m.setNombre(entrenadorRequestDTO.getNombre());
                    m.setEspecialidad(entrenadorRequestDTO.getEspecialidad());
                    m.setSalario(entrenadorRequestDTO.getSalario());
                    return entrenadorRepository.save(m);
                })
                .orElseThrow(() -> new NotFoundException("Entrenador no encontrado"));
    }

    public List<Entrenador> listarEntrenadores() {
        return entrenadorRepository.findAll();
    }

    public void eliminarEntrenador(Long id) {
        entrenadorRepository.deleteById(id);
    }

    // -------------------- CLASES --------------------
    @Transactional
    public ClaseResponse crearClase(ClaseRequest clase) {
        return claseService.createClass(clase);
    }

    public ClaseResponse editarClase(Long idClase, ClaseRequest claseEditada) {
        return claseService.editorInfo(idClase, claseEditada);
    }

    public List<ClaseResponse> listarClases() {
        return claseService.getClasses();
    }

    public void eliminarClase(Long idClase) {
        claseService.eliminate(idClase);
    }

    // -------------------- INSCRIPCIONES --------------------
    @Transactional
    public Inscripcion inscribirMiembro(Long idMiembro, Long idClase) {
        return inscripcionService.guardar(idMiembro, idClase);
    }

    public List<Inscripcion> listarInscripciones() {
        return inscripcionService.listar();
    }

    public void eliminarInscripcion(Long idInscripcion) {
        inscripcionService.eliminar(idInscripcion);
    }

    // -------------------- ASISTENCIAS --------------------
    @Transactional
    public void registrarAsistencia(Long claseId, Long miembroId) {
        asistenciaService.registrarAsistencia(claseId, miembroId);
    }

    public List<AsistenciaResponseDTO> listarAsistencias() {
        return asistenciaService.obtenerTodasLasAsistencias();
    }
}
