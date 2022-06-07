package dech.board.conversation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Conversation")
public class Conversation {

	@Id
	private int id;
	private static int counter = 1000;
	//private String user1;
	//private String user2;
	// private int conversationId;
	private LocalDateTime lastMessageSend;
	//All Participants, including the creator 
	private List<String> conversationParticipants;

	// public Conversation(String user1, String user2) {
	// 	this.id = counter++;
	// 	this.user1 = user1;
	// 	this.user2 = user2;
	// 	this.lastMessageSend = LocalDateTime.now();

	// }

	public Conversation(List<String> participants) {
		this.id = counter++;
		this.conversationParticipants=participants;
		this.lastMessageSend = LocalDateTime.now();

	}

	public Conversation() {

	}

	// public String getUser1() {
	// 	return user1;
	// }

	// public void setUser1(String user1) {
	// 	this.user1 = user1;
	// }

	// public String getUser2() {
	// 	return user2;
	// }

	// public void setUser2(String user2) {
	// 	this.user2 = user2;
	// }

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


	public List<String> getConversationParticipants() {
		return conversationParticipants;
	}

	public void setConversationParticipants(List<String> newParticipants) {
		this.conversationParticipants = newParticipants;
	}



	@Override
	public String toString() {
		return "Conversation Participants[ "+conversationParticipants+ ", conversationId=" + id
				+ ", lastMessageSend=" + lastMessageSend + "]";
	}

}
