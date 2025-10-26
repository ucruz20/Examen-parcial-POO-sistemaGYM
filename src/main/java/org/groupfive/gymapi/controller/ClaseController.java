package org.groupfive.gymapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.groupfive.gymapi.dto.ClaseDTO;
import org.groupfive.gymapi.model.Clase;
import org.groupfive.gymapi.service.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    @Autowired
    private ClaseService claseService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearClase(@RequestBody ClaseDTO claseDTO) {
        Clase nuevaClase = claseService.crearClase(claseDTO);
        return ResponseEntity.ok("creando clase: " + nuevaClase.getNombre());
    }

    @PostMapping("/{idClase}/inscribir/{idMiembro}")
    public ResponseEntity<String> inscribirMiembro(@PathVariable Long idClase, @PathVariable Long idMiembro) {
        //TODO: inscribir miembro con el servicio ClaseService
        boolean resultado = claseService.inscribirMiembro(idClase, idMiembro);
        return resultado ?
            ResponseEntity.ok("Miembro inscrito con exito") :
            ResponseEntity.badRequest().body("Cupo lleno o ya inscrito");
    }
}
