package com.lugo.beauty_api.controller;

import com.lugo.beauty_api.model.Appointments;
import com.lugo.beauty_api.model.dto.AgendaRequestDTO;
import com.lugo.beauty_api.model.dto.AppointmentCreateDTO;
import com.lugo.beauty_api.model.dto.TimeSlotDTO;
import com.lugo.beauty_api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/calendar/slots")
    public ResponseEntity<List<TimeSlotDTO>> getAvailableSlots(@RequestBody AgendaRequestDTO dto){

        List<TimeSlotDTO> slots = appointmentService.getAvailableSlots(dto);

        return ResponseEntity.ok(slots);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AppointmentCreateDTO dto) {
        try {
            Appointments nueva = appointmentService.createAppointment(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

}
