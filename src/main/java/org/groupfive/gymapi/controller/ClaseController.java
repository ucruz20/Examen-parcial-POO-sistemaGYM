package org.groupfive.gymapi.controller;

import java.util.List;

import org.groupfive.gymapi.dto.ClaseDTO;
import org.groupfive.gymapi.dto.CrearClaseDTO;
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
    public ResponseEntity<List<ClaseDTO>> verClases() {
        List<ClaseDTO> clases = claseService.obtenerClases();
        return ResponseEntity.ok(clases);
    }

    @PostMapping("/crear")
    public ResponseEntity<ClaseDTO> crearClase(@RequestBody CrearClaseDTO crearClaseDTO) {
        ClaseDTO nuevaClaseDTO = claseService.crearClase(crearClaseDTO);
        return ResponseEntity.ok(nuevaClaseDTO);
    }

    @PostMapping("/{idClase}/inscribir/{idMiembro}")
    public ResponseEntity<String> inscribirMiembro(@PathVariable Long idClase, @PathVariable Long idMiembro) {
        boolean resultado = claseService.inscribirMiembro(idClase, idMiembro);
        return resultado ?
            ResponseEntity.ok("Miembro inscrito con exito") :
            ResponseEntity.status(409).body("Cupo lleno o ya inscrito");
    }

    @PutMapping("/{idClase}/editar")
    public ResponseEntity<ClaseDTO> editarInfo(@PathVariable Long idClase, @RequestBody CrearClaseDTO clase) {
        ClaseDTO claseEditada = claseService.editarInfo(idClase, clase);
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
