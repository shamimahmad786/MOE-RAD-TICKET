package com.moe.ticket.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moe.ticket.model.KvsMoeTickets;
import com.moe.ticket.service.TicketImpl;




@Controller
@RestController
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TicketCtrl {

	@Value("${userBucket.path}")
	private String UPLOADED_FOLDER;
	
	@Autowired
	TicketImpl ticketImpl;
	
	
	@RequestMapping(value = "/uploadTicketDocument", method = RequestMethod.POST)
	public ResponseEntity<?> uploadDocument(@RequestParam("file") MultipartFile file,
			@RequestHeader("username") String username, @RequestParam("teacherId") String teacherId,
			@RequestParam("filename") String filename,@RequestParam("folderId") String folderId) throws Exception {
		
		Map<String, Object> mp = new HashMap<String, Object>();
		File teacherFolder = new File(UPLOADED_FOLDER + File.separator + folderId);
		if (!teacherFolder.exists()) {
			teacherFolder.mkdirs();
		}

		if (file.isEmpty()) {
		}
		try {
		
			String[] arrOfStr = file.getOriginalFilename().split("\\.");
			byte[] bytes = file.getBytes();
			Path path = Paths.get(teacherFolder + File.separator + filename + ".jpg" );
			Files.write(path, bytes);
			mp.put("status", 1);
			mp.put("folderId", folderId);
		} catch (IOException ex) {
			ex.printStackTrace();
			mp.put("status", 0);
		}

		return ResponseEntity.ok(mp);
	}
	
	
	@RequestMapping(value = "/getDocumentByFolderId", method = RequestMethod.POST)
	public ResponseEntity<?> getDocumentByFolderId(@RequestBody String data) throws Exception {
		
		ObjectMapper mapperObj = new ObjectMapper();
		Map<String,Object> tdata=new HashMap<String,Object>();
		try {
			tdata = mapperObj.readValue(data, new TypeReference<Map<String,Object>>() {
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		String[] docList = null;
		File teacherFolder = new File(UPLOADED_FOLDER + File.separator + tdata.get("folderId"));
		
		if(teacherFolder.exists())
		{
			docList=teacherFolder.list();
		}
		return ResponseEntity.ok(docList);
	
	}
	
	
	@RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadDocument(@RequestBody String data) throws Exception {
		ObjectMapper mapperObj = new ObjectMapper();
		Map<String,Object> tdata=new HashMap<String,Object>();
		try {
			tdata = mapperObj.readValue(data, new TypeReference<Map<String,Object>>() {
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		File file = new File(UPLOADED_FOLDER + File.separator + tdata.get("folderId") + File.separator + tdata.get("fileName"));
		// System.out.println("filepath--->" + file.getAbsolutePath());
		Path path = Paths.get(file.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + tdata.get("fileName"));
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		return ResponseEntity.ok().headers(header).contentLength(file.length()).contentType(MediaType.APPLICATION_PDF)
				.body(resource);
	}
	
	
	@RequestMapping(value = "/deleteTicketDocument", method = RequestMethod.POST)
	public ResponseEntity<?> deleteTicketDocument(@RequestBody String data) throws Exception {
		Map<String,Object> status=new HashMap<String,Object>();
		ObjectMapper mapperObj = new ObjectMapper();
		Map<String,Object> tdata=new HashMap<String,Object>();
		try {
			tdata = mapperObj.readValue(data, new TypeReference<Map<String,Object>>() {
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		File file = new File(UPLOADED_FOLDER + File.separator + tdata.get("folderId") + File.separator + tdata.get("fileName"));
		System.out.println(file.getPath());
		if(file.exists()) {
			file.delete();
			status.put("status", 1);
		}else {
			status.put("status", 0);
		}
	return ResponseEntity.ok(status);
	}
	
	
	@RequestMapping(value = "/initiateTicket", method = RequestMethod.POST)
	public ResponseEntity<?> initiateTicket(@RequestBody String data) throws Exception {
		Calendar cal = Calendar.getInstance();
		long todayMillis2 = cal.getTimeInMillis();
		Map<String,Object> status=new HashMap<String,Object>();
		ObjectMapper mapperObj = new ObjectMapper();
		KvsMoeTickets tdata=new KvsMoeTickets();
		try {
			tdata = mapperObj.readValue(data, new TypeReference<KvsMoeTickets>() {
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		tdata.setTicketId(String.valueOf(tdata.getTeacherId())+String.valueOf(todayMillis2));
	return ResponseEntity.ok(ticketImpl.initiateTicket(tdata));
	}
	
	
	@RequestMapping(value = "/getInitiatedTicket", method = RequestMethod.POST)
	public ResponseEntity<?> getInitiatedTicket(@RequestBody String data) throws Exception {
		Calendar cal = Calendar.getInstance();
		long todayMillis2 = cal.getTimeInMillis();
		Map<String,Object> status=new HashMap<String,Object>();
		ObjectMapper mapperObj = new ObjectMapper();
		KvsMoeTickets tdata=new KvsMoeTickets();
		try {
			tdata = mapperObj.readValue(data, new TypeReference<KvsMoeTickets>() {
			});
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	return ResponseEntity.ok(ticketImpl.getInitiatedTicket(tdata));
	}
	
	
	
	
	
}
