package com.example.News_portal.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="topic")
public class Topic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="topic_id")
    private long topicId;
    @Column(name="topic_name")
    private String topicName;

//one to many mapping topic and hashtags

    @OneToMany(mappedBy = "topic", cascade = {
            CascadeType.ALL,
    },fetch = FetchType.EAGER)
    private List< Hashtag > hashtags;

    //many to many mapping user and  topic
    @ManyToMany(mappedBy = "topics", cascade = { CascadeType.ALL })
    private List<User> users;

    public Topic()
     {
         super();

     }

    public Topic(String topicName, List<Hashtag> hashtags, List<User> users) {
        this.topicName = topicName;
        this.hashtags = hashtags;
     this.users=users;

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }





    public Topic(String topicName) {
        this.topicName = topicName;
    }



    public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }
}
