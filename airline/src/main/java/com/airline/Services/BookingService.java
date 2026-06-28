package com.airline.Services;

import com.airline.DTOs.BookingRequestDTO;
import com.airline.Entities.*;
import com.airline.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    @Autowired private FlightRepository flightRepository;
    @Autowired private FlightPricingRepository pricingRepository;
    @Autowired private BookingRepository bookingRepository;
    @Autowired private PassengerRepository passengerRepository;
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private UserRepository userRepository;

    @Transactional // CRITICAL: Rolls back the entire method if any single line fails
    public Booking processBooking(BookingRequestDTO request) {
        
        // 1. Fetch User and Flight
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        // 2. Check Seat Capacity
        if (flight.getAvailableSeats() < request.getPassengers().size()) {
            throw new RuntimeException("Not enough available seats on this flight.");
        }

        // 3. Fetch Pricing & Calculate Total Amount
        FlightPricing.CabinClass cabinClassEnum = FlightPricing.CabinClass.valueOf(request.getCabinClass());
        List<FlightPricing> pricings = pricingRepository
                .findByFlight_FlightIdAndCabinClass(flight.getFlightId(), cabinClassEnum);

        if (pricings.isEmpty()) {
            throw new RuntimeException("Pricing not found for this cabin class.");
        }
        
        FlightPricing pricing = pricings.get(0);
        BigDecimal totalAmount = pricing.getPrice().multiply(new BigDecimal(request.getPassengers().size()));

        // 4. Create the Booking Record
        Booking booking = new Booking();
        // Generates a unique 7-character alphanumeric PNR
        booking.setPnrNumber("PNR" + UUID.randomUUID().toString().substring(0, 7).toUpperCase());
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setTotalAmount(totalAmount);
        booking.setBookingStatus(Booking.BookingStatus.Confirmed);
        booking = bookingRepository.save(booking);

        // 5. Save Individual Passengers
        for (BookingRequestDTO.PassengerDTO p : request.getPassengers()) {
            Passenger passenger = new Passenger();
            passenger.setBooking(booking);
            passenger.setFullName(p.getFullName());
            passenger.setAge(p.getAge());
            passenger.setCategory(Passenger.PassengerCategory.valueOf(p.getCategory()));
            passenger.setSeatNumber(p.getSeatNumber());
            passengerRepository.save(passenger);
        }

        // 6. Record Payment
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmountPaid(totalAmount);
        payment.setPaymentMethod(request.getPaymentMethod());
        paymentRepository.save(payment);

        // 7. Deduct Available Seats
        flight.setAvailableSeats(flight.getAvailableSeats() - request.getPassengers().size());
        flightRepository.save(flight);

        return booking; // Returns the completed booking object
    }
}