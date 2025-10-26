package org.groupfive.gymapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.groupfive.gymapi.dto.ClaseDTO;
import org.groupfive.gymapi.dto.CrearClaseDTO;
import org.groupfive.gymapi.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    @Autowired
    private ClaseService claseService;

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
            ResponseEntity.badRequest().body("Cupo lleno o ya inscrito");
    }
}
