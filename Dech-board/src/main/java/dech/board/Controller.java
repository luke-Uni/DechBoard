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
import dech.board.post.Post;
import dech.board.post.PostServiceImpl;
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
                System.out.println(authService.getAllTokens());
                System.out.println(authorization);
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
        if (userService.checkunimailFrankfurt(user)) {

            userService.createUser(user);

            return ResponseEntity.status(HttpStatus.OK).body(user);
        }

        return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
    }

    @CrossOrigin
    // Mapping to login
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody User user) {

        System.out.println(user.getUsername() + " wants to Login!");
        if (userService.checkIfUsernameExists(user.getUsername())) {
            if (userService.passwordIsCorrect(user.getUsername(), user.getPassword())) {
                System.out.println(user.getUsername() + " is logged in!");

                token = authService.createToken(user.getUsername());

                return ResponseEntity.status(HttpStatus.OK).body(token);
            }
            System.out.println("Password is incorrect");

        }

        System.out.println("Username does not Exist!");
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @CrossOrigin
    // Mapping to get existing Users
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers() {
        // if a user wants the List of all Users
        ArrayList<User> userList = new ArrayList<>();
        for (int i = 0; i < userService.getUser().size(); i++) {
            userList.add(userService.getUser().get(i));

        }
        return ResponseEntity.status(HttpStatus.OK).body(userList);

    }

}