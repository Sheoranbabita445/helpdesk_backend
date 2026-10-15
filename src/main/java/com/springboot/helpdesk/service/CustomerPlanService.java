package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.CustomerPlanDto;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.CustomerPlanMapper;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.CustomerPlan;
import com.springboot.helpdesk.model.Plan;
import com.springboot.helpdesk.repository.CustomerPlanRepository;
import com.springboot.helpdesk.repository.CustomerRepository;
import com.springboot.helpdesk.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerPlanService {

    private final CustomerRepository customerRepository;
    private final PlanRepository planRepository;
    private final CustomerPlanRepository customerPlanRepository;

    public void addCustomerPlan(CustomerPlanDto customerPlanDto, Long customerId, Long planId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new ResourceNotFoundException("Customer id is invalid"));

        Plan plan = planRepository.findById(planId).orElseThrow(() ->
                new ResourceNotFoundException("Customer id is invalid"));

        CustomerPlan customerPlan = CustomerPlanMapper.convertDtoToEntity(customerPlanDto);
        customerPlan.setCustomer(customer);
        customerPlan.setPlan(plan);

        customerPlanRepository.save(customerPlan);

    }
}
