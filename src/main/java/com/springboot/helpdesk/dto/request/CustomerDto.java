package com.springboot.helpdesk.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerDto(
        @NotBlank(message = "Name is required")
        @NotNull(message = "Name is required")
        String name,
        @NotBlank(message = "City is required")
        @NotNull(message = "CIty is required")
        String city,
        @NotBlank(message = "Email is required")
        @NotNull(message = "Email is required")
        @Email(message = "Email seems invalid")
        String email,
        @NotNull(message = "Username is required")
        @NotBlank(message = "Username is required")
        String username,
        @NotNull(message = "Password is required")
        @NotBlank(message = "Password is required")
        String password
) {
}