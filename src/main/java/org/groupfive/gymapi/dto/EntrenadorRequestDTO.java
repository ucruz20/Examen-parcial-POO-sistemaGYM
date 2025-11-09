package org.groupfive.gymapi.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class EntrenadorRequestDTO {
    @NotNull
    private String nombre;
    @NotNull
    private String especialidad;
    @PositiveOrZero
    private BigDecimal salario;
}