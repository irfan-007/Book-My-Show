package com.bookMyShow.Book.My.Show.repositories;

import com.bookMyShow.Book.My.Show.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,String> {
}
