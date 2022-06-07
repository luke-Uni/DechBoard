package dech.board.conversation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.client.model.ReturnDocument;

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
	// public List<Conversation> getAllConversation(String username) {

	// List<Conversation> allConvos = conversationRepository.findAll();
	// List<Conversation> finalList = new ArrayList<>();

	// for (int i = 0; i < allConvos.size(); i++) {
	// if (allConvos.get(i).getUser1().equalsIgnoreCase(username)) {
	// finalList.add(allConvos.get(i));
	// } else if (allConvos.get(i).getUser2().equalsIgnoreCase(username)) {
	// String userOne = allConvos.get(i).getUser1();
	// allConvos.get(i).setUser1(username);
	// allConvos.get(i).setUser2(userOne);
	// finalList.add(allConvos.get(i));
	// System.out.println("Alle User from " + username);

	// }
	// }

	// return finalList;

	// }

	// // Function to create a new conversation between two user
	// public void createConversation(String username, String recipient) {

	// for (int i = 0; i < conversationRepository.findAll().size(); i++) {
	// System.out.println(username + recipient);
	// if
	// (conversationRepository.findAll().get(i).getUser1().equalsIgnoreCase(username)
	// &&
	// conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(recipient)
	// ||
	// conversationRepository.findAll().get(i).getUser2().equalsIgnoreCase(username)
	// &&
	// conversationRepository.findAll().get(i).getUser1().equalsIgnoreCase(recipient))
	// {
	// System.out.println("Hallo2:- " + LocalDateTime.now());

	// //
	// conversationRepository.findAll().get(i).setLastMessageSend(LocalDateTime.now());
	// Conversation conversationDTO = conversationRepository.findAll().get(i);
	// conversationDTO.setLastMessageSend(LocalDateTime.now());
	// System.out.println("Hallo 4: " + conversationDTO);
	// // conversationRepository.delete(conversationDTO);
	// // conversationDTO.setId(getHighestConversationId()+1);
	// // conversationRepository.save(conversationDTO);
	// System.out.println(conversationRepository.findAll().get(i).getLastMessageSend());

	// return;
	// }
	// }
	// System.out.println("Hallo3:- " + LocalDateTime.now());
	// Conversation newConvo = new Conversation(username, recipient);
	// newConvo.setId(getHighestConversationId() + 1);
	// conversationRepository.save(newConvo);

	// }

	// Function to gett all conversations of one user
	public List<Conversation> getAllConversationGroup(String username) {

		List<Conversation> allConvos = conversationRepository.findAll();
		List<Conversation> finalList = new ArrayList<>();

		for (int i = 0; i < allConvos.size(); i++) {
			// System.out.println("Was geht 1");
			for (int x = 0; x < allConvos.get(i).getConversationParticipants().size(); x++) {
				// System.out.println("Was geht 2");
				String name = allConvos.get(i).getConversationParticipants().get(x);
				if (name.equalsIgnoreCase(username)) {
					// System.out.println("Was geht 3");
					finalList.add(allConvos.get(i));
					//if (!allConvos.get(i).getConversationParticipants().get(0).equalsIgnoreCase(username)) {
						// Set Username on index 0
					//	String firstName = allConvos.get(i).getConversationParticipants().get(0);
					//	allConvos.get(i).getConversationParticipants().set(0, username);
					//	allConvos.get(i).getConversationParticipants().add(firstName);
						// System.out.println("Was geht 4");
				//	}

				}
			}
		}

		return finalList;

	}

	// Function to create a new conversation between two user
	public void createConversationGroup(String username, List<String> participants) {
		int duplicateParticipantCounter = 0;
		List<Conversation> conversationList = conversationRepository.findAll();
		List<Conversation> trueConversation = new ArrayList<Conversation>();
		// Get all needed Conversations where there are as many participants, as above
		if (conversationList.size() == 0) {
			Conversation newConvo = new Conversation(participants);
			newConvo.setId(getHighestConversationId() + 1);
			newConvo.getConversationParticipants().add(username);
			conversationRepository.save(newConvo);
			return;
		}

		else {
			// System.out.println("Erstens");
			for (Conversation convo : conversationList) {
				if (convo.getConversationParticipants().size() == participants.size()+1) {

					trueConversation.add(convo);

					// return;
				}
			}
			if (trueConversation.size() > 0) {

				for (Conversation convo : trueConversation) {
					List<String> nameList = convo.getConversationParticipants();

					// Get all participants of one conversation
					for (String participantName : nameList) {

						// Check if the users from received partcipant List already are in one
						// conversation together;
						for (String inputParticipant : participants) {

							if (participantName.equalsIgnoreCase(inputParticipant)
									|| participantName.equalsIgnoreCase(username)) {
								duplicateParticipantCounter++;
								System.out.println("Erster Pfad: " + duplicateParticipantCounter);

							}

						}
						if (nameList.size() == duplicateParticipantCounter) {

							System.out.println("Duplicate Participants: "+duplicateParticipantCounter);
							Conversation conversationDTOOLD = convo;
							Conversation conversationDTO = convo;
							conversationDTO.setLastMessageSend(LocalDateTime.now());

							conversationDTO.setId(getHighestConversationId() + 1);
							conversationRepository.delete(conversationDTOOLD);
							conversationRepository.save(conversationDTO);
							// All messages of this conversation have to get a updatet conversationID

							return;

						} else {
							duplicateParticipantCounter = 0;
							System.out.println("Else Pfad: " + duplicateParticipantCounter);

						}
					}
				}
				

			}
			// else if(){

			// }
			System.out.println("letzter Pfad: " + duplicateParticipantCounter);
				Conversation newConvo = new Conversation(participants);
				newConvo.getConversationParticipants().add(username);
				newConvo.setId(getHighestConversationId() + 1);
				conversationRepository.save(newConvo);
		}

	}

	// Create ConversationID instantly after the creation of a conversation
	public int getConversationID(List<String> names, String username) {

		// List<String> names = namesList;

		 names.add(username);

		List<Conversation> allConversations = getAllConversationGroup(username);
		System.out.println("Hallo Guten Tag" + allConversations);
		int counter = 0;

		// All Conversations
		System.out.println("Counter: " + counter);
		System.out.println("Alle Conversations: " + allConversations);
		for (int i = 0; i < allConversations.size(); i++) {
			System.out.println("Counter: " + counter);
			System.out.println("Names Size: "+names.size());
			System.out.println("Conversation Size: "+allConversations.get(i).getConversationParticipants().size());
		System.out.println("Alle Conversations: " + allConversations);
			if (allConversations.get(i).getConversationParticipants().size() == names.size() ) {

				List<String> allConversationsNames = allConversations.get(i).getConversationParticipants();

				// All Usernames in the Conversation
				//for (int y = 0; y < names.size(); y++) {
					for (int x = 0; x < allConversationsNames.size(); x++) {

						if (allConversationsNames.contains((names.get(x)))) {
							counter++;

						}
					}

					// if(allConversationsNames.get(x).equalsIgnoreCase(arg0))

					// System.out.println(counter);

				
				System.out.println("Counter: " + counter);
				System.out.println("Alle Conversations: " + allConversations);
				if ((counter) == allConversationsNames.size()) {
					System.out.println(counter);
					names.remove(username);
					return allConversations.get(i).getId();

				}
				// System.out.println(counter);

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
			// if(usernames.contains(username.toLowerCase())){
			// counter++;
			// System.out.println("Counter1: "+counter);
			// }
			for (String oneName : names) {
				for (String zorro : usernames) {
					if (zorro.equalsIgnoreCase(oneName) || zorro.equalsIgnoreCase(username)) {
						System.out.println("Convo Names: " + usernames + ", Name: " + oneName);
						counter++;
						System.out.println("Counter2: " + counter);
					}
				}

			}
			// if(usernames.contains(username)){
			// counter++;
			// System.out.println("Counte3r: "+counter);
			// }

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
