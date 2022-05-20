package dech.board.comment;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

    ArrayList<Comment> comments = new ArrayList<>();

    public ArrayList<Comment> getComments() {

        return comments;
    }

}
