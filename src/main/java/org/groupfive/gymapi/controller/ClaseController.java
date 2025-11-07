package org.groupfive.gymapi.controller;

import java.util.List;

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
        List<ClaseResponse> clases = claseService.obtenerClases();
        return ResponseEntity.ok(clases);
    }

    @PostMapping("/crear")
    public ResponseEntity<ClaseResponse> crearClase(@RequestBody ClaseRequest ClaseRequest) {
        ClaseResponse nuevaClaseResponse = claseService.crearClase(ClaseRequest);
        return ResponseEntity.ok(nuevaClaseResponse);
    }

    @PutMapping("/{idClase}/editar")
    public ResponseEntity<ClaseResponse> editarInfo(@PathVariable Long idClase, @RequestBody ClaseRequest clase) {
        ClaseResponse claseEditada = claseService.editarInfo(idClase, clase);
        return (claseEditada != null) ?
            ResponseEntity.ok(claseEditada) :
            ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idClase}")
    public ResponseEntity<String> eliminar(@PathVariable Long idClase) {
        boolean respuesta = claseService.eliminar(idClase);
        return respuesta ?
            ResponseEntity.ok("Clase eliminada con exito") :
            ResponseEntity.notFound().build();
    }
}
