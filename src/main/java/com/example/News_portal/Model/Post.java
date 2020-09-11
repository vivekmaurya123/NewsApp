package com.example.News_portal.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="post")
public class Post implements Serializable {

    enum draftStatus{
      draft,
      complete
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id")
    private long postId;
    @Column(name="post_title")
    private String postTitle;
    @Column(name="author_name")
    private String authorName;
    @Column(name="topic_id")
    private long topicId;
    // *** huge mistake taken space inside column name
    @Column(name="created_at")
    private Date postCreationDate=new Date();
    @Column(name="edited_at")
    private Date lastEditDate=new Date();
    @Column(name="post_active_status")
    private Boolean activeStatus;
    @Column(name="upvote_count")
    private long upvoteCount;
    @Column(name="downvote_count")
    private long DownvoteCount;
    @Column (name="draft_status")
    private Enum<draftStatus> draftStatus;


    //many to many:post & hashtags
   @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "post_hashtags",
            joinColumns = {
                    @JoinColumn(name = "post_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "hashtag_id")
            }
    )
    private List<Hashtag> hashtagsForPost;


    public Post()
    {
      super();
    }

    public Post( String postTitle, String authorName,long topicId, Date postCreationDate,
                Date lastEditDate, Boolean activeStatus, long upvoteCount,
                long downvoteCount, Enum<draftStatus> draftStatus) {
        this.postTitle = postTitle;
        this.authorName = authorName;
        this.topicId=topicId;
        this.postCreationDate = postCreationDate;
        this.lastEditDate = lastEditDate;
        this.activeStatus = activeStatus;
        this.upvoteCount = upvoteCount;
        this.DownvoteCount = downvoteCount;
        this.draftStatus = draftStatus;
    }

    public Post(String postTitle, String authorName, long topicId, Date postCreationDate,
                Date lastEditDate, Boolean activeStatus, long upvoteCount,
                long downvoteCount, Enum<draftStatus> draftststus, List<Hashtag> hashtags) {
        this.postTitle = postTitle;
        this.authorName = authorName;
        this.topicId = topicId;
        this.postCreationDate = postCreationDate;
        this.lastEditDate = lastEditDate;
        this.activeStatus = activeStatus;
        this.upvoteCount = upvoteCount;
        DownvoteCount = downvoteCount;
        this.draftStatus = draftststus;
        this.hashtagsForPost = hashtags;
    }



    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

   public long getTopicId() {
        return topicId;
    }

    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    public Date getPostCreationDate() {
        return postCreationDate;
    }

    public void setPostCreationDate(Date postCreationDate) {
        this.postCreationDate = postCreationDate;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public long getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(long upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public long getDownvoteCount() {
        return DownvoteCount;
    }

    public void setDownvoteCount(long downvoteCount) {
        DownvoteCount = downvoteCount;
    }

    public Enum<Post.draftStatus> getDraftStatus() {
        return draftStatus;
    }

    public void setDraftStatus(Enum<Post.draftStatus> draftStatus) {
        this.draftStatus = draftStatus;
    }

    public List<Hashtag> getHashtagsForPost() {
        return hashtagsForPost;
    }

    public void setHashtagsForPost(List<Hashtag> hashtagsForPost) {
        this.hashtagsForPost = hashtagsForPost;
    }
}
