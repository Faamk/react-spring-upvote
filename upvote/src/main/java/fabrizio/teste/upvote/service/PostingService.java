package fabrizio.teste.upvote.service;

import fabrizio.teste.upvote.model.Post;
import fabrizio.teste.upvote.repository.PostRepository;
import fabrizio.teste.upvote.service.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostingService {

    @Autowired
    PostRepository postRepository;

    public Post findById(long id) throws PostNotFoundException {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }
    public Post savePost(Post post){
        return postRepository.save(post);
    }
    public List<Post> getAllPosts (){
        List<Post> allPosts = new ArrayList<Post>();
        postRepository.findAll().forEach(allPosts::add);
        return allPosts;
    }
    public Post upvote(long id) throws PostNotFoundException {
        Post upvoted =  postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        upvoted.setUpvotes(upvoted.getUpvotes()+1);
        postRepository.save(upvoted);
        return upvoted;
    }

    public void deleteAll(){
        postRepository.deleteAll();
    }
}
