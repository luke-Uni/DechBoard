package dech.board.conversation;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl {

	ConversationRepository conversationRepository = new ConversationRepository();

	public ArrayList<Conversation> getAllConversation(String username) {

		ArrayList<Conversation> list = new ArrayList<>();

		for (int i = 0; i < conversationRepository.conversationList.size(); i++) {
			if (conversationRepository.conversationList.get(i).getUser1().equalsIgnoreCase(username)
					|| conversationRepository.conversationList.get(i).getUser2().equalsIgnoreCase(username)) {

			
				 if (conversationRepository.conversationList.get(i).getUser2().equals(username)){
					String ui= conversationRepository.conversationList.get(i).getUser1();
				conversationRepository.conversationList.get(i).setUser1(username);
				conversationRepository.conversationList.get(i).setUser2(ui);

				
				}

			// else if(conversationRepository.conversationList.get(i).getUser1().equals(username)){
			// 		list.add(conversationRepository.conversationList.get(i));
					
			// 	}
			list.add(conversationRepository.conversationList.get(i));}
				
		}
		return list;

	}

	public void createConversation(String username, String recipient) {
		for (int i = 0; i < conversationRepository.conversationList.size(); i++) {
			if (conversationRepository.conversationList.get(i).getUser1().equalsIgnoreCase(username)
					&& conversationRepository.conversationList.get(i).getUser2().equalsIgnoreCase(recipient)
					||
					conversationRepository.conversationList.get(i).getUser2().equalsIgnoreCase(username)
							&& conversationRepository.conversationList.get(i).getUser1().equalsIgnoreCase(recipient)) {
								conversationRepository.conversationList.get(i).setLastMessageSend(LocalDateTime.now());
								return;
			}
		}

		conversationRepository.conversationList.add(new Conversation(username, recipient));

	}

}
