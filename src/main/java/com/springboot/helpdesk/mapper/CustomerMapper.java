package com.springboot.helpdesk.mapper;

import com.springboot.helpdesk.dto.request.CustomerDto;
import com.springboot.helpdesk.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public static Customer mapDtoToEntity(CustomerDto dto){
        Customer customer = new Customer();
        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setCity(dto.city());
        return customer;
    }
}
