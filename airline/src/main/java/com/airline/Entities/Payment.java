package com.airline.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Payments")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Payment_ID")
    private Long paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PNR_Number", nullable = false)
    private Booking booking;

    @Column(name = "Amount_Paid", nullable = false, precision = 10, scale = 2)
    private BigDecimal amountPaid;

    // Mapped as String to seamlessly handle the spaces in 'Credit Card' and 'Debit Card'
    @Column(name = "Payment_Method", nullable = false)
    private String paymentMethod; 

    @Column(name = "Payment_Date", insertable = false, updatable = false)
    private LocalDateTime paymentDate;
}