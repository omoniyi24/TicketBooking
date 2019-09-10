package com.bookbusonile.main.repository;

import com.bookbusonile.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String email, String password);

    User  findByUsername(String username);
}
