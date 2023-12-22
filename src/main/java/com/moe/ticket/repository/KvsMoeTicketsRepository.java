package com.moe.ticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moe.ticket.model.KvsMoeTickets;



public interface KvsMoeTicketsRepository extends JpaRepository<KvsMoeTickets, String>{
List<KvsMoeTickets>	findAllByTeacherEmployeeCode(String teacherEmployeeCode);
}
