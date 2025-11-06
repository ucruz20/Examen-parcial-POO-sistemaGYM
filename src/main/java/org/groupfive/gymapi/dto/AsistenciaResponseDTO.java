package org.groupfive.gymapi.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AsistenciaResponseDTO {
    private Long idAsistencia;
    private Long idMiembro;
    private Long idClase;
    private LocalDateTime fechaHora;
    private boolean presente;
}