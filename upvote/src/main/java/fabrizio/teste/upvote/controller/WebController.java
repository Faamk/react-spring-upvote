package fabrizio.teste.upvote.controller;

import fabrizio.teste.upvote.model.Post;
import fabrizio.teste.upvote.service.PostingService;
import fabrizio.teste.upvote.service.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(path="/")
public class WebController {

    @Autowired
    private PostingService postingService;

    @RequestMapping(path="post", method = RequestMethod.GET)
    public ResponseEntity<Post> getPost(@RequestParam Long id) throws PostNotFoundException {
        Post post = postingService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @RequestMapping (path="post", method = RequestMethod.POST)
    public ResponseEntity<Post> addPost (@RequestParam String user, @RequestParam String text){
        Post post = new Post();
        post.setUser(user);
        post.setDate(new Date());
        post.setText(text);
        postingService.savePost(post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @RequestMapping(path="posts", method = RequestMethod.GET)
    public @ResponseBody List<Post> getAllPosts() {
        return postingService.getAllPosts();
    }

    @RequestMapping(path="clean", method = RequestMethod.POST)
    public ResponseEntity<String> clean()  {
        postingService.deleteAll();
        return new ResponseEntity<>("Database Clean", HttpStatus.OK);

    }
    @RequestMapping(path="upvote", method = RequestMethod.POST)
    public ResponseEntity<Post> upvote(@RequestParam Long id) throws PostNotFoundException {
        return new ResponseEntity<>(postingService.upvote(id),HttpStatus.OK);
    }
}
