package com.bookMyShow.Book.My.Show.repositories;

import com.bookMyShow.Book.My.Show.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Movie findMovieByMovieName(String Moviename);
}
