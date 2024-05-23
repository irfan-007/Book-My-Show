package com.bookMyShow.Book.My.Show.requestDTOs;

import com.bookMyShow.Book.My.Show.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private String movieName;
    private Double duration;
    private LocalDate releaseDate;
    private Language language;
    private Double rating;
}
