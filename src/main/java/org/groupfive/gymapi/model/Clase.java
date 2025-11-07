package org.groupfive.gymapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private String horario;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "entrenador_id")
    private Entrenador entrenador;

    @OneToMany(mappedBy = "clase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscripcion> inscritos = new ArrayList<>();
}
