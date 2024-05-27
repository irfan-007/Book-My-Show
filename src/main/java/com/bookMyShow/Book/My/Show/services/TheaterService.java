package com.bookMyShow.Book.My.Show.services;

import com.bookMyShow.Book.My.Show.enums.SeatType;
import com.bookMyShow.Book.My.Show.models.*;
import com.bookMyShow.Book.My.Show.repositories.MovieRepository;
import com.bookMyShow.Book.My.Show.repositories.TheaterRepository;
import com.bookMyShow.Book.My.Show.repositories.TheaterSeatsRepository;
import com.bookMyShow.Book.My.Show.requestDTOs.AddTheaterRequest;
import com.bookMyShow.Book.My.Show.requestDTOs.AddTheaterSeatsRequest;
import com.bookMyShow.Book.My.Show.responseDTOs.MovieResponse;
import com.bookMyShow.Book.My.Show.responseDTOs.TheaterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    TheaterSeatsRepository theaterSeatsRepository;
    @Autowired
    MovieRepository movieRepository;
    public String addTheater(AddTheaterRequest addTheaterRequest){
        Theater theater= Theater.builder()
                .name(addTheaterRequest.getName())
                .address(addTheaterRequest.getAddress())
                .noOfScreens(addTheaterRequest.getNoOfScreens())
        .build();

        theaterRepository.save(theater);
        return "theater added to DB with id="+theater.getTheaterId();
    }
    public String addTheaterSeats(AddTheaterSeatsRequest theaterSeatsRequest){
        // get theater (Parent) Entity by id
        Theater theater=theaterRepository.findById(theaterSeatsRequest.getTheaterId()).get();
        // associate seats using loop
        List<TheaterSeat> theaterSeatList=new ArrayList<>();
        int cols=5;
        int row=0;
        // logic for classic seats
        for(int i=0;i<theaterSeatsRequest.getNoOfClassicSeats();i++){
            // construct seat number with row + alphabet
            int pos=i%cols;
            if(pos==0)
                row++;
            char ch=(char)('A'+pos);
            String seatNum=""+row+ch;

            // save Theater Seat Entity
            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatNo(seatNum)
                    .seatType(SeatType.CLASSIC)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
        }
        // logic for premium seats
        for(int i=0;i<theaterSeatsRequest.getNoOfPremiumSeats();i++){
            // construct seat number with row + alphabet
            int pos=i%cols;
            if(pos==0)
                row++;
            char ch=(char)('A'+pos);
            String seatNum=""+row+ch;

            // save Theater Seat Entity
            TheaterSeat theaterSeat=TheaterSeat.builder()
                    .seatNo(seatNum)
                    .seatType(SeatType.PREMIUM)
                    .theater(theater)
                    .build();

            theaterSeatList.add(theaterSeat);
        }

        // save the list to DB
        theaterSeatsRepository.saveAll(theaterSeatList);
        theater.setTheaterSeats(theaterSeatList); // apply bidirectional mapping
        theaterRepository.save(theater);
        return "theater seats are associated successfully";
    }

    public List<TheaterResponse> getTheaterListOfMovie(Integer movieId){
        // get movie entity
        Movie movie=movieRepository.findById(movieId).get();
        List<Show> showList=movie.getShows();
        // find unique theaters from show list
        Set<Theater> theaterSet=new HashSet<>();
        for(Show show:showList){
            if(!theaterSet.contains(show.getTheater()))
                theaterSet.add(show.getTheater());
        }
        // return theaters through theater-response DTO
        List<TheaterResponse> responseList=new ArrayList<>();
        for(Theater theater:theaterSet){
            TheaterResponse theaterResponse=TheaterResponse.builder()
                    .name(theater.getName())
                    .address(theater.getAddress())
                    .noOfScreens(theater.getNoOfScreens())
                    .build();
            responseList.add(theaterResponse);
        }
        return responseList;
    }

    public Long getTheaterTotalRevenueInDay(Integer theaterId, LocalDate date){
        // get theater Entity
        Theater theater=theaterRepository.findById(theaterId).get();
        List<Show> showList=theater.getShows();
        Long sum=0L;
        for(Show show:showList){
            if(show.getShowDate().equals(date)){
                List<Ticket> ticketList=show.getTicketList();
                for(Ticket ticket:ticketList){
                    sum+=ticket.getTotalAmount();
                }
            }
        }
        return sum;
    }
}
