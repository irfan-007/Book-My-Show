package com.bookMyShow.Book.My.Show.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theaters")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer theaterId;
    private Integer noOfScreens;
    private String name;
    private String address;

    // bidirectional mapping for child records
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<TheaterSeat> theaterSeats=new ArrayList<>();
    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
    private List<Show> shows=new ArrayList<>();
}
