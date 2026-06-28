package com.airline.DTOs;

import lombok.Data;
import java.util.List;

@Data
public class BookingRequestDTO {
    private Long userId;
    private Integer flightId; 
    private String cabinClass; // Expects 'Economy' or 'Business'
    private String paymentMethod; // Expects 'Credit Card' or 'Debit Card'
    private List<PassengerDTO> passengers;

    @Data
    public static class PassengerDTO {
        private String fullName;
        private Integer age;
        private String category; // Expects 'Adult', 'Child', or 'Infant'
        private String seatNumber;
    }
}