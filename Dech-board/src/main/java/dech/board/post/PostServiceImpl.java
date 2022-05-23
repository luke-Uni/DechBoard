package dech.board.post;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl  {
	
	@Autowired
	PostRepository messageRepo = new PostRepository();

	// method to ad a post
	public void addPost(Post post) {

		messageRepo.messageList.add(post);

	}

	public ArrayList<Post> getPosts() {

		return messageRepo.messageList;
	}

}
