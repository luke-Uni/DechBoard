package dech.board.message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import dech.board.conversation.ConversationServiceImpl;

@Service
public class MessageServiceImpl {

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	ConversationServiceImpl convoService = new ConversationServiceImpl();

	public int getHighestMessageId() {

		int max = 1;

		List<Message> allMessages = messageRepository.findAll();
		for (int i = 0; i < allMessages.size(); i++) {
			if (allMessages.get(i).getId() >= 1) {
				max = allMessages.get(i).getId();
			}

		}

		return max;
	}

	// Create and Add a message to the repository
	// public Message createMessage(Message message) {

	// 	if (message.getContent() != null && message.getUsername() != null) {
	// 		message.setTime(LocalDateTime.now());
	// 		message.setId(getHighestMessageId()+1);
	// 		messageRepository.save(message);
	// 		return message;
	// 	} else {

	// 		return null;
	// 	}
	// }

	// Get all
	// messages of
	// one conversation

	public List<Message> getMessages(String username, String recipient) {

		System.out.println("Ich geh in die ArrayList messageList");
		
		List<Message> messages = new ArrayList<Message>();
		List<Message> returnMessages = messageRepository.findAll();
		//System.out.println(returnMessages.size());
		for (int i = 0; i < returnMessages.size(); i++) {
			if (returnMessages.get(i).getUsername().equalsIgnoreCase(username)
					&& returnMessages.get(i).getRecipient().equalsIgnoreCase(recipient)) {
				// Unlimited error
				System.out.println("Ich hole mir die Messages");
				returnMessages.get(i).setState(DirectionState.SEND);
				returnMessages.get(i).setSend(true);
				messages.add(returnMessages.get(i));
			}

		}

		for (int i = 0; i < returnMessages.size(); i++) {
			if (returnMessages.get(i).getRecipient().equalsIgnoreCase(username)
					&& returnMessages.get(i).getUsername().equalsIgnoreCase(recipient)) {
				System.out.println("Drittens");
				returnMessages.get(i).setState(DirectionState.RECEIVED);
				returnMessages.get(i).setSend(false);
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


		//----------------------------------------------------------------------------------------------------------------------------------------------------

		// Create and Add a message to the repository
		public Message createMessageGroup(Message message) {

			if (message.getContent() != null && message.getUsername() != null && message.getRecipientList()!=null) {
				message.setTime(LocalDateTime.now());
				message.setId(getHighestMessageId()+1);
				//message.setConversationId(convoService.getConversationIdByUsernames(message.getRecipientList(), message.getUsername()));
				//System.out.println("Hallo guten Tag");
				messageRepository.save(message);
				return message;
			} else {
	
				return null;
			}
		}
	
		// Get all
		// messages of
		// one conversation
	//Create Message Groupchats
	
		public List<Message> getMessagesGroup(int conversationId, String username) {
	
			//System.out.println("Ich geh in die ArrayList messageList");
			
			List<Message> messages = new ArrayList<Message>();

			//Conversation convo = convoService.getConversationById(conversationId);

			List<Message> returnMessages = getMessagesByConvoId(conversationId);
			
			

			for (int i = 0; i < returnMessages.size(); i++) {
				if (returnMessages.get(i).getUsername().equalsIgnoreCase(username)) {

					// Unlimited error
					System.out.println("Ich hole mir die Messages");
					returnMessages.get(i).setState(DirectionState.SEND);
					returnMessages.get(i).setSend(true);
					messages.add(returnMessages.get(i));
				}
				else{
					System.out.println("Drittens");
					returnMessages.get(i).setState(DirectionState.RECEIVED);
					returnMessages.get(i).setSend(false);
					messages.add(returnMessages.get(i));
				}
	
			}
	
			// for (int i = 0; i < returnMessages.size(); i++) {
			// 	if (returnMessages.get(i).getRecipient().equalsIgnoreCase(username)) {
			// 		System.out.println("Drittens");
			// 		returnMessages.get(i).setState(DirectionState.RECEIVED);
			// 		returnMessages.get(i).setSend(false);
			// 		messages.add(returnMessages.get(i));
	
			// 	}
	
			// }
			//System.out.println("sortieren");
			//getMessageListDescTIME(messages);
			System.out.println(messages);
			
			return messages;
	
		}


// Get all Messages by ConversationId
		public List<Message> getMessagesByConvoId(int convoId){

			List<Message> returnMessages = new ArrayList<>();

			for(Message message: messageRepository.findAll() ){
				if(message.getConversationId()==convoId){
					returnMessages.add(message);
				}

			}

			return returnMessages;
		}

}
