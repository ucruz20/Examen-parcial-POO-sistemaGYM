package org.groupfive.gymapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/clases")
public class ClaseController {

    @PostMapping("/crear")
    public ResponseEntity<String> crearClase(@RequestBody String clase) {
        //TODO: crear clase con el servicio ClaseService
        return ResponseEntity.ok("creando clase: " + clase);
    }

    @PostMapping("/{idClase}/inscribir/{idMiembro}")
    public ResponseEntity<String> inscribirMiembro(@PathVariable Long idClase, @PathVariable Long idMiembro) {
        //TODO: inscribir miembro con el servicio ClaseService
        return ResponseEntity.ok("incribir al miembro " + idMiembro + " en la clase " + idClase);
    }
}
