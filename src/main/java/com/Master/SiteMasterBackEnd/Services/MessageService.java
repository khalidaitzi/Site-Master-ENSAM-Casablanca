package com.Master.SiteMasterBackEnd.Services;

import java.util.List;

import com.Master.SiteMasterBackEnd.Entities.Message;

public interface MessageService {
	public Message saveMessage(Message message);
	public List<Message> getAllMessages();

}
