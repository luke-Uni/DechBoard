package dech.board.confirmation;

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

}
