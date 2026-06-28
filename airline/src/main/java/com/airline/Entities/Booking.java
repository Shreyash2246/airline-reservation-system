package com.airline.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Bookings")
@Data
public class Booking {

    @Id
    @Column(name = "PNR_Number", length = 20)
    private String pnrNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Flight_ID", nullable = false)
    private Flight flight;

    // insertable and updatable false lets the database handle the CURRENT_TIMESTAMP default
    @Column(name = "Booking_Date", insertable = false, updatable = false)
    private LocalDateTime bookingDate;

    @Column(name = "Total_Amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "Booking_Status")
    private BookingStatus bookingStatus;

    public enum BookingStatus { Confirmed, Cancelled, Pending }
}
