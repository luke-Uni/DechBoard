package dech.board.confirmation;

import java.lang.annotation.Retention;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class ConfirmationService {

    public String generateToken() {

        int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);

        String token = "";

        while (token.length() <= 5) {
            token += randomNum;
        }

        return token;
    }

}
