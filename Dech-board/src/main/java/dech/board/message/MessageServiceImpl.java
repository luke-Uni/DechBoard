package dech.board.message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl {

	@Autowired
	MessageRepository messageRepository = new MessageRepository();

	// Create and Add a message to the repository
	public Message createMessage(Message message) {

		if (message.getContent() != null && message.getUsername() != null) {
			message.setTime(LocalDateTime.now());
			messageRepository.messageList.add(message);
			return message;
		} else {

			return null;
		}
	}

	// Get all messages of one conversation
	public ArrayList<Message> getMessages(String username, String recipient) {

		System.out.println("Ich geh in die ArrayList messageList");
		ArrayList<Message> messages = new ArrayList<>();
		for (int i = 0; i < messageRepository.messageList.size(); i++) {
			if (messageRepository.messageList.get(i).getUsername().equals(username)
					&& messageRepository.messageList.get(i).getRecipient().equals(recipient)) {
				System.out.println("Ich hole mir die Messages");
				messageRepository.messageList.get(i).setState(DirectionState.SEND);
				messages.add(messageRepository.messageList.get(i));
			}

		}

		for (int i = 0; i < messageRepository.messageList.size(); i++) {
			if (messageRepository.messageList.get(i).getRecipient().equals(username)
					&& messageRepository.messageList.get(i).getUsername().equals(recipient)) {
				System.out.println("Drittens");
				messageRepository.messageList.get(i).setState(DirectionState.RECEIVED);
				messages.add(messageRepository.messageList.get(i));

			}

		}
		System.out.println("sortieren");
		getMessageListDescTIME(messages);
		
		return messages;

	}

	public ArrayList<Message> getMessageListDescTIME(ArrayList<Message> messages) {
		Collections.sort(messages, new Comparator<Message>() {
			// Descending sort by horsepower
			public int compare(Message message1, Message message2) {
				return message2.getTime().compareTo(message1.getTime());
			}
		});
		for (int i = 0; i < messages.size(); i++) {
			System.out.println((i + 1) + ": -->" + messages.get(i));
		}
		return null;
	}

}
