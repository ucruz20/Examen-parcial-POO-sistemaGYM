package org.groupfive.gymapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrimerEntregaController {

    @GetMapping("/baseAPI")
    public String sayHello() {
        return "Hello World from Gym API!";
    }
}
