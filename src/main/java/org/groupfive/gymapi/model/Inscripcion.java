package org.groupfive.gymapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "inscripcion")

public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaInscripcion;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "miembro_id")
    @JsonIgnore
    private Miembro miembro;

    @ManyToOne
    @JoinColumn(name = "clase_id")
    @JsonIgnore
    private Clase clase;
}
