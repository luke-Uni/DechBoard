package dech.board.message;

import java.time.LocalDateTime;
import java.util.List;

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

	//For Groupchats, later all users will be saved in here
	private List<String> recipients;
	//So it can be assigned to one certain conversation
	private int conversationID;

	private static int counter = 1;

	@JsonCreator
	//For Groupchats
	public Message(String username, List<String>recipients,String content) {

		this.id = ++counter;
		this.username = username;
		this.content = content;
		this.recipients=recipients;
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
		return "Message [username=" + username + ", content=" + content + ", recipients=" + recipients +", ConversationId: "+conversationID +"]";
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

	public int getConversationId() {
		return conversationID;
	}

	public void setConversationId(int conversationID) {
		this.conversationID = conversationID;
	}

	public List<String> getRecipientList() {
		return recipients;
	}

	
	public void setRecipientList(List<String> newRecipients) {
		this.recipients = newRecipients;
	}

}
