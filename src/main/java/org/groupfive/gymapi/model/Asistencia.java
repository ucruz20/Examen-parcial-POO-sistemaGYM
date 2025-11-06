package org.groupfive.gymapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "asistencia")
@Data
@NoArgsConstructor
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsistencia;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_miembro", nullable = false)
    private Miembro miembro;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clase", nullable = false)
    private Clase sesion;
    private LocalDateTime fechaHora = LocalDateTime.now();
    private boolean presente = true;
}