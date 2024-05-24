package com.bookMyShow.Book.My.Show.repositories;

import com.bookMyShow.Book.My.Show.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Integer> {
}
