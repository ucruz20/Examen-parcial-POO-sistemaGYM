package org.groupfive.gymapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "entrenador")
@Data
@NoArgsConstructor
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String especialidad;
    private String horario;
    private BigDecimal salario;
    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Clase> clases;
}