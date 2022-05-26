package dech.board.confirmation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Confirmation")
public class Confirmation {

    @Id
    private int id;
    
    private String email;
    private String confirmationToken;
    private ConfirmationState confirmationStatus;

    public Confirmation(String email) {
        this.email = email;
        this.confirmationStatus = ConfirmationState.UNCONFIRMED;
        this.confirmationToken = "12345";

    }

    public Confirmation() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ConfirmationState getConfirmationState() {
        return confirmationStatus;
    }

    public void setConfirmationState(ConfirmationState confirmationState) {
        this.confirmationStatus = confirmationState;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

}