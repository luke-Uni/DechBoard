package dech.board.user.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import dech.board.user.model.User;

@Repository
public class UserRepository {
    ArrayList<User> addUser = new ArrayList<>();

    public ArrayList<User> getUser() {
		
		
		return addUser;
	}
}
