package com.Castro.cmsApp.helpdesk.services;

import com.Castro.cmsApp.helpdesk.models.Ticket;
import com.Castro.cmsApp.helpdesk.repositories.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

	// Get All Tickets
	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	// Get Ticket By Id
	public Optional<Ticket> findById(int id) {
		return ticketRepository.findById(id);
	}

	// Delete Ticket
	public void delete(int id) {
		ticketRepository.deleteById(id);
	}

	// Update Ticket
	public void save(Ticket invoice) {
		ticketRepository.save(invoice);
	}

}
