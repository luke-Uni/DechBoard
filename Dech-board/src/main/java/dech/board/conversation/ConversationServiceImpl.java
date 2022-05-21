package dech.board.conversation;

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

				list.add( conversationRepository.conversationList.get(i));
			}
		}
		return list;

	}	
	
	public void createConversation (String username, String recipient) {
		for (int i = 0; i < conversationRepository.conversationList.size(); i++) {
			if (conversationRepository.conversationList.get(i).getUser1().equalsIgnoreCase(username)
					&& conversationRepository.conversationList.get(i).getUser2().equalsIgnoreCase(username)
					||
					conversationRepository.conversationList.get(i).getUser2().equalsIgnoreCase(username)
					&& conversationRepository.conversationList.get(i).getUser1().equalsIgnoreCase(username)) {

				break;
			}
		}
		
		conversationRepository.conversationList.add(new Conversation(username,recipient));
		

		
	}

}
