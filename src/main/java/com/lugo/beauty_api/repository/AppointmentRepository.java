package com.lugo.beauty_api.repository;

import com.lugo.beauty_api.model.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointments,Long> {


    @Query("""
    SELECT a FROM Appointments a
    WHERE a.employeeId.id = :employeeId
      AND a.date >= :from
      AND a.date < :to
""")
    List<Appointments> findAppointmentsByEmployeeAndRange(
            @Param("employeeId") Long employeeId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}
