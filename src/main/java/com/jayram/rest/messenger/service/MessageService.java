package com.jayram.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;

import com.jayram.rest.messenger.model.Message;

public class MessageService {
	
	public List<Message> getAllMessages(){
		Message m1 = new Message(1, "Hello World", "John");
		Message m2 = new Message(1, "Hello Jersey", "John");
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		return list;
	}

}
