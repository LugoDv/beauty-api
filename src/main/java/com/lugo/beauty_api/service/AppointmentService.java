package com.lugo.beauty_api.service;

import com.lugo.beauty_api.model.Appointments;
import com.lugo.beauty_api.model.dto.AgendaRequestDTO;
import com.lugo.beauty_api.model.dto.AppointmentCreateDTO;
import com.lugo.beauty_api.model.dto.TimeSlotDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AppointmentService {

    List<TimeSlotDTO> getAvailableSlots(AgendaRequestDTO dto);
    Appointments createAppointment(AppointmentCreateDTO dto);
}
