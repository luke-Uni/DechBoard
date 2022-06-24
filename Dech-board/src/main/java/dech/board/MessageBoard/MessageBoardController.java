package dech.board.MessageBoard;

import org.springframework.http.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dech.board.Authorization.AuthorizationService;

@RestController
public class MessageBoardController {
    
@Autowired
MessageBoardService messageBoardService;

@Autowired
AuthorizationService authService;

@CrossOrigin
// Mapping to create a new post
@RequestMapping(value = "/messageboard/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE )
public ResponseEntity<?> addMessageBoard(@RequestBody MessageBoard messageBoard, @RequestHeader String authorization) {
    System.out.println(authorization + "AUTH");
    //System.out.println(jsonWT);
    if (authService.getUsernameByToKen(authorization) != null) {
        if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
            if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
                    .equals((authorization))) {

                messageBoard.setAdmin(authService.getUsernameByToKen(authorization));
                messageBoardService.addMessageBoard(messageBoard, authService.getUsernameByToKen(authorization));
                //postService.addPost(post);
                System.out.println(messageBoard);
                return ResponseEntity.status(HttpStatus.CREATED).body(messageBoardService.getMessageBoards(authService.getUsernameByToKen(authorization)));
            }
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }
    // System.out.println("Hallo3");
    return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

}



@CrossOrigin
// Mapping to create a new post
@RequestMapping(value = "/messageboard/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
public ResponseEntity<?> getMessageBoard( @RequestHeader String authorization) {
    System.out.println(authorization + "AUTH");
    //System.out.println(jsonWT);
    if (authService.getUsernameByToKen(authorization) != null) {
        if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
            if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
                    .equals((authorization))) {

                
               List<MessageBoard> allMessageBoards = messageBoardService.getMessageBoards(authService.getUsernameByToKen(authorization));
                return ResponseEntity.status(HttpStatus.CREATED).body(allMessageBoards);
            }
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }
    
    return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

}




}
