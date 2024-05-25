package com.bookMyShow.Book.My.Show.services;

import com.bookMyShow.Book.My.Show.models.User;
import com.bookMyShow.Book.My.Show.repositories.UserRepository;
import com.bookMyShow.Book.My.Show.requestDTOs.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(AddUserRequest addUserRequest){
        User user=User.builder()
                .age(addUserRequest.getAge())
                .email(addUserRequest.getEmail())
                .mobile(addUserRequest.getMobile())
                .name(addUserRequest.getName())
                .build();
        user=userRepository.save(user);
        return "user added successfully with user id="+user.getUserId();
    }
}
