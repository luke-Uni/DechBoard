package dech.board.Contacts;

import java.util.ArrayList;
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
import dech.board.user.User;
import dech.board.user.UserService;

@RestController
public class Contact {

    @Autowired
    ContactService friendshipService;

    @Autowired
    AuthorizationService authService;

    @Autowired
    UserService userService;

    @CrossOrigin
    // Mapping to add friend and accept friend
    @RequestMapping(value = "/contact/request", method = RequestMethod.POST)
    public ResponseEntity<?> confirmUser(@RequestHeader String authorization, @RequestBody String username) {
        // workaround because the String username always get a "=" character added on
        // the way to the server
        username = username.substring(0, username.length() - 1);

        if (authService.getUsernameByToKen(authorization) != null) {
            friendshipService.createFriendshipRequest(authService.getUsernameByToKen(authorization),
                    username);

            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authorization Token is " + null);
    }

    @CrossOrigin
    // Mapping to get friends of user
    @RequestMapping(value = "/contact", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendAllFriendsOfUser(@RequestHeader String authorization) {
        System.out.println("I am in the get Firends Controller");
        if (authService.getUsernameByToKen(authorization) != null) {

            return ResponseEntity.status(HttpStatus.OK)
                    .body(friendshipService.getFriendsbyUser(authService.getUsernameByToKen(authorization)));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authorization Token is " + null);
    }

    @CrossOrigin
    // Mapping to get friends of user
    @RequestMapping(value = "/friendsobject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> friendsObject(@RequestHeader String authorization) {
        System.out.println("I am in the get Firends Controller");
        if (authService.getUsernameByToKen(authorization) != null) {
            ArrayList<User> objectFriends = new ArrayList<>();
            List<Contacts> nameList = friendshipService
                    .getFriendsbyUser(authService.getUsernameByToKen(authorization));
            for (int i = 0; i < nameList.size(); i++) {
                System.out.println(nameList.get(i).getUsername1() + " username 2 :" + nameList.get(i).getUsername2());

                if (!nameList.get(i).getUsername1().equalsIgnoreCase(authService.getUsernameByToKen(authorization))) {
                    objectFriends.add(userService.getUserByUsername(nameList.get(i).getUsername1()));
                } else if (!nameList.get(i).getUsername2()
                        .equalsIgnoreCase(authService.getUsernameByToKen(authorization))) {
                    objectFriends.add(userService.getUserByUsername(nameList.get(i).getUsername2()));
                }
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(objectFriends);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authorization Token is " + null);
    }

    @CrossOrigin
    @RequestMapping(value = "/contact/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteASpecificFriend(@RequestHeader String authorization, @PathVariable String username) {

        friendshipService.deleteFriendship(authService.getUsernameByToKen(authorization), username);

        return new ResponseEntity<String>(HttpStatus.OK);

    }
}
