package com.example.News_portal.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="hashtags_for_topic")
public class Hashtag implements Serializable {
      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
      @Column(name="hashtag_id")
      private long hashtagId;
      @Column(name="hashtag_name")
      private String hashtagName;

    //one to many mapping topic and hashtags
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    //many to many:post & hashtags
   @ManyToMany(mappedBy = "hashtagsForPost", cascade = { CascadeType.ALL })
    private Set<Post> posts = new HashSet<Post>();

     public Hashtag()
     {
         super();
     }




    public Hashtag(String hashtagName) {
        this.hashtagName = hashtagName;
    }

    public long getHashtagId() {
        return hashtagId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
    public void setHashtagId(long hashtagId) {
        this.hashtagId = hashtagId;
    }

    public String getHashtagName() {
        return hashtagName;
    }

    public void setHashtagName(String hashtagName) {
        this.hashtagName = hashtagName;
    }


}


