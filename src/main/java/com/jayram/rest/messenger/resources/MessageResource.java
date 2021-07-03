package com.jayram.rest.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.jayram.rest.messenger.model.Message;
import com.jayram.rest.messenger.resources.beans.MessageFilterBean;
import com.jayram.rest.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {

	MessageService messageService = new MessageService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		if(filterBean.getYear() > 0){
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart() >= 0 && filterBean.getSize() > 0){
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		Message newMessage = messageService.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder()
						.path(String.valueOf(newMessage.getId()))
						.build(); //Using Builder pattern to build URI
		return Response.created(uri) //Setting Location value in Response header & sending 201-Created status code
				.entity(newMessage)
				.build(); //Using Builder pattern to build Response
	}
	
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id, Message message){
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id){
		messageService.removeMessage(id);
	}
	
	@GET
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id){
		return messageService.getMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){ //Delegates execution to sub resource
		return new CommentResource();
	}
}
