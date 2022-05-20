package dech.board.Authorization;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

@Repository
public class AuthorizationRepository {

    ArrayList<Token> tokenList = new ArrayList<>();

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

}
