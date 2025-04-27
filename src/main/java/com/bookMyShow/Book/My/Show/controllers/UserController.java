package com.bookMyShow.Book.My.Show.controllers;

import com.bookMyShow.Book.My.Show.requestDTOs.AddUserRequest;
import com.bookMyShow.Book.My.Show.requestDTOs.LoginUserRequest;
import com.bookMyShow.Book.My.Show.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("add")
    public ResponseEntity addUser(@RequestBody AddUserRequest addUserRequest){
        String res=userService.addUser(addUserRequest);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity loginUser(@RequestBody LoginUserRequest loginUserRequest){
        String res=userService.loginUser(loginUserRequest);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
