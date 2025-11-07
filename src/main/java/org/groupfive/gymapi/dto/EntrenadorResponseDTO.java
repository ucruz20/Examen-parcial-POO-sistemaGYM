package org.groupfive.gymapi.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class EntrenadorResponseDTO {
    private Long id;
    private String nombre;
    private String especialidad;
    private String horario;
    private BigDecimal salario;
}