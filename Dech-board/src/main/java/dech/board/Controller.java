package dech.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import dech.board.Authorization.Token;
import dech.board.confirmation.Confirmation;
import dech.board.confirmation.ConfirmationDTO;
import dech.board.confirmation.ConfirmationService;
import dech.board.confirmation.EmailSenderService;
import dech.board.post.Post;
import dech.board.post.PostServiceImpl;
import dech.board.user.State;
import dech.board.user.User;
import dech.board.user.UserService;

@CrossOrigin
@RestController
public class Controller {

    @Autowired
    PostServiceImpl postService = new PostServiceImpl();

    @Autowired
    UserService userService = new UserService();

    @Autowired
    AuthorizationService authService = new AuthorizationService();

    @Autowired
    ConfirmationService confirmationService = new ConfirmationService();

    @Autowired
    EmailSenderService senderService = new EmailSenderService();

    Token token;

    HashMap<String, String> jsonWT = new HashMap<String, String>();

    @CrossOrigin
    // Mapping to create a new post
    @RequestMapping(value = "/posts/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPost(@RequestBody Post post, @RequestHeader String authorization) {
        System.out.println(authorization + "AUTH");
        System.out.println(jsonWT);
        if (authService.getUsernameByToKen(authorization) != null) {
            if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
                if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
                        .equals((authorization))) {

                    post.setUsername(authService.getUsernameByToKen(authorization));

                    postService.addPost(post);
                    System.out.println(post);
                    return ResponseEntity.status(HttpStatus.OK).body(postService.getPosts());
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
    @RequestMapping(value = "/posts/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPost(@RequestHeader String authorization) {
        System.out.println(authService.getUsernameByToKen(authorization) + " wants to see all Messages!");
        if (authService.getUsernameByToKen(authorization) != null) {
            if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
                if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
                        .equals(authorization)) {
                    List<Post> list = postService.getPosts();

                    return ResponseEntity.status(HttpStatus.OK).body(list);
                }
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

        }
        System.out.println("Username does not Exists");
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    // Mapping to create a new user
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.validInputs(user).equals("perfect")) {

            userService.createUser(user);

            Confirmation confirmation = new Confirmation(user.getEmail());

            // Create a confirmation Token for the user, so they can confirm their account
            // after registration
            confirmationService.createConfirmation(confirmation);

            senderService.sendEmail(user.getEmail(), "Confirm your E-Mail",
                    "Hey, please confirm Your Email adress: \n https://62952c12b5e4832ce651b431--dechboard.netlify.app/#/confirmuser \n Token: "
                            + confirmationService.getTokenByEmail(user.getEmail()));

            return ResponseEntity.status(HttpStatus.OK).body(user);

        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(userService.validInputs(user));
    }

    @CrossOrigin
    // Mapping to login
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody User user) {

        System.out.println(user.getUsername() + " wants to Login!");
        if (userService.checkIfUsernameExists(user.getUsername())) {
            if (userService.passwordIsCorrect(user.getUsername(), user.getPassword())) {
                System.out.println("Fehler3");
                if (userService.getUserByUsername(user.getUsername()).getState() == State.CONFIRMED) {

                    System.out.println(user.getUsername() + " is logged in!");

                    token = authService.createToken(user.getUsername());

                    return ResponseEntity.status(HttpStatus.OK).body(token);

                }
                System.out.println("User has not been confirmed");
                return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
            }
            System.out.println("Password is incorrect");
            return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);

        }

        System.out.println("Username does not Exist!");
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

    }

    @CrossOrigin
    // Mapping to get existing Users
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers(@RequestHeader String authorization) {
        // if a user wants the List of all Users
        if (authService.getUsernameByToKen(authorization) != null) {
            if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
                if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
                        .equals(authorization)) {
                    ArrayList<User> userList = new ArrayList<>();
                    for (int i = 0; i < userService.getUser().size(); i++) {
                        if (userService.getUser().get(i).getUsername()
                                .equalsIgnoreCase(authService.getUsernameByToKen(authorization))) {

                        } else {
                            userList.add(userService.getUser().get(i));
                        }

                    }
                    return ResponseEntity.status(HttpStatus.OK).body(userList);
                }
                return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
            }
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    // Mapping to confirm a User after registration
    @RequestMapping(value = "/confirmuser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> confirmUser(@RequestBody ConfirmationDTO confDTO) {

        if (confDTO.getEmail() != null && confDTO.getToken() != null) {

            if (confirmationService.confirmationExists(confDTO.getEmail())) {
                confirmationService.ConfirmUser(confDTO.getEmail(), confDTO.getToken());
                User user = userService.getUserByEmail(confDTO.getEmail());
                user.setState(State.CONFIRMED);

                userService.replaceUser(user);

                return new ResponseEntity<String>(HttpStatus.OK);
            }
            System.out.println("Confirmation for [" + confDTO.getEmail() + " ] does not Exist!");
            return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);

        }

        System.out.println("Wrong values in request");
        return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

    }

}