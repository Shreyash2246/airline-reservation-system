package com.airline.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "Flight_Pricing")
@Data
public class FlightPricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Pricing_ID")
    private Long pricingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Flight_ID", nullable = false)
    private Flight flight;

    @Enumerated(EnumType.STRING)
    @Column(name = "Cabin_Class", nullable = false)
    private CabinClass cabinClass;

    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    public enum CabinClass { Economy, Business }
}
