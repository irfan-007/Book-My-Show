package com.bookMyShow.Book.My.Show.services;

import com.bookMyShow.Book.My.Show.models.Theater;
import com.bookMyShow.Book.My.Show.repositories.TheaterRepository;
import com.bookMyShow.Book.My.Show.requestDTOs.AddTheaterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;
    public String addTheater(AddTheaterRequest addTheaterRequest){
        Theater theater= Theater.builder()
                .name(addTheaterRequest.getName())
                .address(addTheaterRequest.getAddress())
                .noOfScreens(addTheaterRequest.getNoOfScreens())
        .build();

        theaterRepository.save(theater);
        return "theater added to DB with id="+theater.getTheaterId();
    }
}
