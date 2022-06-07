package dech.board.conversation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dech.board.Authorization.AuthorizationService;
import dech.board.message.MessageServiceImpl;

@CrossOrigin
@RestController
public class ConversationController {

	@Autowired
	ConversationServiceImpl conversationService = new ConversationServiceImpl();

	@Autowired
	MessageServiceImpl messageServcice = new MessageServiceImpl();

	@Autowired
	AuthorizationService authService = new AuthorizationService();

	@CrossOrigin
	// Mapping to get all conversations of the current user
	@RequestMapping(value = "/conversation/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllConversations(@RequestHeader String authorization) {

		if (authService.getUsernameByToKen(authorization) != null) {
			// System.out.println(authService.getUsernameByToKen(authorization));
			if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {

				// System.out.println(authService.getTokenByUsername(authService.getUsernameByToKen(authorization)));
				
				if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
						.equals((authorization))) {

					System.out.println(authService.getUsernameByToKen(authorization));
					// List<Conversation> conversations = conversationService
					// 		.getAllConversation(authService.getUsernameByToKen(authorization));
					List<Conversation> conversations = conversationService.getAllConversationGroup(authService.getUsernameByToKen(authorization));

					System.out.println(conversations);

					return ResponseEntity.status(HttpStatus.OK).body(conversations);
				}
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

	}

}
