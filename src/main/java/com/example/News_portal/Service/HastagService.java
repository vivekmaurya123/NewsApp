package com.example.News_portal.Service;

import com.example.News_portal.Exception.ResourceNotFoundException;
import com.example.News_portal.Model.Hashtag;
import com.example.News_portal.Repository.HashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HastagService {

    @Autowired
    private HashtagRepository hashtagRepository;

    //get all hashtags

    public List<Hashtag> getAllHashtags()
    {
        return this.hashtagRepository.findAll();
    }

    //get hashtag by id
    public  Hashtag getHashtagById(long hashtagId)
    {
        Optional<Hashtag> hashtag= this.hashtagRepository.findById(hashtagId);
        return hashtag.get();
    }

    // add hashtah
    public Hashtag createUser(Hashtag hashtag)
    {

        return this.hashtagRepository.save(hashtag);
    }

    //update hashtag
    public Hashtag  updateHashtag(long hashtagId,Hashtag hashtagDetails) throws ResourceNotFoundException {

        Hashtag hashtag= this.hashtagRepository.findById(hashtagId)
                .orElseThrow(()->new ResourceNotFoundException("user not found"));
         hashtag.setHashtagName(hashtagDetails.getHashtagName());
        final Hashtag updatedHashtag=this.hashtagRepository.save(hashtag);
        return updatedHashtag;
    }
    //delete hashtag
    public Map<String,Boolean> deleteHashtag(long hashtagId) throws ResourceNotFoundException
    {
        Hashtag hahstag=this.hashtagRepository.findById(hashtagId)
                .orElseThrow(()-> new ResourceNotFoundException("user not exist"));
        hashtagRepository.delete(hahstag);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
