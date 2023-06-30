package com.Castro.cmsApp.helpdesk.controllers;

import com.Castro.cmsApp.helpdesk.models.Ticket;
import com.Castro.cmsApp.helpdesk.services.TicketService;
import com.Castro.cmsApp.helpdesk.services.TicketStatusService;
import com.Castro.cmsApp.parameters.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class TicketController {
	
	@Autowired private TicketService ticketService;
	@Autowired private TicketStatusService ticketStatusService;
	@Autowired private ClientService clientService;
	
	public TicketController(TicketService ticketService, TicketStatusService ticketStatusService, ClientService clientService) {
		this.ticketService = ticketService;
		this.ticketStatusService = ticketStatusService;
		this.clientService = clientService;
	}

	//Get All Tickets
	@GetMapping("/helpdesk/tickets")
	public String findAll(Model model){		
		model.addAttribute("tickets", ticketService.findAll());
		model.addAttribute("clients", clientService.findAll());
		model.addAttribute("ticketStatuses", ticketStatusService.findAll());
		return "/helpdesk/tickets";
	}	
	
	@RequestMapping("/tickets/findById")
	@ResponseBody
	public Optional<Ticket> findById(Integer id)
	{
		return ticketService.findById(id);
	}
	
	//Add Ticket
	@PostMapping(value="/tickets/addNew")
	public String addNew(Ticket ticket) {
		ticketService.save(ticket);
		return "redirect:/ticketList";
	}	
	
	@RequestMapping(value="/tickets/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Ticket ticket) {
		ticketService.save(ticket);
		return "redirect:/ticketList";
	}
	
	@RequestMapping(value="/tickets/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		ticketService.delete(id);
		return "redirect:/ticketList";
	}
}
