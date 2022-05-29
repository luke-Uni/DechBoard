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

	// Get the highest Id
	public int getHighestUserId() {

		int max = 1;

		List<User> allUser = userRepository.findAll();
		for (int i = 0; i < allUser.size(); i++) {
			if (allUser.get(i).getId() >= 1) {
				max = allUser.get(i).getId();
			}

		}

		return max;
	}

	public void createUser(User user) {

		if (checkunimailFrankfurt(user)) {
			user.setId(getHighestUserId() + 1);
			user.setState(State.UNCONFIRMED);
			userRepository.save(user);
			System.out.println("User " + user.getUsername() + " created!");
		}
	}

	public String validInputs(User user) {

		List<User> userList = userRepository.findAll();

		for (User aUser : userList) {
			if (aUser.getUsername().equalsIgnoreCase(user.getUsername())) {
				return "Username already exists!";
			} else if (!checkunimailFrankfurt(user)) {
				return "Email has to be from University!";
			} else if (aUser.getEmail().equalsIgnoreCase(user.getEmail())) {
				return "Email is already in use!";
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
	// if(){

	// }

	// return user;
	// }
public void replaceUser(User user){

	User replacedUser = getUserByEmail(user.getEmail());

	userRepository.delete(replacedUser);
	System.out.println("Deleted User: "+replacedUser);

	userRepository.save(user);

	System.out.println("Saved User: "+user);

}



public User getUserByEmail(String email){

	List<User> allUser = userRepository.findAll();
	for(User user: allUser){
		if(user.getEmail().equalsIgnoreCase(email)){
			return user;
		}
	}
	return null;
}


}
