package org.groupfive.gymapi.controller;

import java.util.List;
import java.util.Map;

import jakarta.validation.Valid;
import org.groupfive.gymapi.dto.ClaseResponse;
import org.groupfive.gymapi.dto.ClaseRequest;
import org.groupfive.gymapi.service.ClaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/clases")
@RequiredArgsConstructor
public class ClaseController {
    private final ClaseService claseService;

    @GetMapping
    public ResponseEntity<List<ClaseResponse>> verClases() {
        List<ClaseResponse> clases = claseService.getClasses();
        return ResponseEntity.ok(clases);
    }

    @PostMapping("/crear")
    public ResponseEntity<ClaseResponse> createClase(@Valid @RequestBody ClaseRequest ClaseRequest) {
        ClaseResponse nuevaClaseResponse = claseService.createClass(ClaseRequest);
        return ResponseEntity.ok(nuevaClaseResponse);
    }

    @PutMapping("/{idClase}")
    public ResponseEntity<ClaseResponse> updateClase(@PathVariable Long idClase,@Valid @RequestBody ClaseRequest request) {
        ClaseResponse actualizada = claseService.editorInfo(idClase, request);
         return   ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{idClase}")
    public ResponseEntity<String> eliminar(@PathVariable Long idClase) {
         claseService.eliminate(idClase);
         return
            ResponseEntity.ok(Map.of("message", "Clase eliminada con exito", "idEliminado", idClase).toString());
    }
}
