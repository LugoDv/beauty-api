package com.lugo.beauty_api.service;

import com.lugo.beauty_api.model.*;
import com.lugo.beauty_api.model.dto.AgendaRequestDTO;
import com.lugo.beauty_api.model.dto.AppointmentCreateDTO;
import com.lugo.beauty_api.model.dto.TimeSlotDTO;
import com.lugo.beauty_api.repository.AppointmentRepository;
import com.lugo.beauty_api.repository.AvailabilityRepository;
import com.lugo.beauty_api.repository.ServiceRepository;
import com.lugo.beauty_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService {

    private final ServiceRepository serviceRepository;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepo;

    @Override
    public List<TimeSlotDTO> getAvailableSlots(AgendaRequestDTO dto) {
        // 1. Sumar duración total de servicios
        int totalMinutes = getTotalMinutes(dto.getSelectedServiceIds());

        // 2. Obtener citas existentes del empleado
        List<Appointments> citas = appointmentRepository.findAppointmentsByEmployeeAndRange(
                dto.getEmployeeId(), dto.getFrom(), dto.getTo()
        );

        // 3. Generar bloques dinámicos según regla (Lun a Sab de 9 a 17)
        Set<DayOfWeek> diasLaborales = EnumSet.range(DayOfWeek.MONDAY, DayOfWeek.SATURDAY);
        LocalTime horaInicio = LocalTime.of(9, 0);
        LocalTime horaFin = LocalTime.of(17, 0);

        List<TimeSlotDTO> bloques = new ArrayList<>();
        LocalDate fecha = dto.getFrom().toLocalDate();

        while (!fecha.isAfter(dto.getTo().toLocalDate())) {
            if (diasLaborales.contains(fecha.getDayOfWeek())) {
                LocalDateTime cursor = fecha.atTime(horaInicio);
                LocalDateTime finDelDia = fecha.atTime(horaFin);

                while (!cursor.plusMinutes(totalMinutes).isAfter(finDelDia)) {
                    LocalDateTime slotEnd = cursor.plusMinutes(totalMinutes);

                    // Verificar si se solapa con alguna cita
                    LocalDateTime finalCursor = cursor;
                    LocalDateTime finalEnd = slotEnd;
                    boolean ocupado = citas.stream().anyMatch(cita -> {
                        int duracion = cita.getServices().stream()
                                .mapToInt(cs -> cs.getService().getDurationMinutes())
                                .sum();
                        LocalDateTime citaEnd = cita.getDate().plusMinutes(duracion);
                        return finalCursor.isBefore(citaEnd) && cita.getDate().isBefore(finalEnd);
                    });

                    if (!ocupado) {
                        bloques.add(new TimeSlotDTO(cursor, slotEnd, "AVAILABLE"));
                    }

                    cursor = cursor.plusMinutes(15);
                }
            }
            fecha = fecha.plusDays(1);
        }

        return bloques;
    }

    @Override
    public Appointments createAppointment(AppointmentCreateDTO dto) {
        int totalMinutes = getTotalMinutes(dto.getSelectedServiceIds());

        LocalDateTime endTime = dto.getStartTime().plusMinutes(totalMinutes);

        // Verificar solapamiento con citas existentes
        List<Appointments> citas = appointmentRepository.findAppointmentsByEmployeeAndRange(
                dto.getEmployeeId(), dto.getStartTime().minusHours(1), endTime.plusHours(1)
        );

        boolean conflicto = citas.stream().anyMatch(cita -> {
            int duracion = cita.getServices().stream()
                    .mapToInt(cs -> cs.getService().getDurationMinutes())
                    .sum();
            LocalDateTime citaEnd = cita.getDate().plusMinutes(duracion);
            return dto.getStartTime().isBefore(citaEnd) && cita.getDate().isBefore(endTime);
        });

        if (conflicto) {
            throw new RuntimeException("Ese horario no está disponible.");
        }

        // Crear y guardar la cita
        Appointments cita = new Appointments();
        cita.setUserId(userRepo.findById(dto.getClientId()).orElseThrow());
        cita.setEmployeeId(userRepo.findById(dto.getEmployeeId()).orElseThrow());
        cita.setDate(dto.getStartTime());
        cita.setStatus(StatusAppointments.PENDING);

        List<AppointmentsServices> servicios = new ArrayList<>();
        for (Long id : dto.getSelectedServiceIds()) {
            ServicesBeauty servicio = serviceRepository.findById(id).orElseThrow();
            AppointmentsServices newAppointmentsServices = new AppointmentsServices();

            newAppointmentsServices.setService(servicio);
            newAppointmentsServices.setAppointment(cita);
            newAppointmentsServices.setPrice(servicio.getPrice());
            servicios.add(newAppointmentsServices);
        }

        cita.setServices(servicios);
        return appointmentRepository.save(cita);


    }

    private int getTotalMinutes(List<Long> dto) {
        int totalMinutes = 0;
        totalMinutes = serviceRepository.findAllById(dto).stream()
                .mapToInt(ServicesBeauty::getDurationMinutes)
                .sum();
        return totalMinutes;
    }
}