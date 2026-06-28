package com.airline.Repositories;

import com.airline.Entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    
    // Fetch all passengers associated with a specific PNR
    List<Passenger> findByBooking_PnrNumber(String pnrNumber);
}