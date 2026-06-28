package com.airline.Repositories;

import com.airline.Entities.FlightPricing;
import com.airline.Entities.FlightPricing.CabinClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightPricingRepository extends JpaRepository<FlightPricing, Long> {
    
    // Find pricing details for a specific flight and cabin class
    List<FlightPricing> findByFlight_FlightIdAndCabinClass(Integer flightId, CabinClass cabinClass);
}