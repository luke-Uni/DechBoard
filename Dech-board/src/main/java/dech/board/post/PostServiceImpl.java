package dech.board.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl {

	@Autowired
	PostRepository postRepository;

	// method to ad a post
	public void addPost(Post post) {

		postRepository.save(post);

	}

	public List<Post> getPosts() {

		return postRepository.findAll();
	}

}
