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
    private String entrenador; // TODO: usar modelo Entrenador
    private List<String> inscritos; // TODO: usar modelo Miembro
}
