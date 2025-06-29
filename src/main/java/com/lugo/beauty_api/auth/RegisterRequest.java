package com.lugo.beauty_api.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name cannot be blank")
    @Length(min = 5, message = "Password should be at least 5 characters long")
    private String password;

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name cannot be blank")
    private String phoneNumber;

}
