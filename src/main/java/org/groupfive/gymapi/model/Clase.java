package org.groupfive.gymapi.model;

import java.util.List;

public class Clase {
    private Long id;
    private String nombre;
    private int cupoMaximo;
    private String horario;
    private String entrenador; // TODO: usar modelo Entrenador
    private List<String> inscritos; // TODO: usar modelo Miembro
    
    // --- Constructores ---

    public Clase(Long id, String nombre, int cupoMaximo, String horario, String entrenador, List<String> inscritos) {
        this.id = id;
        this.nombre = nombre;
        this.cupoMaximo = cupoMaximo;
        this.horario = horario;
        this.entrenador = entrenador;
        this.inscritos = inscritos;
    }

    public Clase() {
    }

    // --- Getters ---

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public String getHorario() {
        return horario;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public List<String> getInscritos() {
        return inscritos;
    }

    // --- Setters ---

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public void setInscritos(List<String> inscritos) {
        this.inscritos = inscritos;
    }
}
