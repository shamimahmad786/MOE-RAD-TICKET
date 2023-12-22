package com.moe.ticket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moe.ticket.model.KvsMoeTickets;
import com.moe.ticket.repository.KvsMoeTicketsRepository;

@Service
public class TicketImpl {

	@Autowired
	KvsMoeTicketsRepository kvsMoeTicketsRepository;
	
	public KvsMoeTickets initiateTicket(KvsMoeTickets data) {
		return kvsMoeTicketsRepository.save(data);
	}
	
	public List<KvsMoeTickets> getInitiatedTicket(KvsMoeTickets data) {
		return kvsMoeTicketsRepository.findAllByTeacherEmployeeCode(data.getTeacherEmployeeCode());
	}
}
