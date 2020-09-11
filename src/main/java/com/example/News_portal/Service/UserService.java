package com.example.News_portal.Service;

import com.example.News_portal.Exception.ResourceNotFoundException;
import com.example.News_portal.Model.Topic;
import com.example.News_portal.Model.User;
import com.example.News_portal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {


    @Autowired UserRepository userRepository;
    //get all users

    @Cacheable(value="users")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<User> getAllUsers()
    {



        return this.userRepository.findAll();
    }

    //get user by id
    public User getUserById(long user_id)
    {
        Optional<User> user= this.userRepository.findById(user_id);
        return user.get();
    }

    // add user
    public User createUser(User user)
    {
      return this.userRepository.save(user);
    }

    //update user
    public User  updateUser(long userId,User userDetails ) throws ResourceNotFoundException{

        User user= this.userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user not found"));
        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
       user.setActiveStatus(userDetails.getActiveStatus());

        final User updatedUser=this.userRepository.save(user);
        return updatedUser;
    }
    //delete user
    public Map<String,Boolean> deleteUser(long userId) throws ResourceNotFoundException
    {
        User user=this.userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("user not exist"));
          userRepository.delete(user);
        Map < String, Boolean > response = new HashMap< >();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    // add topic for user
    public User addTopic(long userId,List<Topic> topic) throws  ResourceNotFoundException{
        User user1=this.userRepository.findById(userId).map(user->{
            user.setTopics(topic);
            return userRepository.save(user);

        }).orElseThrow(()-> new ResourceNotFoundException("not found"));
        return user1;
    }
}
