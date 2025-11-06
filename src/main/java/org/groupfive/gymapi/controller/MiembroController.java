package org.groupfive.gymapi.controller;

import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.model.Miembro;
import org.groupfive.gymapi.service.MiembroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/miembros")
@RequiredArgsConstructor
public class MiembroController {

    private final MiembroService miembroService;

    @GetMapping
    public List<Miembro> listar() {
        return miembroService.listar();
    }

    @PostMapping
    public Miembro crear(@RequestBody Miembro miembro) {
        return miembroService.guardar(miembro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Miembro> obtenerPorId(@PathVariable Long id) {
        return miembroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        miembroService.eliminar(id);
    }
}



