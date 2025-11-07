package org.groupfive.gymapi.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(name = "id_entrenador")
    private Long entrenadorId;

    private List<Long> inscritos;
}
