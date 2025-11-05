package org.groupfive.gymapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaseResponse {
    private Long id;
    private String nombre;
    private int cupoMaximo;
    private String horario;
    private Long entrenadorId;
}
