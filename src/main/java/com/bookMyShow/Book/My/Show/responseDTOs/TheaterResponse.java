package com.bookMyShow.Book.My.Show.responseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterResponse {
    private Integer noOfScreens;
    private String name;
    private String address;
}
