package com.springboot.helpdesk.service;

import com.springboot.helpdesk.dto.request.TicketDto;
import com.springboot.helpdesk.dto.response.TicketCustomerCountDto;
import com.springboot.helpdesk.dto.response.TicketInfoDtoV2;
import com.springboot.helpdesk.dto.response.TicketInfoDto;
import com.springboot.helpdesk.dto.response.TicketRespDto;
import com.springboot.helpdesk.enums.Priority;
import com.springboot.helpdesk.enums.Status;
import com.springboot.helpdesk.exception.OwnershipException;
import com.springboot.helpdesk.exception.ResourceNotFoundException;
import com.springboot.helpdesk.mapper.TicketMapper;
import com.springboot.helpdesk.model.Customer;
import com.springboot.helpdesk.model.Executive;
import com.springboot.helpdesk.model.Ticket;
import com.springboot.helpdesk.repository.CustomerRepository;
import com.springboot.helpdesk.repository.ExecutiveRepository;
import com.springboot.helpdesk.repository.TicketRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final ExecutiveRepository executiveRepository;

    public void add(String customerUsername, @Valid TicketDto ticketDto) {
        // Step 1: Fetch Customer Object from DB using given customerId
        /*
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Customer id is invalid"));

         */

        Customer customer =  customerRepository.getByUsername(customerUsername);

        // Step 2: Map TicketDto to Ticket entity
        Ticket ticket = TicketMapper.convertDtoToEntity(ticketDto);

        //Step 3: assign status to ticket
        ticket.setStatus(Status.OPEN);

        //Step 4: assign customer to ticket
        ticket.setCustomer(customer);

        //Step 5: save ticket to DB
        ticketRepository.save(ticket);
    }

    public void assignExecutive(Long executiveId, Long ticketId, String managerUsername) {
        // Step 1: Fetch executive from DB using executiveId
        Executive executive = executiveRepository.findById(executiveId)
                .orElseThrow(()-> new ResourceNotFoundException("executive not found"));

        // Step 1.5 Does this executive belong to the team of logged-in manager?
        if(! (executive.getManager().getUser().getUsername().equals(managerUsername))){
            throw new OwnershipException("Operation not allowed! contact admin");
        }

        // Step 2: Fetch ticket from DB using ticketId
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket not found"));

        // Step 3: Attach executive to ticket
        ticket.setExecutive(executive);

        // Step 4: Save ticket
        ticketRepository.save(ticket);
    }

    public List<TicketInfoDto> getTicketsByCustomerId(Long customerId, int page, int size) {
        // Validate customerId
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer Id Invalid"));

        Pageable pageable = PageRequest.of(page,size);
        return ticketRepository.getTicketByCustomerId(customerId,pageable);
    }

    public List<TicketInfoDto> getTicketsByCustomerUsername(String customerUsername, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return ticketRepository.getTicketsByCustomerUsername(customerUsername,pageable);
    }

    public List<TicketInfoDtoV2> getTicketsByExecutiveId(String executiveUsername, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        return ticketRepository.getTicketsByExecutiveUsername(executiveUsername,pageable);
    }

    public List<TicketCustomerCountDto> getStatusWithCustomerCount() {
        return ticketRepository.getStatusWithCustomerCount();
    }

    public List<TicketRespDto> getTicketsByPriority(Priority priority) {
        List<Ticket> list = ticketRepository.findByPriority(priority);
        // Map List<Ticket> to List<TicketDto> using Mapper
        return list
                .stream()
                .map(TicketMapper :: convertEntityToDto)
                .toList();
    }

    public List<TicketRespDto> getTicketsByStatus(Status status) {
        List<Ticket> list = ticketRepository.findByStatus(status);
        // Map List<Ticket> to List<TicketDto> using Mapper
        return list
                .stream()
                .map(TicketMapper :: convertEntityToDto)
                .toList();
    }

    public List<TicketRespDto> getTicketsByPriorityAndStatus(Priority priority,Status status) {
        List<Ticket> list = ticketRepository.findByPriorityAndStatus(priority,status);
        // Map List<Ticket> to List<TicketDto> using Mapper
        return list
                .stream()
                .map(TicketMapper :: convertEntityToDto)
                .toList();
    }

    public List<TicketRespDto> dynamicFilterTicket(Priority priority,
                                                   Status status,
                                                   LocalDate fromDate,
                                                   LocalDate toDate) {

        return ticketRepository.dynamicFilterTicket(priority,
                status,
                fromDate == null ? null: fromDate.atStartOfDay(ZoneOffset.UTC).toInstant(),
                toDate == null? null:toDate.atStartOfDay(ZoneOffset.UTC).toInstant());
    }
}
