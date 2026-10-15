package com.springboot.helpdesk.dto.request;

import com.springboot.helpdesk.enums.PlanType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanDto(
        @NotBlank(message = "Plan Type is required")
        @NotNull(message = "Plan Type is required")
        PlanType planType,
        @NotBlank(message = "price is required")
        @NotNull(message = "price is required")
        Double price
) {
}
