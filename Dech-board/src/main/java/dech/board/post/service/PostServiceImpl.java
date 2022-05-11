package dech.board.post.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dech.board.post.model.Post;
import dech.board.post.repository.PostRepository;

@Service
public class PostServiceImpl implements MessageService{

	@Autowired
	PostRepository messageRepo = new PostRepository();
	
	
	public void addPost(Post post) {
		
		messageRepo.messageList.add(post);
		
		
		
	}
	
	
	public ArrayList<Post> getPosts() {
		
		
		return messageRepo.messageList;
	}
	
	
	
}
