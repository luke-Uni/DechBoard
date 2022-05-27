package dech.board.confirmation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ConfirmationService {

    @Autowired
    ConfirmationRepository confirmationRepository;

    public String generateToken() {

        int randomNum = 0;

        String token = "";

        while (token.length() < 5) {
            randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
            token += randomNum;
        }

        return token;
    }

    public Confirmation createConfirmation(Confirmation confirmation) {
        confirmation.setConfirmationToken(generateToken());
        confirmation.setConfirmationState(ConfirmationState.UNCONFIRMED);

        confirmationRepository.save(confirmation);

        return confirmation;
    }

    //Confirm a User after registration
    public void ConfirmUser(String email, String token) {
        List<Confirmation> confList = confirmationRepository.findAll();

        for (Confirmation confirmation : confList) {
            if (confirmation.getEmail().equalsIgnoreCase(email)
                    && confirmation.getConfirmationToken().equalsIgnoreCase(token)
                    && confirmation.getConfirmationState() == ConfirmationState.UNCONFIRMED) {
                confirmationRepository.delete(confirmation);
                confirmation.setConfirmationState(ConfirmationState.CONFIRMED);
                confirmationRepository.save(confirmation);
                System.out.println("Email: " + email + " has been confirmed!");

            }
            System.out.println("Email: " + email + " has already been confirmed");
        }
        System.out.println("Email: " + email + " has not been registered");
    }


    public Boolean confirmationExists(String email){
        List<Confirmation> confList= confirmationRepository.findAll(); 
        for(Confirmation conf : confList){
            if(conf.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }

}
