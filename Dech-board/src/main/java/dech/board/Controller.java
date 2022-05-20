package dech.board;

import java.util.ArrayList;
import java.util.HashMap;

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
                    // post.setImportant(true);
                    System.out.println("Hallo2");

                    post.setUsername(authService.getUsernameByToKen(authorization));

                    postService.addPost(post);
                    System.out.println(post);
                    return ResponseEntity.status(HttpStatus.OK).body("Post successfully created" + post);
                }
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
        }
        System.out.println("Hallo3");
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

    }

    @CrossOrigin
    // Mapping to create a new post
    @RequestMapping(value = "/posts/getall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPost(@RequestHeader String authorization) {
        if (authService.getUsernameByToKen(authorization) != null) {
            if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization)) != null) {
                if (authService.getTokenByUsername(authService.getUsernameByToKen(authorization))
                        .equals((authorization))) {
                    ArrayList<Post> list = postService.getPosts();

                    return ResponseEntity.status(HttpStatus.OK).body(list);
                }
                return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

        }
        return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    @CrossOrigin
    // Mapping to create a new post
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createUser(@RequestBody User user) {

        userService.createUser(user);
        System.out.println(user + "Userrrr");

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @CrossOrigin
    // Mapping to create a new post
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody User user) {

        for (int i = 0; i < userService.getUser().size(); i++) {

            if (userService.getUser().get(i).getUsername().toLowerCase().equals(user.getUsername().toLowerCase())
                    && userService.getUser().get(i).getPassword().toLowerCase()
                            .equals(user.getPassword().toLowerCase())) {
                System.out.println(user + "Userrrr");

                System.out.println("1");

                Token token = authService.createToken(user.getUsername());
                // jsonWT.put(user.getUsername(), uniqueID);

                return ResponseEntity.status(HttpStatus.OK).body(token);

            }
            System.out.println("2");
        }
        System.out.println("3");
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

}