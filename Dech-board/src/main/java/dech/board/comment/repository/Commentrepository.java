package dech.board.comment.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import dech.board.comment.model.Comment;

@Repository
public class CommentRepository {

    ArrayList<Comment> comments = new ArrayList<>();

    public ArrayList<Comment> getComments() {

        return comments;
    }

}
