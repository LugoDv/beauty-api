package com.lugo.beauty_api.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AppointmentCreateDTO {
    private Long clientId;
    private Long employeeId;
    private LocalDateTime startTime; // hora en la que quiere agendar
    private List<Long> selectedServiceIds;

}
