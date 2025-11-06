package org.groupfive.gymapi.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class EntrenadorRequestDTO {
    @NotNull
    private String nombre;
    @NotNull
    private String especialidad;
    private String horario;
    @NotNull
    private BigDecimal salario;
}