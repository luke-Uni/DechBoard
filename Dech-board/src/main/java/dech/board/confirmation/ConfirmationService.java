package dech.board.confirmation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationService {

    @Autowired
    ConfirmationRepository confirmationRepository;

    public int getHighestConversationId() {

		int max = 1;

		List<Confirmation> allConfirmations = confirmationRepository.findAll();
		for (int i = 0; i < allConfirmations.size(); i++) {
			if (allConfirmations.get(i).getId() >= 1) {
				max = allConfirmations.get(i).getId();
			}
		}
		return max;
	}
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
        confirmation.setId(getHighestConversationId()+1);

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
                        return;
            }
            System.out.println("Email: " + email + " has already been confirmed");
            return;
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


    public String getTokenByEmail(String email){
        List<Confirmation> confList = confirmationRepository.findAll();

        for(Confirmation conf : confList){
            if(conf.getEmail().equalsIgnoreCase(email)){
                return conf.getConfirmationToken();
            }
        }
        return"No Token found";
    }

}
