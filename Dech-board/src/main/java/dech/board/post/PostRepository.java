package dech.board.post;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {
    // This is going to be replaced with a database. For the first sprint this
    // should be ok.

    public ArrayList<Post> messageList = new ArrayList<Post>();

}
