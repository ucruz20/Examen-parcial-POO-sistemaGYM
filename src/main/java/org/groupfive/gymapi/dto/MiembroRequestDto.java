package org.groupfive.gymapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiembroRequestDto {
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    @Size(max= 500, message = "La direccion tiene un maximo de 500 caracteres")
    private String direccion;
    @NotNull
    private String correo;
    @NotNull
    private String tipoMembresia;
    @NotNull
    private String telefono;
}
