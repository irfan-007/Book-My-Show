package com.bookMyShow.Book.My.Show.controllers;

import com.bookMyShow.Book.My.Show.requestDTOs.AddTheaterRequest;
import com.bookMyShow.Book.My.Show.requestDTOs.AddTheaterSeatsRequest;
import com.bookMyShow.Book.My.Show.services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("assosiate-seats")
    public ResponseEntity addTheaterSeats(@RequestBody AddTheaterSeatsRequest theaterSeatsRequest){
        String res=theaterService.addTheaterSeats(theaterSeatsRequest);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
