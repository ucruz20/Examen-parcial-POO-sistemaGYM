package org.groupfive.gymapi.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "miembro")

public class Miembro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    @Column(length = 500)
    private String direccion;
    @Column(nullable = false)
    private String tipoMembresia;

    @OneToMany(mappedBy = "miembro", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Inscripcion> inscripciones = new ArrayList<>();
}
