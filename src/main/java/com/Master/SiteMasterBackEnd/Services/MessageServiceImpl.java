package com.Master.SiteMasterBackEnd.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Master.SiteMasterBackEnd.Entities.Message;
import com.Master.SiteMasterBackEnd.Repository.MessageRepository;

@Service
@Transactional
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private MessageRepository messageRepo;

	@Override
	public Message saveMessage(Message message) {
		return this.messageRepo.save(message);	
	}

	@Override
	public List<Message> getAllMessages() {
		return this.messageRepo.findAll();
	}

}
