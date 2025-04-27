package com.bookMyShow.Book.My.Show.services;

import com.bookMyShow.Book.My.Show.models.User;
import com.bookMyShow.Book.My.Show.repositories.UserRepository;
import com.bookMyShow.Book.My.Show.requestDTOs.AddUserRequest;
import com.bookMyShow.Book.My.Show.requestDTOs.LoginUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    UserRepository userRepository;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserService(AuthenticationManager authenticationManager, JwtService jwtService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String addUser(AddUserRequest addUserRequest){
        System.out.println(addUserRequest);
        // encrypt password
        String pass=addUserRequest.getPassword();
        pass=bCryptPasswordEncoder.encode(pass);
        // save user to DB
        User user=User.builder()
                .age(addUserRequest.getAge())
                .email(addUserRequest.getEmail())
                .mobile(addUserRequest.getMobile())
                .userName(addUserRequest.getUserName())
                .password(pass)
                .build();
        user=userRepository.save(user);
        // send a welcome mail to user
//        SimpleMailMessage mailMessage=new SimpleMailMessage();
//
//        mailMessage.setFrom("bookmyshow00716@gmail.com");
//        mailMessage.setTo(user.getEmail());
//        mailMessage.setSubject("Welcome To Book My Show");
//        mailMessage.setText("hello "+user.getUserName()+"! \n welcome to Book-My-Show Application.");
//        javaMailSender.send(mailMessage);

        return "user added successfully with user id="+user.getUserId();
    }

    public String loginUser(LoginUserRequest loginUserRequest) {

        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginUserRequest.getUserName(), loginUserRequest.getPassword()));
        if(auth.isAuthenticated()){
            return jwtService.generatedToken(loginUserRequest.getUserName());
        }

        return "no user found";
    }


}
