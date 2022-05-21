package dech.board.message;

import java.util.ArrayList;

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

import dech.board.Authorization.AuthorizationRepository;
import dech.board.Authorization.AuthorizationService;
import dech.board.post.Post;

@CrossOrigin
@RestController
public class MessageController {
	
	@Autowired
	MessageServiceImpl messageServcice = new MessageServiceImpl();
	
	@Autowired 
	AuthorizationService authService = new AuthorizationService();
	
	
	
	 @CrossOrigin
	    // Mapping to create a new post
	    @RequestMapping(value = "/message/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> addPost(@RequestBody Message message, @RequestHeader String authorization) {
	        //System.out.println(authorization + "AUTH");
	       // System.out.println(authService.getUsernameByToKen(authorization));
	        message.setUsername(authService.getUsernameByToKen(authorization));
	     
	        if (authService.getUsernameByToKen(authorization) != null) {
	        	System.out.println(authService.getUsernameByToKen(authorization));
	            if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
	                if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
	                        .equals((authorization))) {
	                    // post.setImportant(true);
	                    
	                	System.out.println(message);
	                	messageServcice.createMessage(message);
	                	

	                    
	                    return ResponseEntity.status(HttpStatus.OK).body("Post successfully created" + message);
	                }
	                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	            }
	            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	        }
	        
	        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

	    }
	 
	 @CrossOrigin
	    // Mapping to create a new post
	    @RequestMapping(value = "/message/getall/{recipient}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> getAllMessages( @PathVariable String recipient, @RequestHeader String authorization) {
	       
		 System.out.println(recipient);
	     
	        if (authService.getUsernameByToKen(authorization) != null) {
	        	System.out.println(authService.getUsernameByToKen(authorization));
	            if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
	            	
	            	System.out.println(authService.getTokenByUsername(authService.getUsernameByToKen(authorization)));
	            	
	                if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
	                        .equals((authorization))) {
	                	
	                	System.out.println(authService.getTokenByUsername(authService.getUsernameByToKen(authorization)));
	                	
	                	ArrayList<Message> messages = messageServcice.getMessages(authService.getUsernameByToKen(authorization), recipient);
	                	
	                	System.out.println(messages);
	                    
	                    return ResponseEntity.status(HttpStatus.OK).body( messages);
	                }
	                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	            }
	            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
	        }
	        
	        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

	    }
	 
	 
	
	
}