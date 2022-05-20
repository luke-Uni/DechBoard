package dech.board.Authorization;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

	AuthorizationRepository authorizationRepository = new AuthorizationRepository();

	// test
	public ArrayList getAllTokens() {
		return authorizationRepository.tokenList;
	}

	public Token createToken(String username) {

		UUID uuid = UUID.randomUUID();
		Token token = new Token(uuid.toString(), username);

		authorizationRepository.getTokenList().add(token);

		return token;
	}

	public String getTokenByUsername(String username) {
		for (int i = 0; i < authorizationRepository.getTokenList().size(); i++) {
			if (username.equals(authorizationRepository.getTokenList().get(i).getUsername())) {
				return authorizationRepository.getTokenList().get(i).getToken();
			}
		}
		return null;
	}

	public String getUsernameByToKen(String token) {
		for (int i = 0; i < authorizationRepository.getTokenList().size(); i++) {
			if (token.equals(authorizationRepository.getTokenList().get(i).getToken())) {
				return authorizationRepository.getTokenList().get(i).getUsername();
			}
		}
		return null;
	}

	public void changeToken(String newToken) {

	}

}
