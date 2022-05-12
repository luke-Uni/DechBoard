package dech.board.user.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dech.board.user.model.User;
import dech.board.user.repository.UserRepository;

@Service


public class UserService {
	// this is the arrayList, it stores the data of Users. Later on it is going to be replaced by a database
	@Autowired
	UserRepository addUser =new UserRepository ();

	public void addUser(User user) {
		addUser.getUser().add(user);
	}

	
	public int findUser(String username) {
		for (int i = 0; i < addUser.getUser().size(); i++) {
			if (addUser.getUser().get(i).getUsername().toLowerCase().equals(username.toLowerCase())) {
				return i + 1;
				// if the fighter is found, i is increased by one (when using the method, i must
				// be reduced by one again)
			}

		}
		return 0;
	}

	
	

}
