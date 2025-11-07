package org.groupfive.gymapi.dto;

import java.time.LocalDateTime;

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
    private LocalDateTime horario;
    private Long entrenadorId;
}
