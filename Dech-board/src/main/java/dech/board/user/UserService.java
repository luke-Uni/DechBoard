package dech.board.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {
	// this is the arrayList, it stores the data of Users. Later on it is going to
	// be replaced by a database
	@Autowired
	UserRepository userRepository = new UserRepository();

	public void createUser(User user) {
		if(checkunimailFrankfurt(user)){
		userRepository.userList().add(user);
		System.out.println("User " + user.getUsername() + " created!");
	}
}

	public Boolean checkIfUsernameExists(String username) {
		for (int i = 0; i < userRepository.userList().size(); i++) {
			if (userRepository.userList().get(i).getUsername().toLowerCase().equals(username.toLowerCase())) {
				return true;

			}

		}
		return false;
	}

	public boolean passwordIsCorrect(String username, String password) {

		for (int i = 0; i < userRepository.allUser.size(); i++) {
			if (checkIfUsernameExists(userRepository.allUser.get(i).getUsername())) {
				if (userRepository.allUser.get(i).getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}



	public boolean checkunimailFrankfurt(User user){
		if(user.getEmail().endsWith("fra-uas.de")){
			return true;
		}
		return false;

	}

	public ArrayList<User> getUser() {
		return this.userRepository.userList();
	}

}
