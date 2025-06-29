package com.lugo.beauty_api.controller;

import com.lugo.beauty_api.model.ServicesBeauty;
import com.lugo.beauty_api.service.ServicesBeautyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services-beauty")
@RequiredArgsConstructor
public class ServicesBeautyController {

    private final ServicesBeautyService servicesBeautyService;

    @GetMapping
    public ResponseEntity<List<ServicesBeauty>> findAll() {
        return ResponseEntity.ok(servicesBeautyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicesBeauty> findById(@PathVariable Long id) {
        return ResponseEntity.ok(servicesBeautyService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ServicesBeauty> create(@RequestBody ServicesBeauty service) {
        return ResponseEntity.ok(servicesBeautyService.save(service));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ServicesBeauty> update(@PathVariable Long id, @RequestBody ServicesBeauty service) {
        service.setId(id);
        return ResponseEntity.ok(servicesBeautyService.update(service));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicesBeautyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}