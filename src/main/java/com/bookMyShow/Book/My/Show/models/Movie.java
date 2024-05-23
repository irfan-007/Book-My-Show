package com.bookMyShow.Book.My.Show.models;

import com.bookMyShow.Book.My.Show.enums.Language;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer movieId;
    @Column(unique = true)
    private String movieName;
    private Double duration;
    private LocalDate releaseDate;
    @Enumerated(value = EnumType.STRING)
    private Language language;
    private Double rating;
}
