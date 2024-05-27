package com.bookMyShow.Book.My.Show.services;

import com.bookMyShow.Book.My.Show.models.Movie;
import com.bookMyShow.Book.My.Show.models.Show;
import com.bookMyShow.Book.My.Show.models.Theater;
import com.bookMyShow.Book.My.Show.models.Ticket;
import com.bookMyShow.Book.My.Show.repositories.MovieRepository;
import com.bookMyShow.Book.My.Show.repositories.TheaterRepository;
import com.bookMyShow.Book.My.Show.requestDTOs.MovieRequest;
import com.bookMyShow.Book.My.Show.requestDTOs.UpdateMovieAttributesRequest;
import com.bookMyShow.Book.My.Show.responseDTOs.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;
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
    public List<MovieResponse> getMovieListOfTheater(Integer theaterId){
        // show is the connecting Entity between movie and theater
        Theater theater=theaterRepository.findById(theaterId).get();
        List<Show> showList=theater.getShows();
        // find unique movies from show list
        Set<Movie> movieSet=new HashSet<>();
        for(Show show:showList){
            if(!movieSet.contains(show.getMovie()))
                movieSet.add(show.getMovie());
        }
        // return movies through movie-response DTO
        List<MovieResponse> responseList=new ArrayList<>();
        for(Movie movie:movieSet){
            MovieResponse movieResponse=MovieResponse.builder()
                    .movieName(movie.getMovieName())
                    .language(movie.getLanguage())
                    .rating(movie.getRating())
                    .releaseDate(movie.getReleaseDate())
                    .build();
            responseList.add(movieResponse);
        }
        return responseList;
    }
    public Long getGrossRevenueFromMovie(Integer movieId){
        // get movie entity and shows
        Movie movie=movieRepository.findById(movieId).get();
        List<Show> showList=movie.getShows();
        Long sum=0L;
        for(Show show:showList){
            // get ticket list and calculate total
            List<Ticket> ticketList=show.getTicketList();
            for(Ticket ticket:ticketList){
                sum+=ticket.getTotalAmount();
            }
        }
        return sum;
    }
}
