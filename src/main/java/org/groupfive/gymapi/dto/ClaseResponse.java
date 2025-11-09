package org.groupfive.gymapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.groupfive.gymapi.model.Entrenador;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaseResponse {
    private Long id;
    private String nombre;
    private Integer cupoMaximo;
    private String horario;
    private EntrenadorResumen entrenador;
}


