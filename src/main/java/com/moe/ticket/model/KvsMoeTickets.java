package com.moe.ticket.model;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "kvs_moe_ticket", schema="public")
public class KvsMoeTickets {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
private Long id;
@Column(name = "teacher_id")
private Integer teacherId;
@Column(name = "teacher_employee_code")
private String teacherEmployeeCode;
@Column(name = "ticket_id")
private String ticketId;
@Column(name = "ticket_to")
private String ticketTo;
@Column(name = "ticket_to_id")
private String ticketToId;
@Column(name = "ticket_from")
private String ticketFrom;
@Column(name = "ticket_from_id")
private String ticketFromId;
@Column(name = "ticket_subject")
private String ticketSubject;
@Column(name = "ticket_description")
private String ticketDescription;
@Column(name = "ticket_doc_upload_yn")
private String ticketDocUploadYn;
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
@Column(name = "ticketdate_time")
private Date   ticketdateTime;
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
@Column(name = "ticket_resolve_date_time")
private Date   ticketResolveDateTime;
@Column(name = "ticket_status")
private Integer ticketStatus;
@Column(name = "ticket_resolved_by")
private String ticketResolvedBy;
@Column(name = "folder_id")
private String folderId;


public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Integer getTeacherId() {
	return teacherId;
}
public void setTeacherId(Integer teacherId) {
	this.teacherId = teacherId;
}
public String getTeacherEmployeeCode() {
	return teacherEmployeeCode;
}
public void setTeacherEmployeeCode(String teacherEmployeeCode) {
	this.teacherEmployeeCode = teacherEmployeeCode;
}
public String getTicketId() {
	return ticketId;
}
public void setTicketId(String ticketId) {
	this.ticketId = ticketId;
}
public String getTicketTo() {
	return ticketTo;
}
public void setTicketTo(String ticketTo) {
	this.ticketTo = ticketTo;
}
public String getTicketFrom() {
	return ticketFrom;
}
public void setTicketFrom(String ticketFrom) {
	this.ticketFrom = ticketFrom;
}
public String getTicketSubject() {
	return ticketSubject;
}
public void setTicketSubject(String ticketSubject) {
	this.ticketSubject = ticketSubject;
}
public String getTicketDescription() {
	return ticketDescription;
}
public void setTicketDescription(String ticketDescription) {
	this.ticketDescription = ticketDescription;
}
public String getTicketDocUploadYn() {
	return ticketDocUploadYn;
}
public void setTicketDocUploadYn(String ticketDocUploadYn) {
	this.ticketDocUploadYn = ticketDocUploadYn;
}
public Date getTicketdateTime() {
	return ticketdateTime;
}
public void setTicketdateTime(Date ticketdateTime) {
	this.ticketdateTime = ticketdateTime;
}
public Date getTicketResolveDateTime() {
	return ticketResolveDateTime;
}
public void setTicketResolveDateTime(Date ticketResolveDateTime) {
	this.ticketResolveDateTime = ticketResolveDateTime;
}
public Integer getTicketStatus() {
	return ticketStatus;
}
public void setTicketStatus(Integer ticketStatus) {
	this.ticketStatus = ticketStatus;
}
public String getTicketResolvedBy() {
	return ticketResolvedBy;
}
public void setTicketResolvedBy(String ticketResolvedBy) {
	this.ticketResolvedBy = ticketResolvedBy;
}
public String getTicketToId() {
	return ticketToId;
}
public void setTicketToId(String ticketToId) {
	this.ticketToId = ticketToId;
}
public String getTicketFromId() {
	return ticketFromId;
}
public void setTicketFromId(String ticketFromId) {
	this.ticketFromId = ticketFromId;
}
public String getFolderId() {
	return folderId;
}
public void setFolderId(String folderId) {
	this.folderId = folderId;
}

@PrePersist
void updatedAt() {
  this.ticketdateTime = new Date();
}

}
