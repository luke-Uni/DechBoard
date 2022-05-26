package dech.board.conversation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl {

	@Autowired
	ConversationRepository conversationRepository;

	// Function to gett all conversations of one user
	public List<Conversation> getAllConversation(String username) {

		List<Conversation> list = new ArrayList<Conversation>();

		for (int i = 0; i < conversationRepository.findAll().size(); i++) {
			if (conversationRepository.findAll().get(i).getUser1().equalsIgnoreCase(username)
					|| conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(username)) {

				if (conversationRepository.findAll().get(i).getUser2().equals(username)) {
					String ui = conversationRepository.findAll().get(i).getUser1();
					conversationRepository.findAll().get(i).setUser1(username);
					conversationRepository.findAll().get(i).setUser2(ui);

				}

				list.add(conversationRepository.findAll().get(i));
			}

		}
		return list;

	}

	// Function to create a new conversation between two user
	public void createConversation(String username, String recipient) {
		for (int i = 0; i < conversationRepository.findAll().size(); i++) {
			if (conversationRepository.findAll().get(i).getUser1().equalsIgnoreCase(username)
					&& conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(recipient)
					||
					conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(username)
							&& conversationRepository.findAll().get(i).getUser1().equalsIgnoreCase(recipient)) {
				conversationRepository.findAll().get(i).setLastMessageSend(LocalDateTime.now());
				return;
			}
		}

		conversationRepository.save(new Conversation(username, recipient));

	}

}
