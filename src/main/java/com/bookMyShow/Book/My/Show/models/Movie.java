package com.bookMyShow.Book.My.Show.models;

import com.bookMyShow.Book.My.Show.enums.Language;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private List<Show> shows=new ArrayList<>();
}
