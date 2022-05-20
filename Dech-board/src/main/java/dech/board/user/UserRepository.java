package dech.board.user;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
  ArrayList<User> addUser = new ArrayList<>();

  public ArrayList<User> userList() {

    return addUser;
  }
}
