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
	private LocalDateTime lastMessageSend;
	//All Participants, including the creator 
	private List<String> conversationParticipants;

	public Conversation(List<String> participants) {
		this.id = counter++;
		this.conversationParticipants=participants;
		this.lastMessageSend = LocalDateTime.now();

	}

	public Conversation() {

	}

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
