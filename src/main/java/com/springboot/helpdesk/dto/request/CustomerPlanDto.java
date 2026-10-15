package com.springboot.helpdesk.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerPlanDto(
        @NotBlank(message = "activation date is required")
        @NotNull(message = "activation date is required")
        LocalDate activationDate,

        @NotBlank(message = "end date is required")
        @NotNull(message = "end date is required")
        LocalDate endDate,

        String coupon,

        @NotBlank(message = "amount paid is required")
        @NotNull(message = "amount paid is required")
        Double amountPaid
){
}
