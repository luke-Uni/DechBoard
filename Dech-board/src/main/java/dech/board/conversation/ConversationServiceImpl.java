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

	public int getHighestConversationId() {

		int max = 1;

		List<Conversation> allConversations = conversationRepository.findAll();
		for (int i = 0; i < allConversations.size(); i++) {
			if (allConversations.get(i).getId() >= 1) {
				max = allConversations.get(i).getId();
			}

		}

		return max;
	}

	// Function to gett all conversations of one user
	public List<Conversation> getAllConversationGroup(String username) {

		List<Conversation> allConvos = conversationRepository.findAll();
		List<Conversation> finalList = new ArrayList<>();

		for (int i = 0; i < allConvos.size(); i++) {
			for (int x = 0; x < allConvos.get(i).getConversationParticipants().size(); x++) {
				String name = allConvos.get(i).getConversationParticipants().get(x);
				if (name.equalsIgnoreCase(username)) {
					finalList.add(allConvos.get(i));

				}
			}
		}

		return finalList;

	}

	public void testCreateConversation(String username, List<String> newParticipants) {
		int duplicateParticipants = 0;
		List<Conversation> conversationList = conversationRepository.findAll();
		List<Conversation> trueConversation = new ArrayList<Conversation>();
		List<String> convoParticipentsValue = new ArrayList<>();
		for (int i = 0; i < newParticipants.size(); i++) {
			convoParticipentsValue.add(newParticipants.get(i));
		}
		if (!newParticipants.contains(username)) {
			convoParticipentsValue.add(username);
		}

		Boolean exists = false;

		if (conversationList.size() == 0) {

			Conversation newConvo = new Conversation(convoParticipentsValue);
			newConvo.setId(getHighestConversationId() + 1);
			newConvo.setLastMessageSend(LocalDateTime.now());

			conversationRepository.save(newConvo);
			return;
		}
		for (Conversation conversation : conversationList) {
			if (conversation.getConversationParticipants().size() == convoParticipentsValue.size()) {
				trueConversation.add(conversation);
			}
		}

		if (trueConversation.size() == 0) {

			Conversation newConvo = new Conversation(convoParticipentsValue);
			newConvo.setId(getHighestConversationId() + 1);
			newConvo.setLastMessageSend(LocalDateTime.now());

			conversationRepository.save(newConvo);
			return;
		}

		else {

			for (Conversation conversation : trueConversation) {

				for (String name : convoParticipentsValue) {
					if (conversation.getConversationParticipants().contains(name)) {
						duplicateParticipants++;
					}

				}
				if (duplicateParticipants == conversation.getConversationParticipants().size()) {
					exists = true;
					Conversation conversationDTO = new Conversation(convoParticipentsValue);
					conversationDTO.setLastMessageSend(LocalDateTime.now());
					conversationDTO.setId(conversation.getId());
					conversationRepository.delete(conversation);
					conversationRepository.save(conversationDTO);
					return;
				} else {
					duplicateParticipants = 0;
				}

			}
			if (exists == false) {
				Conversation newConvo = new Conversation(convoParticipentsValue);
				newConvo.setId(getHighestConversationId() + 1);
				newConvo.setLastMessageSend(LocalDateTime.now());
				conversationRepository.save(newConvo);
				return;
			}

		}

	}

	// Create ConversationID instantly after the creation of a conversation
	public int getConversationID(List<String> names, String username) {

		
		names.add(username);

		List<Conversation> allConversations = getAllConversationGroup(username);
		
		int counter = 0;

		
		for (int i = 0; i < allConversations.size(); i++) {
			
			if (allConversations.get(i).getConversationParticipants().size() == names.size()) {

				List<String> allConversationsNames = allConversations.get(i).getConversationParticipants();

				
				for (int x = 0; x < allConversationsNames.size(); x++) {

					if (allConversationsNames.contains((names.get(x)))) {
						counter++;

					}
				}

				
				if ((counter) == allConversationsNames.size()) {
					System.out.println(counter);
					
					names.remove(username);
					return allConversations.get(i).getId();

				}
				

			}
			counter = 0;
		}
		names.remove(username);
		return 0;
	}

	// Get id of a conversation
	public int getConversationIdByUsernames(List<String> names, String username) {
		int id = 0;
		int counter = 0;
		List<Conversation> convos = getAllConversationGroup(username);

		for (Conversation convo : convos) {
			System.out.println("Counter0: " + counter);
			List<String> usernames = convo.getConversationParticipants();
			
			for (String oneName : names) {
				for (String zorro : usernames) {
					if (zorro.equalsIgnoreCase(oneName) || zorro.equalsIgnoreCase(username)) {
						System.out.println("Convo Names: " + usernames + ", Name: " + oneName);
						counter++;
						System.out.println("Counter2: " + counter);
					}
				}

			}
			

			if (counter == usernames.size()) {
				id = convo.getId();
				System.out.println("Counter4: " + counter);
				return id;

			} else {
				counter = 0;
				id = 0;
			}
		}

		return id;
	}

	public Conversation getConversationById(int id) {
		for (Conversation convo : conversationRepository.findAll()) {
			if (convo.getId() == id) {
				return convo;
			}
		}
		return null;
	}

}
