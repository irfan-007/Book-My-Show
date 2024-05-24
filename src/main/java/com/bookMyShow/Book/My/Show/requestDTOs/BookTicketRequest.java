package com.bookMyShow.Book.My.Show.requestDTOs;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class BookTicketRequest {
    private List<String> bookingSeats;
    private List<Boolean> isFoodAttached;
    private Integer showId;
    private Integer userId;
}
