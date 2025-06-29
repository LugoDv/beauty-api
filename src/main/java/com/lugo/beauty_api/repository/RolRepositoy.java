package com.lugo.beauty_api.repository;

import com.lugo.beauty_api.model.Rol;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolRepositoy  extends CrudRepository<Rol,Long> {

    Optional<Rol> findByName(String name); // ej: "EMPLOYEE"

}
