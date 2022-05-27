package dech.board.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl {

	@Autowired
	PostRepository postRepository;

	// Get the highest Id
	public int getHighestPostId() {

		int max = 1;

		List<Post> allUser = postRepository.findAll();
		for (int i = 0; i < allUser.size(); i++) {
			if (allUser.get(i).getPostId() >= 1) {
				max = allUser.get(i).getPostId();
			}

		}

		return max;

	}

	// method to ad a post
	public void addPost(Post post) {
		post.setPostId(getHighestPostId() + 1);
		postRepository.save(post);

	}

	public List<Post> getPosts() {

		List<Post> allPosts = postRepository.findAll();
		return allPosts;
	}

}
