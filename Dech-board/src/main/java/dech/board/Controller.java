package dech.board;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dech.board.post.model.Post;
import dech.board.post.service.PostServiceImpl;
import dech.board.user.model.User;
import dech.board.user.service.UserService;

@CrossOrigin
@RestController
public class Controller {

//abc
//abc
    @Autowired
    PostServiceImpl postService = new PostServiceImpl();
    @Autowired
	UserService uS;
//abcd
//absdsa
//abcd

    //Mapping to create a new post
    @RequestMapping(value = "/posts/create", method= RequestMethod.POST,  consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPost(@RequestBody Post post){
    	//post.setImportant(true);
        postService.addPost(post);
        System.out.println(post);

        return ResponseEntity.status(HttpStatus.OK).body("Post successfully created"+post);
        //aa
    }

    //Mapping to create a new post
        @RequestMapping(value = "/posts/getall", method= RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> getPost(){

            ArrayList<Post> list = postService.getPosts();


            return ResponseEntity.status(HttpStatus.OK).body(list);

        }


        //Mapping to create a User(i dont know if it is really necessary)
        @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> addUser(@RequestBody User user) {
            
            
            if (uS.findUser(user.getUsername()) == 0) {
                // user should not be already existing
                uS.addUser(user);
                System.out.println(user.getUsername() + " successfull registert");
                
    
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }
    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user is already existing");
            
        
            
        }
}