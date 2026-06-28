package com.airline.Repositories;

import com.airline.Entities.Booking;
import com.airline.Entities.Booking.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    
    // Find all bookings for a specific user
    List<Booking> findByUser_UserId(Long userId);

    // Find bookings by flight and status (e.g., all Confirmed bookings for a flight)
    List<Booking> findByFlight_FlightIdAndBookingStatus(Integer flightId, BookingStatus status);
}