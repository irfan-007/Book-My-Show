package com.bookMyShow.Book.My.Show.requestDTOs;

import com.bookMyShow.Book.My.Show.models.Movie;
import com.bookMyShow.Book.My.Show.models.Theater;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AddShowRequest {
    private LocalDate showDate;
    private LocalTime showTime;
    private Integer theaterId;
    private String movieName;
}
