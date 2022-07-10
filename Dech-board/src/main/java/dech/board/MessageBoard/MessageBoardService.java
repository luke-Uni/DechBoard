package dech.board.MessageBoard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageBoardService {
 
    @Autowired
    MessageBoardRepository messageBoardRepository;


    public int getHighestMessageBoardId() {

		int max = 1;

		List<MessageBoard> allUser = messageBoardRepository.findAll();
		for (int i = 0; i < allUser.size(); i++) {
			if (allUser.get(i).getMessageBoardId() >= 1) {
				max = allUser.get(i).getMessageBoardId();
			}

		}

		return max;

	}

    // method to ad a post
	public void addMessageBoard(MessageBoard messageBoard, String admin) {
		messageBoard.setMessageBoardId(getHighestMessageBoardId() + 1);
		messageBoard.getParticipants().add(admin);
		messageBoardRepository.save(messageBoard);

	}

    public List<MessageBoard> getMessageBoards(String username) {
		List<MessageBoard> allBoards = new ArrayList<>();

		for(MessageBoard messageBoard : messageBoardRepository.findAll()){
			for(String participant : messageBoard.getParticipants()){
				if(username.equals(participant)){
					allBoards.add(messageBoard);
				}
			}
		}
		return allBoards;
	}
}
