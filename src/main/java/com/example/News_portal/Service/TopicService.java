package com.example.News_portal.Service;

import com.example.News_portal.Exception.ResourceNotFoundException;
import com.example.News_portal.Model.Hashtag;
import com.example.News_portal.Model.Topic;
import com.example.News_portal.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class   TopicService {

    @Autowired
    private TopicRepository topicRepository;

    //get all topics
    public List<Topic> getAllTopics()
    {
        return this.topicRepository.findAll();
    }
    // get topic by id

    public Topic getTopicById(long topicId) throws ResourceNotFoundException
    {
        return this.topicRepository.findById(topicId)
                .orElseThrow(()-> new ResourceNotFoundException("not founnd"));
    }

    //add topic
    public Topic createTopic(Topic topic)
    {
        return this.topicRepository.save(topic);
    }
    //Update Topic
    public Topic updateTopic(long topicId,Topic topicDetails) throws ResourceNotFoundException
    {
        Topic existingTopic=this.topicRepository.findById(topicId)
                .orElseThrow(()-> new ResourceNotFoundException("not found"));

        existingTopic.setTopicName(topicDetails.getTopicName());
        Topic updatedTopic=this.topicRepository.save(existingTopic);
        return updatedTopic;

    }
    //delete topic
    public Map<String,Boolean> deleteTopic(long topicId) throws ResourceNotFoundException{
        Topic topic=this.topicRepository.findById(topicId)
                .orElseThrow(()->new ResourceNotFoundException("not found"));
         this.topicRepository.delete(topic);
         Map<String,Boolean> response= new HashMap<>();
         response.put("deleted",Boolean.TRUE);
         return response;
    }

    //add hashtags for topic
    public Topic addHashtags(long topicId, List<Hashtag> hashtags) throws  ResourceNotFoundException
    {
        Topic topic1=this.topicRepository.findById(topicId).map(topic->{
            topic.setHashtags(hashtags);
            return this.topicRepository.save(topic);
        }).orElseThrow(()-> new ResourceNotFoundException("not found"));
        return topic1;
    }
}
