package com.bookMyShow.Book.My.Show.controllers;

import com.bookMyShow.Book.My.Show.requestDTOs.MovieRequest;
import com.bookMyShow.Book.My.Show.requestDTOs.UpdateMovieAttributesRequest;
import com.bookMyShow.Book.My.Show.responseDTOs.MovieResponse;
import com.bookMyShow.Book.My.Show.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @PostMapping("add")
    public ResponseEntity addMovie(@RequestBody MovieRequest movieRequest){
        String response=movieService.addMovie(movieRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("update")
    public ResponseEntity updateMovieAttributes(@RequestBody UpdateMovieAttributesRequest updateRequest){
        String res=movieService.updateMovieAttributes(updateRequest);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping("get-movie-list-of-theater")
    public ResponseEntity getMovieListOfTheater(@RequestParam Integer theaterId){
        List<MovieResponse> res=movieService.getMovieListOfTheater(theaterId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
    @GetMapping("gross-revenue-from-movie")
    public ResponseEntity getGrossRevenueFromMovie(@RequestParam Integer movieId){
        Long res=movieService.getGrossRevenueFromMovie(movieId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
