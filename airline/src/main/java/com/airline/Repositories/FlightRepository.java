package com.airline.Repositories;

import com.airline.Entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    
    // Find flights by source, destination, and a time range
    List<Flight> findBySourceCityAndDestinationCityAndDepartureTimeBetween(
        String sourceCity, 
        String destinationCity, 
        LocalDateTime startTime, 
        LocalDateTime endTime
    );

    // Find a specific flight by its flight number (e.g., "AI-202")
    List<Flight> findByFlightNumber(String flightNumber);
}