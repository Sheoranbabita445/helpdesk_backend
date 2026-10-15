package com.springboot.helpdesk.mapper;

import com.springboot.helpdesk.dto.request.PlanDto;
import com.springboot.helpdesk.model.Plan;
import org.springframework.stereotype.Component;

@Component
public class PlanMapper {

    public static Plan convertDtoToEntity(PlanDto dto){
        Plan plan = new Plan();
        plan.setPlanType(dto.planType());
        plan.setPrice(dto.price());
        return plan;
    }
}
