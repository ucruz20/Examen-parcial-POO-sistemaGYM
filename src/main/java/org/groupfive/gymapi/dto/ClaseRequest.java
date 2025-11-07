package org.groupfive.gymapi.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaseRequest {
    @NotBlank
    private String nombre;

    @NotNull
    @Min(1)
    private int cupoMaximo;

    @NotBlank
    private String horario;

    @NotNull
    private Long entrenadorId;
}
