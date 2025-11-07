package org.groupfive.gymapi.dto;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class AttendanceRequest {
    @NotNull
    private Long claseId;
    @NotNull
    private Long miembroId;
}
