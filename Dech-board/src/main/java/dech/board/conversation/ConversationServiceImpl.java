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
	public List<Conversation> getAllConversation(String username) {

		List<Conversation> allConvos = conversationRepository.findAll();
		List<Conversation> finalList = new ArrayList<>();

		for (int i = 0; i < allConvos.size(); i++) {
			if (allConvos.get(i).getUser1().equalsIgnoreCase(username)) {
				finalList.add(allConvos.get(i));
			} else if (allConvos.get(i).getUser2().equalsIgnoreCase(username)) {
				String userOne = allConvos.get(i).getUser1();
				allConvos.get(i).setUser1(username);
				allConvos.get(i).setUser2(userOne);
				finalList.add(allConvos.get(i));
				System.out.println("Alle User from " + username);

			}
		}

		return finalList;

	}

	// Function to gett all conversations of one user
	public List<Conversation> getAllConversationGroup(String username) {

		List<Conversation> allConvos = conversationRepository.findAll();
		List<Conversation> finalList = new ArrayList<>();

		for (int i = 0; i < allConvos.size(); i++) {
			for(int x =0; x<allConvos.get(i).getConversationParticipants().size();x++){
				String name= allConvos.get(i).getConversationParticipants().get(x);
			if (name.equalsIgnoreCase(username)) {
				finalList.add(allConvos.get(i));
				if(!allConvos.get(i).getConversationParticipants().get(0).equalsIgnoreCase(username)){
					//Set Username on index 0
				String hallo = allConvos.get(i).getConversationParticipants().get(0);
				allConvos.get(i).getConversationParticipants().set(0, username);
				allConvos.get(i).getConversationParticipants().add(hallo);
			}

			} 
		}
		}

		return finalList;

	}

	// Function to create a new conversation between two user
	public void createConversation(String username, String recipient) {

		for (int i = 0; i < conversationRepository.findAll().size(); i++) {
			System.out.println(username + recipient);
			if (conversationRepository.findAll().get(i).getUser1().equalsIgnoreCase(username)
					&& conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(recipient)
					||
					conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(username)
							&& conversationRepository.findAll().get(i).getUser1().equalsIgnoreCase(recipient)) {
				System.out.println("Hallo2:- " + LocalDateTime.now());

				// conversationRepository.findAll().get(i).setLastMessageSend(LocalDateTime.now());
				Conversation conversationDTO = conversationRepository.findAll().get(i);
				conversationDTO.setLastMessageSend(LocalDateTime.now());
				System.out.println("Hallo 4: " + conversationDTO);
				// conversationRepository.delete(conversationDTO);
				// conversationDTO.setId(getHighestConversationId()+1);
				// conversationRepository.save(conversationDTO);
				System.out.println(conversationRepository.findAll().get(i).getLastMessageSend());

				return;
			}
		}
		System.out.println("Hallo3:- " + LocalDateTime.now());
		Conversation newConvo = new Conversation(username, recipient);
		newConvo.setId(getHighestConversationId() + 1);
		conversationRepository.save(newConvo);

	}

	// Function to create a new conversation between two user
	public void createConversationGroup(String username, String recipient, List<String> participants) {
		int duplicateParticipantCounter = 0;
		List<Conversation> conversationList = conversationRepository.findAll();
		List<Conversation> trueConversation = new ArrayList<Conversation>();
		// Get all needed Conversations where there are as many participants, as above
		for (Conversation convo : conversationList) {
			if (convo.getConversationParticipants().size() == participants.size()) {
				trueConversation.add(convo);
			}
		}
		if (trueConversation.size() > 0) {
			for (Conversation convo : trueConversation) {
				List<String> nameList = convo.getConversationParticipants();

				// Get all participants of one conversation
				for (String participantName : nameList) {

					// Chcek if the users from received partcipant List already are in one
					// conversation together;
					for (String inputParticipant : participants) {
						if (participantName.equalsIgnoreCase(inputParticipant)) {
							duplicateParticipantCounter++;
						}

					}
					if (nameList.size() == duplicateParticipantCounter++) {

						Conversation conversationDTOOLD = convo;
						Conversation conversationDTO = convo;
						conversationDTO.setLastMessageSend(LocalDateTime.now());

						conversationDTO.setId(getHighestConversationId() + 1);
						conversationRepository.delete(conversationDTOOLD);
						conversationRepository.save(conversationDTO);

						return;

					} else {
						duplicateParticipantCounter = 0;
					}
				}
			}

			Conversation newConvo = new Conversation(participants);
			newConvo.setId(getHighestConversationId() + 1);
			conversationRepository.save(newConvo);
		}

	}

}
