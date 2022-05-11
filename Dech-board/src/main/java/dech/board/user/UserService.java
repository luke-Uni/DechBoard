package dech.board.user;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
ArrayList<User> addUser = new ArrayList<>();
public void addUser (User user) {
	addUser.add(user);
}
public int findUser(String username) {
	for (int i = 0; i < addUser.size(); i++) {
		if (addUser.get(i).getUsername().toLowerCase().equals(username.toLowerCase())) {
			return i + 1;
			// if the fighter is found, i is increased by one (when using the method, i must
			// be reduced by one again)
		}

	}
	return 0;
}
public ArrayList<User> getAddUser() {
	return addUser;
}
public void setAddUser(ArrayList<User> addUser) {
	this.addUser = addUser;
}
	

}
