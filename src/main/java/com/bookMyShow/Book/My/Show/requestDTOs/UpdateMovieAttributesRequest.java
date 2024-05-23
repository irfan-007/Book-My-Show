package com.bookMyShow.Book.My.Show.requestDTOs;

import com.bookMyShow.Book.My.Show.enums.Language;
import lombok.Data;


@Data
public class UpdateMovieAttributesRequest {
    private String movieName;
    private Language language;
    private Double rating;
}
