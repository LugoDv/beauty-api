package com.lugo.beauty_api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String roleName; // Nombre del rol, ej: EMPLOYEE, CLIENT
    private String createdAt; // Formato ISO 8601
    private String updatedAt; // Formato ISO 8601
}
