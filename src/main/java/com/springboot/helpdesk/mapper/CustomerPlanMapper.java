package com.springboot.helpdesk.mapper;

import com.springboot.helpdesk.dto.request.CustomerPlanDto;
import com.springboot.helpdesk.model.CustomerPlan;
import org.springframework.stereotype.Component;

@Component
public class CustomerPlanMapper {

    public static CustomerPlan convertDtoToEntity(CustomerPlanDto dto){
        CustomerPlan customerPlan = new CustomerPlan();
        customerPlan.setCoupon(dto.coupon());
        customerPlan.setAmountPaid(dto.amountPaid());
        customerPlan.setActivationDate(dto.activationDate());
        customerPlan.setEndDate(dto.endDate());
        return customerPlan;
    }
}
