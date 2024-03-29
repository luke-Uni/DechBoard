package dech.board.message;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import dech.board.Authorization.AuthorizationService;
import dech.board.conversation.ConversationServiceImpl;

@CrossOrigin
@RestController
public class MessageController {

	@Autowired
	ConversationServiceImpl conversationService = new ConversationServiceImpl();

	@Autowired
	MessageServiceImpl messageServcice = new MessageServiceImpl();

	@Autowired
	AuthorizationService authService = new AuthorizationService();

	@CrossOrigin
	// Mapping to create a new post
	@RequestMapping(value = "/message/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addMessageGroup(@RequestBody Message message, @RequestHeader String authorization) {

		message.setUsername(authService.getUsernameByToKen(authorization));

		if (authService.getUsernameByToKen(authorization) != null) {
			System.out.println(authService.getUsernameByToKen(authorization) + " created a message");
			if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
				if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
						.equals((authorization))) {

					int index = message.getRecipientList().indexOf(authService.getUsernameByToKen(authorization));
					if (index > 0) {
						message.getRecipientList().remove(index);
						System.out.println("Hallo4:  " + message);
					}

					index = message.getRecipientList().indexOf(authService.getUsernameByToKen(authorization));
					if (index > 0) {
						message.getRecipientList().remove(index);
						System.out.println("Hallo4:  " + message);
					}

					conversationService.testCreateConversation(authService.getUsernameByToKen(authorization),
							message.getRecipientList());

					System.out.println("New Message created: " + message);

					message.setConversationId(conversationService.getConversationID(message.getRecipientList(),
							authService.getUsernameByToKen(authorization)));

					index = message.getRecipientList().indexOf(authService.getUsernameByToKen(authorization));
					if (index > 0) {
						message.getRecipientList().remove(index);

					}
					if(message.getContent().length()>0){
					messageServcice.createMessageGroup(message);

					return ResponseEntity.status(HttpStatus.OK).body("Post successfully created"
							+ message);
					}
					return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("No Message");
				}
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

	}

	@CrossOrigin
	// Mapping to create a new post
	@RequestMapping(value = "/message/{conversationId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addMessageGroupByID(@RequestBody Message message, @RequestHeader String authorization,
			@PathVariable int conversationId) {
		System.out.println(message);

		message.setUsername(authService.getUsernameByToKen(authorization));
		if (authService.getUsernameByToKen(authorization) != null) {
			System.out.println(authService.getUsernameByToKen(authorization) + " created a message");
			if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
				if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
						.equals((authorization))) {

					message.setRecipientList(
							conversationService.getConversationById(conversationId).getConversationParticipants());
					message.setConversationId(conversationId);

					int index = message.getRecipientList().indexOf(authService.getUsernameByToKen(authorization));
					if (index > 0) {
						message.getRecipientList().remove(index);
						System.out.println("Hallo4:  " + message);
					}
					if(message.getContent().length()>1){

					messageServcice.createMessageGroup(message);
					return ResponseEntity.status(HttpStatus.OK).body("Message successfully created"
							+ message);
				}

					return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("No Message");
				}																					
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

	}

	@CrossOrigin
	// Mapping to
	// create a new post
	@RequestMapping(value = "/message/getall/{conversationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<?> getAllMessagesGroup(@PathVariable int conversationId,
			@RequestHeader String authorization) {

		if (authService.getUsernameByToKen(authorization) != null) {
			if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
				System.out.println(authService.getTokenByUsername(authService.getUsernameByToKen(authorization)));
				if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
						.equals((authorization))) {
					System.out.println(authService.getTokenByUsername(authService.getUsernameByToKen(authorization)));
					List<Message> messages = messageServcice
							.getMessagesGroup(conversationId, authService.getUsernameByToKen(authorization));
					System.out.println(messages);
					return ResponseEntity.status(HttpStatus.OK).body(messages);
				}
				return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
			}
			return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	}
}
