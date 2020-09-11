package com.example.News_portal.Controller;

import com.example.News_portal.Exception.ResourceNotFoundException;
import com.example.News_portal.Model.Hashtag;
import com.example.News_portal.Model.Post;
import com.example.News_portal.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    @Autowired
    private PostService postService;
    //get all posts
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAllPost()
    {
        List<Post> postList= postService.getAllPost();
        return new ResponseEntity<List<Post>>(postList,new HttpHeaders(), HttpStatus.OK);
    }
    //get user by id;
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(value="id")  long postId) throws ResourceNotFoundException
    {
        Post post=postService.getPostById(postId);

        return new ResponseEntity<Post>(post,new HttpHeaders(),HttpStatus.OK);
    }

    // add user
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/post")
    public ResponseEntity<Post> creatUser( @Validated @RequestBody Post post)
    {
        Post newPost = postService.createUser(post);
        // return this.userRepository.save(user);
        return new ResponseEntity<Post>(post,new HttpHeaders(),HttpStatus.OK);
    }
    //update user
    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/post/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable (value="userId") long postId,
                                           @Validated @RequestBody Post postDetails) throws ResourceNotFoundException {
        Post updatedPost=this.postService.updatePost(postId,postDetails);
        return new ResponseEntity<Post>(updatedPost, new HttpHeaders(),HttpStatus.OK);
    }
    //delete user
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/post/{postId}")
    public Map< String, Boolean > deletePost(@Validated @PathVariable(value = "postId") Long postId )throws ResourceNotFoundException {
        Map<String,Boolean> response=this.postService.deletePost(postId);
        return response;
    }

    // add hashtags for the post
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping("/post/{postId}/hashtag")
    public Post addHashtags(@PathVariable (value="postId") long postId , @Validated @RequestBody
                            List<Hashtag>hashtags) throws ResourceNotFoundException
    {
        return this.postService.addHashtags(postId,hashtags);
    }
}
