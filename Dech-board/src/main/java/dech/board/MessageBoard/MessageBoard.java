package dech.board.MessageBoard;

import java.util.List;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;

@Document(collection = "MessageBoard")
public class MessageBoard {
    

private String MessageBoardName;





private int MessageBoardId;

private List<String> participants;

private String admin;


@PersistenceConstructor
@JsonCreator
public MessageBoard(String messageBoardName, List<String> participants, String admin) {
    MessageBoardName = messageBoardName;
    this.participants = participants;
    this.admin = admin;
}

public MessageBoard(){

}




public int getMessageBoardId() {
    return MessageBoardId;
}

public void setMessageBoardId(int messageBoardId) {
    MessageBoardId = messageBoardId;
}

public String getMessageBoardName() {
    return MessageBoardName;
}

public void setMessageBoardName(String messageBoardName) {
    MessageBoardName = messageBoardName;
}





public List<String> getParticipants() {
    return participants;
}

public void setParticipants(List<String> participants) {
    this.participants = participants;
}



public String getAdmin() {
    return admin;
}

public void setAdmin(String admin) {
    this.admin = admin;
}


@Override
	public String toString() {
		String s;
		s = "ID: " + MessageBoardId + "\n" + "Participants: " + participants + "\n" + "Admin: " + "\n" + admin + "\n"
				+ "MessageBoardName: " + MessageBoardName;
		return s;
	}


}
