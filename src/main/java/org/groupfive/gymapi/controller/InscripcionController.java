package org.groupfive.gymapi.controller;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.model.Inscripcion;
import org.groupfive.gymapi.service.InscripcionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@RequiredArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    @GetMapping
    public List<Inscripcion> listar() {
        return inscripcionService.listar();
    }

    @PostMapping
    public ResponseEntity<Inscripcion> crearInscripcion(
            @RequestParam Long miembroId,
            @RequestParam Long claseId) {

        Inscripcion inscripcion = inscripcionService.guardar(miembroId, claseId);
        return ResponseEntity.ok(inscripcion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerPorId(@PathVariable Long id) {
        return inscripcionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        inscripcionService.eliminar(id);
        return ResponseEntity.ok("Inscripción eliminada correctamente");
    }
}
