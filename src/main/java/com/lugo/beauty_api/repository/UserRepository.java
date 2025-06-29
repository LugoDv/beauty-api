package com.lugo.beauty_api.repository;

import com.lugo.beauty_api.model.Rol;
import com.lugo.beauty_api.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Autenticación
    Optional<User> findByEmail(String email);

    // Filtrar por rol
    List<User> findByRoleName(Rol role); // ej: EMPLOYEE, CLIENT

    // Buscar por nombre (útil para autocompletar)
    List<User> findByNameContainingIgnoreCase(String name);

    // Para asegurarte de que un email o teléfono no se repitan
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
    boolean existsByEmailOrPhone(String email, String phone);

    // Buscar empleados disponibles (más adelante podés combinar esto con disponibilidad)
    @Query("SELECT u FROM User u WHERE u.role.name = 'EMPLOYEE'")
    List<User> findAllEmployees();

    @Query("""
    SELECT DISTINCT u
    FROM User u
    JOIN u.availabilityList a
    WHERE u.role.name = 'EMPLOYEE'
      AND a.status = 'AVAILABLE'
      AND a.startTime <= CURRENT_TIMESTAMP
      AND a.endTime >= CURRENT_TIMESTAMP
""")
    List<User> findAvailableEmployeesNow();

}
