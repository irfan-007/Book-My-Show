package com.bookMyShow.Book.My.Show.repositories;

import com.bookMyShow.Book.My.Show.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
