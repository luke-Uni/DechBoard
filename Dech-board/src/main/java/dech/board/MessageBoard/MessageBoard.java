package dech.board.MessageBoard;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonCreator;

@Document(collection = "MessageBoard")
public class MessageBoard {

    @Id
    private int messageBoardId;

    private String messageBoardName;

    private List<String> participants;

    private String admin;

    @PersistenceConstructor
    @JsonCreator
    public MessageBoard(String messageBoardName, List<String> participants, String admin) {
        this.messageBoardName = messageBoardName;
        this.participants = participants;
        this.admin = admin;
    }

    public MessageBoard() {

    }

    public int getMessageBoardId() {
        return messageBoardId;
    }

    public void setMessageBoardId(int messageBoardId) {
        this.messageBoardId = messageBoardId;
    }

    public String getMessageBoardName() {
        return messageBoardName;
    }

    public void setMessageBoardName(String messageBoardName) {
        this.messageBoardName = messageBoardName;
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
        s = "ID: " + messageBoardId + "\n" + "Participants: " + participants + "\n" + "Admin: " + "\n" + admin + "\n"
                + "MessageBoardName: " + messageBoardName;
        return s;
    }

}
