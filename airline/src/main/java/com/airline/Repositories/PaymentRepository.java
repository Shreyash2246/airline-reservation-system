package com.airline.Repositories;

import com.airline.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    // Find all payments made for a specific booking
    List<Payment> findByBooking_PnrNumber(String pnrNumber);
}