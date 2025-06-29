package com.lugo.beauty_api.mapper;

import com.lugo.beauty_api.auth.RegisterRequest;
import com.lugo.beauty_api.model.Rol;
import com.lugo.beauty_api.model.User;
import com.lugo.beauty_api.model.dto.UserDto;

import java.time.format.DateTimeFormatter;

public class UserMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    public static UserDto toDto(User user) {
        if (user == null) return null;
        return UserDto.builder()
               .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .roleName(user.getRole() != null ? user.getRole().getName() : null)
                .createdAt(user.getCreatedAt() != null ? user.getCreatedAt().format(FORMATTER) : null)
                .updatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().format(FORMATTER) : null)
                .build();
    }

    public static User toEntity(RegisterRequest dto) {
        if (dto == null) return null;
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhoneNumber());
        user.setPassword(dto.getPassword());
        Rol rolUsuario = new Rol();
        rolUsuario.setName("CLIENT");
        user.setRole(rolUsuario);
        return user;
    }

    public static User toEntityDto(UserDto dto) {
        if (dto == null) return null;
        User user = new User();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        Rol rolUsuario = new Rol();
        rolUsuario.setName("CLIENT");
        user.setRole(rolUsuario);
        return user;
    }
}