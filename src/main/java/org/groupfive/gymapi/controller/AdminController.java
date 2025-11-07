package org.groupfive.gymapi.controller;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.dto.*;
import org.groupfive.gymapi.model.*;
import org.groupfive.gymapi.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // -------------------- MIEMBROS --------------------
    @PostMapping("/miembros")
    public ResponseEntity<Miembro> crearMiembro(@RequestBody Miembro miembro) {
        return ResponseEntity.ok(adminService.crearMiembro(miembro));
    }

    @PutMapping("/miembros/{id}")
    public ResponseEntity<Miembro> editarMiembro(@PathVariable Long id, @RequestBody MiembroRequestDto dto) {
        return ResponseEntity.ok(adminService.editarMiembro(id, dto));
    }

    @GetMapping("/miembros")
    public ResponseEntity<List<Miembro>> listarMiembros() {
        return ResponseEntity.ok(adminService.listarMiembros());
    }

    @DeleteMapping("/miembros/{id}")
    public ResponseEntity<?> eliminarMiembro(@PathVariable Long id) {
        adminService.eliminarMiembro(id);
        return ResponseEntity.ok().body(java.util.Map.of("message", "Miembro eliminado correctamente"));
    }

    // -------------------- ENTRENADORES --------------------
    @PostMapping("/entrenadores")
    public ResponseEntity<Entrenador> crearEntrenador(@RequestBody Entrenador entrenador) {
        return ResponseEntity.ok(adminService.crearEntrenador(entrenador));
    }

    @PutMapping("/entrenadores/{id}")
    public ResponseEntity<Entrenador> editarEntrenador(@PathVariable Long id, @RequestBody EntrenadorRequestDTO dto) {
        return ResponseEntity.ok(adminService.actualizarEntrenador(id, dto));
    }

    @GetMapping("/entrenadores")
    public ResponseEntity<List<Entrenador>> listarEntrenadores() {
        return ResponseEntity.ok(adminService.listarEntrenadores());
    }

    @DeleteMapping("/entrenadores/{id}")
    public ResponseEntity<?> eliminarEntrenador(@PathVariable Long id) {
        adminService.eliminarEntrenador(id);
        return ResponseEntity.ok().body(java.util.Map.of("message", "Entrenador eliminado correctamente"));
    }

    // -------------------- CLASES --------------------
    @PostMapping("/clases")
    public ResponseEntity<ClaseResponse> crearClase(@RequestBody ClaseRequest clase) {
        return ResponseEntity.ok(adminService.crearClase(clase));
    }

    @PutMapping("/clases/{id}")
    public ResponseEntity<ClaseResponse> editarClase(@PathVariable Long id, @RequestBody ClaseRequest clase) {
        return ResponseEntity.ok(adminService.editarClase(id, clase));
    }

    @GetMapping("/clases")
    public ResponseEntity<List<ClaseResponse>> listarClases() {
        return ResponseEntity.ok(adminService.listarClases());
    }

    @DeleteMapping("/clases/{id}")
    public ResponseEntity<?> eliminarClase(@PathVariable Long id) {
        adminService.eliminarClase(id);
        return ResponseEntity.ok().body(java.util.Map.of("message", "Clase eliminada correctamente"));
    }

    // -------------------- INSCRIPCIONES --------------------
    @PostMapping("/inscripciones")
    public ResponseEntity<Inscripcion> inscribir(
            @RequestParam Long miembroId,
            @RequestParam Long claseId) {

        Inscripcion inscripcion = adminService.inscribirMiembro(miembroId, claseId);
        return ResponseEntity.ok(inscripcion);
    }


    @GetMapping("/inscripciones")
    public ResponseEntity<List<Inscripcion>> listarInscripciones() {
        return ResponseEntity.ok(adminService.listarInscripciones());
    }

    @DeleteMapping("/inscripciones/{id}")
    public ResponseEntity<?> eliminarInscripcion(@PathVariable Long id) {
        adminService.eliminarInscripcion(id);
        return ResponseEntity.ok().body(java.util.Map.of("message", "Inscripción eliminada correctamente"));
    }

    // -------------------- ASISTENCIAS --------------------
    @PostMapping("/asistencias")
    public ResponseEntity<?> registrarAsistencia(@RequestParam Long claseId, @RequestParam Long miembroId) {
        adminService.registrarAsistencia(claseId, miembroId);
        return ResponseEntity.ok(java.util.Map.of("message", "Asistencia registrada correctamente"));
    }

    @GetMapping("/asistencias")
    public ResponseEntity<List<AsistenciaResponseDTO>> listarAsistencias() {
        return ResponseEntity.ok(adminService.listarAsistencias());
    }
}
