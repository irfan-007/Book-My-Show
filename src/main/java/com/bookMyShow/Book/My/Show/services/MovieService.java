package com.bookMyShow.Book.My.Show.services;

import com.bookMyShow.Book.My.Show.models.Movie;
import com.bookMyShow.Book.My.Show.repositories.MovieRepository;
import com.bookMyShow.Book.My.Show.requestDTOs.MovieRequest;
import com.bookMyShow.Book.My.Show.requestDTOs.UpdateMovieAttributesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    public String addMovie(MovieRequest movieRequest){
        Movie movie=new Movie();
        movie.setMovieName(movieRequest.getMovieName());
        movie.setDuration(movieRequest.getDuration());
        movie.setLanguage(movieRequest.getLanguage());
        movie.setReleaseDate(movieRequest.getReleaseDate());
        movie.setRating(movieRequest.getRating());

        movieRepository.save(movie);
        return "movie added to db with movie id="+movie.getMovieId();
    }
    public String updateMovieAttributes(UpdateMovieAttributesRequest updateRequest){
        // get movie entity
        Movie movie=movieRepository.findMovieByMovieName(updateRequest.getMovieName());
        //update attributes
        movie.setLanguage(updateRequest.getLanguage());
        movie.setRating(updateRequest.getRating());
        // save back it to the DB
        movieRepository.save(movie);
        return "movie's attributes updated";
    }
}
