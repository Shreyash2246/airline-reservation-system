package com.airline.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Flights")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Flight_ID")
    private Integer flightId;

    @Column(name = "Flight_Number", length = 20, nullable = false)
    private String flightNumber;

    @Column(name = "Source_City", length = 100, nullable = false)
    private String sourceCity;

    @Column(name = "Destination_City", length = 100, nullable = false)
    private String destinationCity;

    @Column(name = "Departure_Time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "Arrival_Time", nullable = false)
    private LocalDateTime arrivalTime;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Booking> bookings;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<FlightPricing> flightPricings;

    @Column(name = "Total_Seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "Available_Seats", nullable = false)
    private Integer availableSeats;
}
