package dech.board.Authorization;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

	AuthorizationRepository authorizationRepository = new AuthorizationRepository();

	public ArrayList getAllTokens() {
		return authorizationRepository.tokenList;
	}

	// Create a new token
	public Token createToken(String username) {
		UUID uuid = UUID.randomUUID();
		for (int i = 0; i < authorizationRepository.tokenList.size(); i++) {

			// If user once had a token, it will be replaced
			if (authorizationRepository.tokenList.get(i).getUsername().equalsIgnoreCase(username)) {

				authorizationRepository.tokenList.get(i).setToken(uuid.toString());
				return authorizationRepository.tokenList.get(i);
			}
		}

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

}
