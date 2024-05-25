package com.bookMyShow.Book.My.Show.services;

import com.bookMyShow.Book.My.Show.models.User;
import com.bookMyShow.Book.My.Show.repositories.UserRepository;
import com.bookMyShow.Book.My.Show.requestDTOs.AddUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    UserRepository userRepository;

    public String addUser(AddUserRequest addUserRequest){
        // save user to DB
        User user=User.builder()
                .age(addUserRequest.getAge())
                .email(addUserRequest.getEmail())
                .mobile(addUserRequest.getMobile())
                .name(addUserRequest.getName())
                .build();
        user=userRepository.save(user);
        // send a welcome mail to user
        SimpleMailMessage mailMessage=new SimpleMailMessage();

        mailMessage.setFrom("bookmyshow00716@gmail.com");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Welcome To Book My Show");
        mailMessage.setText("hello "+user.getName()+"! \n welcome to Book-My-Show Application.");
        javaMailSender.send(mailMessage);

        return "user added successfully with user id="+user.getUserId();
    }
}
