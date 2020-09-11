package com.example.News_portal.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="users")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","roles"})
public class User  implements Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name="user_id")
     private long userId;
     @Column(name="user_name")
     private String userName;
     @Column(name="email")
    private String email;
     @Column(name="password")
    private String password;
    //location variable
    @Column(name="user_active_status")
    private Boolean activeStatus;

    @OneToMany(mappedBy = "user", cascade =
            CascadeType.ALL ,fetch = FetchType.EAGER
    )
    private List<Role> roles;

    //many to many mapping user and  topic
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "topics_followed_by_users",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "topic_id")
            }
    )
    private List<Topic> topics;

    public User() {

    }



    public User(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.activeStatus = user.getActiveStatus();
        this.roles = user.getRoles();
        this.topics = user.getTopics();
    }

    public User(String userName, String email, String password, Boolean activeStatus) {

        this.userName = userName;
        this.email = email;
         this.password=password;
        this.activeStatus = activeStatus;
    }

    public User(String userName, String email, Boolean activeStatus, List<Topic> topics) {
        this.userName = userName;
        this.email = email;

        this.activeStatus = activeStatus;
        this.topics = topics;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public long getUserId() {
        return userId;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
