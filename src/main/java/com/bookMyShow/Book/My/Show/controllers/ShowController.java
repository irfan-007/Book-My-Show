package com.bookMyShow.Book.My.Show.controllers;

import com.bookMyShow.Book.My.Show.requestDTOs.AddShowRequest;
import com.bookMyShow.Book.My.Show.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("show")
public class ShowController {
    @Autowired
    private ShowService showService;
    @PostMapping("add")
    public ResponseEntity addShow(@RequestBody AddShowRequest addShowRequest){
        String res=showService.addShow(addShowRequest);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
