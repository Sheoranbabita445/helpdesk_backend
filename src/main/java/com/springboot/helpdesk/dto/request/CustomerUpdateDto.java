package com.springboot.helpdesk.dto.request;

import jakarta.validation.constraints.Pattern;

public record CustomerUpdateDto(
        // @Pattern(regexp = "[a-zA-Z ]+")
        String name,
        String city
) {
}