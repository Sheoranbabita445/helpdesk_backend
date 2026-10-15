package com.springboot.helpdesk.dto.response;

import com.springboot.helpdesk.enums.Status;

public record TicketCustomerCountDto(
        Status ticketStatus,
        long numberOfCustomers
) {
}
