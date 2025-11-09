package org.groupfive.gymapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorResumen{
    private Long id;
    private String nombre;
    private String especialidad;
}