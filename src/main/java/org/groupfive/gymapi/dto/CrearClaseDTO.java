package org.groupfive.gymapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrearClaseDTO {
    private String nombre;
    private int cupoMaximo;
    private String horario;
    private String entrenador; // TODO: usar modelo Entrenador
}
