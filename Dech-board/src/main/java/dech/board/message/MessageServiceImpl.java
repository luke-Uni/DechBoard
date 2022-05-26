package dech.board.message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl {

	@Autowired
	MessageRepository messageRepository;

	// Create and Add a message to the repository
	public Message createMessage(Message message) {

		if (message.getContent() != null && message.getUsername() != null) {
			message.setTime(LocalDateTime.now());
			messageRepository.save(message);
			return message;
		} else {

			return null;
		}
	}

	// Get all
	// messages of
	// one conversation

	public List<Message> getMessages(String username, String recipient) {

		System.out.println("Ich geh in die ArrayList messageList");
		
		List<Message> messages = new ArrayList<Message>();
		List<Message> returnMessages = messageRepository.findAll();
		//System.out.println(returnMessages.size());
		for (int i = 0; i < returnMessages.size(); i++) {
			if (returnMessages.get(i).getUsername().equals(username)
					&& returnMessages.get(i).getRecipient().equals(recipient)) {
				// Unlimited error
				System.out.println("Ich hole mir die Messages");
				returnMessages.get(i).setState(DirectionState.SEND);
				messages.add(returnMessages.get(i));
			}

		}

		for (int i = 0; i < returnMessages.size(); i++) {
			if (returnMessages.get(i).getRecipient().equals(username)
					&& returnMessages.get(i).getUsername().equals(recipient)) {
				System.out.println("Drittens");
				returnMessages.get(i).setState(DirectionState.RECEIVED);
				messages.add(returnMessages.get(i));

			}

		}
		System.out.println("sortieren");
		getMessageListDescTIME(messages);
		System.out.println(messages);
		
		return messages;

	}

	public List<Message> getMessageListDescTIME(List<Message> messages) {
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
