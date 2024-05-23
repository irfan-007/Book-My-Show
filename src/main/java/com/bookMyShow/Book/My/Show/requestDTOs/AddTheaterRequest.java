package com.bookMyShow.Book.My.Show.requestDTOs;

import lombok.Data;

@Data
public class AddTheaterRequest {
    private Integer noOfScreens;
    private String name;
    private String address;
}
