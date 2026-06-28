package com.airline.Controllers;

import com.airline.DTOs.BookingRequestDTO;
import com.airline.Entities.Booking;
import com.airline.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:3000") // Prepares for frontend integration
public class BookingController {

    @Autowired
    private BookingService bookingService;

    /* 
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDTO request) {
        try {
            // Passes the DTO directly to the service layer
            Booking newBooking = bookingService.processBooking(request);
            return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
        } catch (Exception e) {
            // Catches our RuntimeExceptions (like "Not enough seats") and sends a Bad Request
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    */
   
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDTO request) {
        // No more try-catch needed! If processBooking throws an error, 
        // the GlobalExceptionHandler will automatically intercept it.
        Booking newBooking = bookingService.processBooking(request);
        return new ResponseEntity<>(newBooking, HttpStatus.CREATED);
    }
}