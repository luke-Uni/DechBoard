package dech.board.conversation;

import java.time.LocalDateTime;

public class Conversation {

	private String user1;
	private String user2;
	private int conversationId;
	private LocalDateTime lastMessageSend;
	
	
	public Conversation(String user1, String user2) {
		
		this.user1=user1;
		this.user2=user2;
		this.lastMessageSend=LocalDateTime.now();
		
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
	public int getConversationId() {
		return conversationId;
	}
	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}
	public LocalDateTime getLastMessageSend() {
		return lastMessageSend;
	}
	public void setLastMessageSend(LocalDateTime lastMessageSend) {
		this.lastMessageSend = lastMessageSend;
	}
	
	
	
}
