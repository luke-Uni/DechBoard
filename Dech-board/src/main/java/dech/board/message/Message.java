package dech.board.message;


import java.time.LocalDateTime;

public class Message {

	private String username;
	private String content;
	private String recipient;
	private LocalDateTime time ;

	public Message(String username, String content, String recipient) {

		this.username = username;
		this.content = content;
		this.recipient=recipient;
		this.setTime(LocalDateTime.now());

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	@Override
	public String toString() {
		return "Message [username=" + username + ", content=" + content + ", recipient=" + recipient + "]";
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
	

}
