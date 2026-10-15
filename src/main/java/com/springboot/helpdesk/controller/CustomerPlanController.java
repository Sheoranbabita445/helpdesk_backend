package com.springboot.helpdesk.controller;

import com.springboot.helpdesk.dto.request.CustomerPlanDto;
import com.springboot.helpdesk.model.Plan;
import com.springboot.helpdesk.service.CustomerPlanService;
import com.springboot.helpdesk.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerPlanController {

    private final CustomerPlanService customerPlanService;


    /*
    API to add customer buys plan record in customer_plan table with all relevant fields
    * */
    @PostMapping("/api/customerplan/add/{customer_id}/{plan_id}")
    public void addCustomerPlan(@RequestParam Long customer_id,
                                @RequestParam Long plan_id,
                                @RequestBody CustomerPlanDto customerPlanDto){
        customerPlanService.addCustomerPlan(customerPlanDto,customer_id,plan_id);
    }


    /*
     * GET API : to fetch all plans based on customer username.
     * */

    @GetMapping("/api/customerplan/getall")
    public List<Plan> getPlanByCustomerUsername(
            @RequestParam("username") String customerUsername,
            @RequestParam(name = "page",required = false,defaultValue = "0") Integer page,
            @RequestParam(name = "size",required = false,defaultValue = "20") Integer size
    ){
        return null;
    }
}