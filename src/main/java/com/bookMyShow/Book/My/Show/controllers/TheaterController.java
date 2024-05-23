package com.bookMyShow.Book.My.Show.controllers;

import com.bookMyShow.Book.My.Show.requestDTOs.AddTheaterRequest;
import com.bookMyShow.Book.My.Show.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    @PostMapping("add")
    public ResponseEntity addTheater(@RequestBody AddTheaterRequest addTheaterRequest){
        String res=theaterService.addTheater(addTheaterRequest);
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
