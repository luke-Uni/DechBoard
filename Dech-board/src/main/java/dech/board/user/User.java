package dech.board.user;

import com.fasterxml.jackson.annotation.JsonCreator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
	// this is the login with the User data

	@Id
	private int id;

	private String username;
	private String password;
	private String email;
	private State state;

	private String country;
	@JsonCreator
	public User(String username, String password, String email) {

		// this.id = setId();
		this.username = username;
		this.password = password;
		this.email = email;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getCountry() {
		return country;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return username;
	}

}
