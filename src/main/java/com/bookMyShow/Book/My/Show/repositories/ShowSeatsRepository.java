package com.bookMyShow.Book.My.Show.repositories;

import com.bookMyShow.Book.My.Show.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatsRepository extends JpaRepository<ShowSeat,Integer> {
}
