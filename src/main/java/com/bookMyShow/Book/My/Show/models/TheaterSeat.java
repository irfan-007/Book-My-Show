package com.bookMyShow.Book.My.Show.models;

import com.bookMyShow.Book.My.Show.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    @JoinColumn
    @ManyToOne
    private Theater theater;
}
