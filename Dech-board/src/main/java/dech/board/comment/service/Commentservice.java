package dech.board.comment.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dech.board.comment.model.Comment;
import dech.board.comment.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired
	CommentRepository cmt= new CommentRepository();
	
	//method to ad a post
	public void addComment(Comment comment) {
		
		cmt.getComments().add(comment);
		
		
		
	}
	public ArrayList<Comment> getComments() {
		
		
		return cmt.getComments();
	}
}
