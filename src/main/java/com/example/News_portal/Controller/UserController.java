package com.example.News_portal.Controller;

import com.example.News_portal.Exception.ResourceNotFoundException;
import com.example.News_portal.Model.Topic;
import com.example.News_portal.Model.User;
import com.example.News_portal.Repository.TopicRepository;
import com.example.News_portal.Repository.UserRepository;
import com.example.News_portal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;


    //get all users
   // @Cacheable(value="users")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/secured/user")        //issue : in redis cache yu need to implement how to serialize response enitty
    public ResponseEntity<List<User>> getAllUsers ()
    {
        List<User> userList= userService.getAllUsers();
        System.out.println("get all users called");
          return new ResponseEntity<List<User>>(userList,new HttpHeaders(), HttpStatus.OK);
    }
    //get user by id;
    //@Cacheable(value="users",key="#userId")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/user/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable (value="id")  long userId) throws ResourceNotFoundException
    {
        User user=userService.getUserById(userId);
        System.out.println("get user by id called");
        return new ResponseEntity<User>(user,new HttpHeaders(),HttpStatus.OK);
    }

    // add user
    @CachePut(value="users",key="#user")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/user")
    public ResponseEntity<User> creatUser( @Validated @RequestBody User user)
    {
       User newUser = userService.createUser( user);
     // return this.userRepository.save(user);
        System.out.println("add  users called");
        return new ResponseEntity<User>(newUser,new HttpHeaders(),HttpStatus.OK);
    }
    //update user
    @CachePut(value="users",key="#userId")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable (value="userId") long userId,
                   @Validated @RequestBody User userDetails) throws ResourceNotFoundException {
        User updatedUser=this.userService.updateUser(userId,userDetails);
        return new ResponseEntity<User>(updatedUser, new HttpHeaders(),HttpStatus.OK);
    }
    //delete user
    @CacheEvict(value="users",key="#userId")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @DeleteMapping("/user/{userId}")
    public Map < String, Boolean > deleteUser(@Validated @PathVariable(value = "userId") Long userId )throws ResourceNotFoundException {
      Map<String,Boolean> response=this.userService.deleteUser(userId);
      return response;
    }

    //add topic followed by user
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping ("/user/{userId}/topic")
    public User addTopic(@PathVariable (value="userId")long userId,@Validated @RequestBody
                         List<Topic> topic) throws  ResourceNotFoundException
    {
        User user1=this.userService.addTopic(userId,topic);
        return user1;
    }

}


