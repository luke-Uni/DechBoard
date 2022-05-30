package dech.board.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;

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
	private Boolean send;

	private static int counter = 1;

	@JsonCreator
	public Message(String username, String content, String recipient) {

		this.id = ++counter;
		this.username = username;
		this.content = content;
		this.recipient = recipient;
		this.setTime(LocalDateTime.now());
		this.setState(DirectionState.SEND);
		this.setSend(true);
		

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

	public Boolean getSend() {
		return send;
	}

	public void setSend(Boolean send) {
		this.send = send;
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
