package dech.board.post.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import dech.board.post.model.Post;


@Repository
public class PostRepository {

public ArrayList<Post> messageList = new ArrayList<>();
	
}
