package dech.board.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	// this is the arrayList, it stores the data of Users. Later on it is going to
	// be replaced by a database
	@Autowired
	UserRepository userRepository;

	public void createUser(User user) {
		if (checkunimailFrankfurt(user)) {
			userRepository.save(user);
			System.out.println("User " + user.getUsername() + " created!");
		}
	}

	public String validInputs(User user) {

		List<User> userList = userRepository.findAll();

		for (User aUser : userList) {
			if (aUser.getUsername().equalsIgnoreCase(user.getUsername())) {
				return "Username already exists!";
			} else if (checkunimailFrankfurt(user)) {
				return "Email already exists!";
			}
		}

		return "perfect";
	}

	public Boolean checkIfUsernameExists(String username) {

		for (int i = 0; i < userRepository.findAll().size(); i++) {
			if (userRepository.findAll().get(i).getUsername().toLowerCase().equals(username.toLowerCase())) {
				return true;

			}

		}
		return false;
	}

	public boolean passwordIsCorrect(String username, String password) {

		List<User> userList = userRepository.findAll();

		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equalsIgnoreCase(username)) {
				if (userList.get(i).getPassword().equals(password)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkunimailFrankfurt(User user) {
		if (user.getEmail().endsWith("fra-uas.de")) {
			return true;
		}
		return false;

	}

	public List<User> getUser() {
		return userRepository.findAll();
	}


	// public User checkAllParameters(User user){
	// 	if(){

	// 	}


	// 	return user;
	// }

}
