package com.airline.Repositories;

import com.airline.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom method to find a user by their unique email
    Optional<User> findByEmailId(String emailId);
}