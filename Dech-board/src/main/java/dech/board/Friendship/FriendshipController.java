package dech.board.Friendship;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dech.board.Authorization.AuthorizationService;
import dech.board.user.User;

@RestController
public class FriendshipController {

    @Autowired
    FriendshipService friendshipService;

    @Autowired
    AuthorizationService authService;

    @CrossOrigin
    // Mapping to confirm a User after registration
    @RequestMapping(value = "/friendship/request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmUser(@RequestHeader String authorization, @RequestBody User user) {

        friendshipService.createFriendshipRequest(authService.getUsernameByToKen(authorization), user.getUsername());

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @CrossOrigin
    // Mapping to confirm a User after registration
    @RequestMapping(value = "/friends", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendAllFriendsOfUser(@RequestHeader String authorization) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(friendshipService.getFriendsbyUser(authService.getUsernameByToKen(authorization)));
    }
}
