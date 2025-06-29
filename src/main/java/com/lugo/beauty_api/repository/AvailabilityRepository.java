package com.lugo.beauty_api.repository;

import com.lugo.beauty_api.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability,Long> {
    @Query("""
    SELECT a FROM Availability a
    WHERE a.employee.id = :employeeId
      AND a.startTime >= :from
      AND a.endTime <= :to
""")
    List<Availability> findAvailabilityByEmployeeAndRange(
            @Param("employeeId") Long employeeId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}
