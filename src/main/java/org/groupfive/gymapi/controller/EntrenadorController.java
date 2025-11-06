package org.groupfive.gymapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.groupfive.gymapi.service.EntrenadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entrenadores")
@RequiredArgsConstructor
public class EntrenadorController {

    private final EntrenadorService servicioEntrenador;

    @PostMapping("/clases")
    public ResponseEntity<Clase> generarClase(@Valid @RequestBody CreateClaseRequest datosClase) {
        Clase claseCreada = servicioEntrenador.crearClase(datosClase);
        return new ResponseEntity<>(claseCreada, HttpStatus.CREATED);
    }
}