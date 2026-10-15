package com.springboot.helpdesk.controller;

import com.springboot.helpdesk.dto.request.ExecutiveDto;
import com.springboot.helpdesk.dto.response.TicketInfoDto;
import com.springboot.helpdesk.model.Executive;
import com.springboot.helpdesk.service.ExecutiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ExecutiveController {
    private final ExecutiveService executiveService;

    /*
    DTO (name,email,contact)
    validation
    Map DTO to Entity
    Insert Entity IN Db
    * */

    @PostMapping("/api/executive/add")
    public void insertExecutive(@Valid @RequestBody ExecutiveDto executiveDto,
                                Principal principal) {
        executiveService.add(executiveDto, principal.getName()); //principal.getName() gives the usernmae of logged in user
    }


}
