package com.bookMyShow.Book.My.Show.requestDTOs;

import lombok.Data;

@Data
public class AddTheaterSeatsRequest {
    private Integer theaterId;
    private Integer noOfClassicSeats;
    private Integer noOfPremiumSeats;
}
