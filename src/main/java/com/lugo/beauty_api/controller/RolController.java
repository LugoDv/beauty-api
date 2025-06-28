package com.lugo.beauty_api.controller;

import com.lugo.beauty_api.model.Rol;
import com.lugo.beauty_api.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rols")
public class RolController {

    @Autowired

    private RolService rolService;

    @PostMapping
    public ResponseEntity<Rol> createRol(@RequestBody Rol rol) {
        Rol creado = rolService.saveRol(rol);
        return ResponseEntity.ok(creado);
    }

    @GetMapping
    public ResponseEntity<List<Rol>> getRols() {
        List<Rol> roles = rolService.getRols();
        return ResponseEntity.ok(roles);
    }

}
