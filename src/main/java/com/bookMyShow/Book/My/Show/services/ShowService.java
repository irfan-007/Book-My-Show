package com.bookMyShow.Book.My.Show.services;

import com.bookMyShow.Book.My.Show.models.*;
import com.bookMyShow.Book.My.Show.repositories.MovieRepository;
import com.bookMyShow.Book.My.Show.repositories.ShowRepository;
import com.bookMyShow.Book.My.Show.repositories.ShowSeatsRepository;
import com.bookMyShow.Book.My.Show.repositories.TheaterRepository;
import com.bookMyShow.Book.My.Show.requestDTOs.AddShowRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {
    @Autowired
    private ShowSeatsRepository showSeatsRepository;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheaterRepository theaterRepository;

    public String addShow(AddShowRequest addShowRequest){
        Movie movie=movieRepository.findMovieByMovieName(addShowRequest.getMovieName());
        Theater theater=theaterRepository.findById(addShowRequest.getTheaterId()).get();
        if(movie==null)return "movie not found";
        if(theater==null)return "theater not found";

        // save show into DB
        Show show=Show.builder()
                .showTime(addShowRequest.getShowTime())
                .showDate(addShowRequest.getShowDate())
                .movie(movie)
                .theater(theater)
                .build();
//        showRepository.save(show);

        //associate corresponding show seats
        List<ShowSeat> showSeatList=new ArrayList<>();
        List<TheaterSeat> theaterSeatList=theater.getTheaterSeats();
        for(TheaterSeat seat:theaterSeatList){
            ShowSeat showSeat=ShowSeat.builder()
                    .seatNo(seat.getSeatNo())
                    .seatType(seat.getSeatType())
                    .isBooked(Boolean.FALSE)
                    .isFoodAttached(Boolean.FALSE)
                    .show(show)
                    .build();
            showSeatList.add(showSeat);
        }
        // adding all show seats to DB
        showSeatsRepository.saveAll(showSeatList);
        show.setShowSeatList(showSeatList);
        showRepository.save(show);
        return "show is added to DB with id="+show.getShowId();
    }
}
