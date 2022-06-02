package dech.board.Friendship;

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
import dech.board.user.User;

@RestController
public class FriendshipController {

    @Autowired
    FriendshipService friendshipService;

    @Autowired
    AuthorizationService authService;

    @CrossOrigin
    // Mapping to add friend and accept friend
    @RequestMapping(value = "/friendship/request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmUser(@RequestHeader String authorization, @RequestBody User user) {
        if (authService.getUsernameByToKen(authorization) != null) {
            friendshipService.createFriendshipRequest(authService.getUsernameByToKen(authorization),
                    user.getUsername());

            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authorization Token is " + null);
    }

    @CrossOrigin
    // Mapping to get friends of user
    @RequestMapping(value = "/friends", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendAllFriendsOfUser(@RequestHeader String authorization) {
        if (authService.getUsernameByToKen(authorization) != null) {

            return ResponseEntity.status(HttpStatus.OK)
                    .body(friendshipService.getFriendsbyUser(authService.getUsernameByToKen(authorization)));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authorization Token is " + null);
    }

    @CrossOrigin

    @RequestMapping(value = "/friends/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteASpecificFriend(@RequestHeader String authorization, @PathVariable String username) {

        friendshipService.deleteFriendship(authService.getUsernameByToKen(authorization), username);

        return new ResponseEntity<String>(HttpStatus.OK);

    }
}
