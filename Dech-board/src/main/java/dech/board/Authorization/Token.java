package dech.board.Authorization;

public class Token {

	private String username;
	private String token;

	public Token(String token, String username) {
		super();
		this.setUsername(username);
		this.setToken(token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
