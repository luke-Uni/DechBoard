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

@CrossOrigin
@RestController
public class Controller {

//abc
<<<<<<< HEAD

=======
//abc
>>>>>>> 10ca0716df5f701517e697210fcd9165191834d8
    @Autowired
    PostServiceImpl postService = new PostServiceImpl();
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


}