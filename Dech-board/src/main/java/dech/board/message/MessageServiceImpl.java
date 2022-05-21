package dech.board.message;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl {

	MessageRepository messageRepository = new MessageRepository();

	public Message createMessage(Message message) {

		if (message.getContent() != null && message.getUsername() != null) {
			messageRepository.messageList.add(message);
			return message;
		}
		else {

		System.out.println("This message is bad!");
		return null;
		}
	}
	
	public ArrayList<Message> getMessages (String username, String recipient){
		
		ArrayList<Message> messages = new ArrayList<>();
		for(int i =0; i< messageRepository.messageList.size(); i++) {
			if(messageRepository.messageList.get(i).getUsername().equals(username) && messageRepository.messageList.get(i).getRecipient().equals(recipient)) {
				messages.add(messageRepository.messageList.get(i));
			}
			
		}
		if(messages.size()==0) {
			System.out.println("bad messages");
			return messages;
		}
		else {
			return messages;
		}
		
	}
	
}
