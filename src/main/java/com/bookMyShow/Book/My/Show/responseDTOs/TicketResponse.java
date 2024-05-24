package com.bookMyShow.Book.My.Show.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponse {
    private String movieName;
    private String theaterName;
    private List<String> bookedSeats;
    private LocalTime showTime;
    private LocalDate showDate;
    private Integer totalAmount;
}
