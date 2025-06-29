package com.lugo.beauty_api.model.dto;


import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AgendaRequestDTO {


    private Long employeeId;
    private LocalDateTime from;
    private LocalDateTime to;
    private List<Long> selectedServiceIds;

}
