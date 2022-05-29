package dech.board.confirmation;

public class ConfirmationDTO {
    

    private String email;
    private String token;

    public ConfirmationDTO (String email,
     String token){
         this.email=email;
         this.token=token;

    }

    public ConfirmationDTO (){
        
        

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
