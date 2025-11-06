package org.groupfive.gymapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "clase")
public class Clase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String instructor;
    private LocalDate fecha;
    private String horario;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;
}

