package com.example.News_portal.Service;

import com.example.News_portal.Exception.ResourceNotFoundException;
import com.example.News_portal.Model.Hashtag;
import com.example.News_portal.Model.Post;
import com.example.News_portal.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    //get all post
    public List<Post> getAllPost()
    {
        return this.postRepository.findAll();
    }

    //get post by id
    public Post getPostById(long postId)
    {
        Optional<Post> post= this.postRepository.findById(postId);
        return post.get();
    }

    // add post
    public Post createUser(Post post)
    {
        return this.postRepository.save(post);
    }

    //update post
    public Post  updatePost(long postId,Post postDetails ) throws ResourceNotFoundException {

        Post post= this.postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("user not found"));
        post.setPostTitle(postDetails.getPostTitle());
        post.setAuthorName(postDetails.getAuthorName());
        post.setPostId(postDetails.getPostId());
        post.setUpvoteCount(postDetails.getUpvoteCount());
        post.setDownvoteCount(postDetails.getDownvoteCount());
        post.setActiveStatus(postDetails.getActiveStatus());
        post.setDraftStatus(postDetails.getDraftStatus());
        post.setLastEditDate(postDetails.getLastEditDate());
        post.setPostCreationDate(post.getPostCreationDate());
        post.setTopicId(postDetails.getTopicId());
        final Post updatedPost=this.postRepository.save(post);
        return post;
    }
    //delete post
    public Map<String,Boolean> deletePost(long postId) throws ResourceNotFoundException
    {
        Post post=this.postRepository.findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("user not exist"));
        postRepository.delete(post);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    // add hashtags for post
    public Post addHashtags(long postId, List<Hashtag> hashtags) throws  ResourceNotFoundException
    {
        Post post1= this.postRepository.findById(postId).map(post->{
            post.setHashtagsForPost(hashtags);
            return this.postRepository.save(post);
        }).orElseThrow(()->new ResourceNotFoundException("not found"));
        return post1;
    }
}
