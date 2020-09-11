package com.example.News_portal.Controller;

import com.example.News_portal.Exception.ResourceNotFoundException;
import com.example.News_portal.Model.Hashtag;
import com.example.News_portal.Service.HastagService;
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
public class HashtagController {

    @Autowired
    private HastagService hashtagService;
    //get all hashtags
    @GetMapping("/hashtag")
    public ResponseEntity<List<Hashtag>> getAllHashtags()
    {
        List<Hashtag> hashtagList= this.hashtagService.getAllHashtags();
        return new ResponseEntity<List<Hashtag>>(hashtagList,new HttpHeaders(), HttpStatus.OK);
    }
    //get hashtag by id;
    @GetMapping("hashtag/{id}")
    public ResponseEntity<Hashtag> getHashtagById(@PathVariable(value="id")  long hashtagId) throws ResourceNotFoundException
    {
        Hashtag hashtag=hashtagService.getHashtagById(hashtagId);

        return new ResponseEntity<Hashtag>(hashtag,new HttpHeaders(),HttpStatus.OK);
    }

    // add hashtag
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/hashtag")
    public ResponseEntity<Hashtag> creatHashtag( @Validated @RequestBody Hashtag hashtag)
    {
        Hashtag newHashtag = hashtagService.createUser(hashtag);
        // return this.userRepository.save(user);
        return new ResponseEntity<Hashtag>(newHashtag,new HttpHeaders(),HttpStatus.OK);
    }
    //update hashtag
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/hashtag/{hashtagId}")
    public ResponseEntity<Hashtag> updateHashtag(@PathVariable (value="hashtagId") long hashtagId,
                                           @Validated @RequestBody Hashtag hashtagDetails) throws ResourceNotFoundException {
        Hashtag updatedHashtag=this.hashtagService.updateHashtag(hashtagId,hashtagDetails);
        return new ResponseEntity<Hashtag>(updatedHashtag, new HttpHeaders(),HttpStatus.OK);
    }
    //delete user
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/hashtag/{hashtagId}")
    public Map< String, Boolean > deleteHashtag(@Validated @PathVariable(value = "hashtagId") Long hashtagId )throws ResourceNotFoundException {
        Map<String,Boolean> response=this.hashtagService.deleteHashtag(hashtagId);
        return response;
    }
  // add topic for hashtag

}
