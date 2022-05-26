package dech.board.message;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Message")
public class Message {

	@Id
	private int id;

	private String username;
	private String content;
	private String recipient;
	private LocalDateTime time;
	private DirectionState state;

	private static int counter = 1;

	public Message(String username, String content, String recipient) {

		this.id = ++counter;
		this.username = username;
		this.content = content;
		this.recipient = recipient;
		this.setTime(LocalDateTime.now());
		this.setState(DirectionState.SEND);

	}

	public Message() {

	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setId(int id) {
		this.id = id;
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

	public DirectionState getState() {
		return state;
	}

	public void setState(DirectionState state) {
		this.state = state;
	}

}
