package com.lugo.beauty_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TimeSlotDTO {

    private LocalDateTime start;
    private LocalDateTime end;
    private String status; // "AVAILABLE" o "OCCUPIED"

}
