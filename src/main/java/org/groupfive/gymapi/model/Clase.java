package org.groupfive.gymapi.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clase {
    private Long id;
    private String nombre;
    private int cupoMaximo;
    private String horario;
    private Long entrenadorId;
    private List<Long> inscritos;
}
