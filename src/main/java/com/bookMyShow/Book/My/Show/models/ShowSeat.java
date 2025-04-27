package com.bookMyShow.Book.My.Show.models;

import com.bookMyShow.Book.My.Show.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "show_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer showSeatId;
    private String seatNo;
    private Boolean isAvailable;
    private Boolean isBooked;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private Boolean isFoodAttached;
    @JoinColumn
    @ManyToOne
    private Show show;
}
