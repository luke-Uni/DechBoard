package dech.board.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService {

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

	public void addImgToPost(MultipartFile multipartFile, String title) throws IOException, InterruptedException {
		System.out.println("I am in the addImgToPost method!");
		List<Post> allPosts = postRepository.findAll();
		TimeUnit.SECONDS.sleep(1);
		for (Post post : allPosts) {
			System.out.println(post.getTitle() + " = " + title);
			if (post.getTitle().equals(title)) {
				System.out.println("Delete post with id: " + post.getPostId());
				postRepository.deleteById(post.getPostId());
				post.setImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()));
				System.out.println(post.getImage());
				System.out.println("Save post with id: " + post.getPostId() + " with a image!");
				postRepository.save(post);
			}
		}
	}

	public Binary getImagebyTitle(String title) {
		List<Post> allPosts = postRepository.findAll();

		for (Post post : allPosts) {
			System.out.println(post.getTitle() + " = " + title);
			if (post.getTitle().equals(title)) {
				return post.getImage();
			}
		}
		return null;
	}

	public List<Post> getPosts(int boardId) {

		List<Post> allPosts = postRepository.findAll();

		System.out.println(boardId);

		List<Post> postsOfBoard = new ArrayList<>();

		for (Post post : allPosts) {
			if (post.getMessageBoardId() == boardId) {
				postsOfBoard.add(post);
			}
		}

		return sortPosts(postsOfBoard);

	}

	public List<Post> sortPosts(List<Post> allPosts) {

		Collections.sort(allPosts, new Comparator<Post>() {
			public int compare(Post post1, Post post2) {
				return String.valueOf(post1.getCreationDate().getDayOfYear())
						.compareTo(String.valueOf(post2.getCreationDate().getDayOfYear()));
			}
		});

		Collections.reverse(allPosts);

		return allPosts;
	}

}
