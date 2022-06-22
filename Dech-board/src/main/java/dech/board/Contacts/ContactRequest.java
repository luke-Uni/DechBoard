package dech.board.Contacts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ContactRequest")
public class ContactRequest {

    @Id
    private int requestId;

    private String from;
    private String to;

    public ContactRequest(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
