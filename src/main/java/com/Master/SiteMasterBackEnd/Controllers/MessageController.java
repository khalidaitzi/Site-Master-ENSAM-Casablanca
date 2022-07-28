package com.Master.SiteMasterBackEnd.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Master.SiteMasterBackEnd.Entities.Message;
import com.Master.SiteMasterBackEnd.Services.MessageService;

import lombok.Data;

@RestController
public class MessageController {
	@Autowired
	MessageService messageService;


	@GetMapping("/messages")
	public List<Message> getAll()
	{
		return messageService.getAllMessages();
	}
	@PostMapping("/messages")
	public Message sendMessage(@RequestBody MessageForm messageForm)
	{
		
		//System.err.println(userForm);
		return messageService.saveMessage(new Message(null,messageForm.getNom(),messageForm.getEmail(),messageForm.getMessage()));
	}

	}

	@Data
	class MessageForm{
		private String nom;
		private String email;
		private String message;
		public String getNom() {
			return nom;
		}
		public void setNom(String nom) {
			this.nom = nom;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
	}