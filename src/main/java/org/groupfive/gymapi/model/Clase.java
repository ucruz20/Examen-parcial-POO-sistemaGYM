package org.groupfive.gymapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "clase")
public class Clase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clase")
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "cupo_max", nullable = false)
    private int cupoMaximo;

    @Column(name = "horario", nullable = false)
    private LocalDateTime horario;
    
    @ManyToOne(optional = false)
    @Column(name = "id_entrenador")
    private Long entrenadorId;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL)
    private List<Inscripcion> inscritos;
}
