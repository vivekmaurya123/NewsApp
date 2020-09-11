package com.example.News_portal.Controller;

import com.example.News_portal.Exception.ResourceNotFoundException;
import com.example.News_portal.Model.Hashtag;
import com.example.News_portal.Model.Topic;
import com.example.News_portal.Service.TopicService;
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
public class TopicController {

    @Autowired
    private TopicService topicService;

    //get all topics
    @GetMapping("/topic")
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topicList = this.topicService.getAllTopics();
        return new ResponseEntity<List<Topic>>(topicList, new HttpHeaders(), HttpStatus.OK);
    }

    //get topic by id
    @GetMapping("/topic/{topicId}")
    public ResponseEntity<Topic> getTopicById(@PathVariable(value = "topicId") long topicId) throws ResourceNotFoundException {
        Topic topic = this.topicService.getTopicById(topicId);
        return new ResponseEntity<Topic>(topic, new HttpHeaders(), HttpStatus.OK);
    }

    //add topic
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/topic")
    public ResponseEntity<Topic> createTopic(@Validated @RequestBody Topic topic) {
        Topic addedTopic = this.topicService.createTopic(topic);
        return new ResponseEntity<Topic>(addedTopic, new HttpHeaders(), HttpStatus.OK);
    }

    // update topic
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/topic/{topicId}")
    public ResponseEntity<Topic> updateTopic(@PathVariable(value = "topicId") long topicId
            , @Validated @RequestBody Topic topicDetails) throws ResourceNotFoundException {
        Topic updatedTopic = this.topicService.updateTopic(topicId, topicDetails);
        return new ResponseEntity<Topic>(updatedTopic, new HttpHeaders(), HttpStatus.OK);
    }

    //delete topic
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/topic/{topicId}")
    public Map<String,Boolean> deleteTopic(@PathVariable (value="topicId") long topicId) throws ResourceNotFoundException
    {
        return  this.topicService.deleteTopic(topicId);
    }
    //add hashtags for topic
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("topic/{topicId}/hashtag")
    public Topic addHashtags(@PathVariable(value="topicId")  long topicId,@ Validated @RequestBody
                             List<Hashtag> hashtag)  throws  ResourceNotFoundException {
        Topic topic1=this.topicService.addHashtags(topicId,hashtag);
        return topic1;
    }
}
