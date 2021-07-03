package com.jayram.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jayram.rest.messenger.database.DatabaseClass;
import com.jayram.rest.messenger.model.Comment;
import com.jayram.rest.messenger.model.Message;

public class CommentService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messsageId){
		Map<Long, Comment> comments = messages.get(messsageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messsageId, long commentId){
		Map<Long, Comment> comments = messages.get(messsageId).getComments();
		return comments.get(commentId);
	}
	
	public Comment addComments(long messsageId, Comment comment){
		Map<Long, Comment> comments = messages.get(messsageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment updateComments(long messsageId, Comment comment){
		Map<Long, Comment> comments = messages.get(messsageId).getComments();
		if(comment.getId() <= 0){
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComments(long messsageId, long commentId){
		Map<Long, Comment> comments = messages.get(messsageId).getComments();
		return comments.remove(commentId);
	}
	
}