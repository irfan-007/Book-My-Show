package com.bookMyShow.Book.My.Show.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "tickets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ticketId;
    private String movieName;
    private String theaterName;
    private List<String> bookedSeats;
    private LocalTime showTime;
    private LocalDate showDate;
    private Integer totalAmount;

    @JoinColumn
    @ManyToOne
    private User user;
    @JoinColumn
    @ManyToOne
    private Show show;
}
