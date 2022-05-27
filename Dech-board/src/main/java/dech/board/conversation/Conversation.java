package dech.board.conversation;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Conversation")
public class Conversation {

	@Id
	private int id;
	private static int counter = 1000;
	private String user1;
	private String user2;
	// private int conversationId;
	private LocalDateTime lastMessageSend;

	public Conversation(String user1, String user2) {
		this.id = counter++;
		this.user1 = user1;
		this.user2 = user2;
		this.lastMessageSend = LocalDateTime.now();

	}

	public Conversation() {

	}

	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	}

	public String getUser2() {
		return user2;
	}

	public void setUser2(String user2) {
		this.user2 = user2;
	}

	// public int getConversationId() {
	// return conversationId;
	// }

	// public void setConversationId(int conversationId) {
	// this.conversationId = conversationId;
	// }

	public LocalDateTime getLastMessageSend() {
		return lastMessageSend;
	}

	public void setLastMessageSend(LocalDateTime lastMessageSend) {
		this.lastMessageSend = lastMessageSend;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Conversation [user1=" + user1 + ", user2=" + user2 + ", conversationId=" + id
				+ ", lastMessageSend=" + lastMessageSend + "]";
	}

}
