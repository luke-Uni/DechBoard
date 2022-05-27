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
			System.out.println("11, If schleife");
			if (conversationRepository.findAll().get(i).getUser1().equalsIgnoreCase(username)
					|| conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(username)) {
						System.out.println("22, If schleife");
				if (conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(username)) {
					System.out.println("Usernamen vergleichen");
					
					Conversation convo = conversationRepository.findAll().get(i);
					conversationRepository.delete(convo);
					String ui = convo.getUser1();
					convo.setUser1(username);
					convo.setUser2(ui);
					//conversationRepository.findAll().get(i).setUser1(username);
					//conversationRepository.findAll().get(i).setUser2(ui);
					conversationRepository.save(convo);
					System.out.println("33, If schleife");
				}

				list.add(conversationRepository.findAll().get(i));
			}
		}
		System.out.println(list);
		return list;

	}

	// Function to create a new conversation between two user
	public void createConversation(String username, String recipient) {

		for (int i = 0; i < conversationRepository.findAll().size(); i++) {
			System.out.println("Hallo1:- " + LocalDateTime.now());
			if (conversationRepository.findAll().get(i).getUser1().equalsIgnoreCase(username)
					&& conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(recipient)
					||
					conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(username)
							&& conversationRepository.findAll().get(i).getUser1().equalsIgnoreCase(recipient)) {
				System.out.println("Hallo2:- " + LocalDateTime.now());

				//conversationRepository.findAll().get(i).setLastMessageSend(LocalDateTime.now());
				Conversation conversationDTO = conversationRepository.findAll().get(i);
				conversationDTO.setLastMessageSend(LocalDateTime.now());
				conversationRepository.delete(conversationDTO);
				conversationRepository.save(conversationDTO);
				System.out.println(conversationRepository.findAll().get(i).getLastMessageSend());

				return;
			}
		}
		System.out.println("Hallo3:- " + LocalDateTime.now());
		conversationRepository.save(new Conversation(username, recipient));

	}

}
