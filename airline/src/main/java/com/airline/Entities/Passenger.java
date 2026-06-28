package com.airline.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Passengers")
@Data
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Passenger_ID")
    private Long passengerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PNR_Number", nullable = false)
    private Booking booking;

    @Column(name = "Full_Name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "Age", nullable = false)
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "Category", nullable = false)
    private PassengerCategory category;

    @Column(name = "Seat_Number", nullable = false, length = 10)
    private String seatNumber;

    public enum PassengerCategory { Adult, Child, Infant }
}