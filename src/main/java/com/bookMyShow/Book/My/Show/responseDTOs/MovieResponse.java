package com.bookMyShow.Book.My.Show.responseDTOs;

import com.bookMyShow.Book.My.Show.enums.Language;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private String movieName;
    private LocalDate releaseDate;
    @Enumerated(value = EnumType.STRING)
    private Language language;
    private Double rating;
}
