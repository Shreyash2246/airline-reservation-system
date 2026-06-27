use airline;

-- 1. Register a new User
INSERT INTO Users (Role, Title, First_Name, Last_Name, Email_Id, Password, Date_Of_Birth, Phone_Number)
VALUES ('User', 'Mr', 'Alex', 'Dev', 'alex.dev@email.com', 'SecurePass123!', '2000-05-15', '9876543210');

-- 2. Admin adds a new Flight 
INSERT INTO Flights (Flight_Number, Source_City, Destination_City, Departure_Time, Arrival_Time, Total_Seats, Available_Seats)
VALUES ('IND-202', 'Pune', 'Bangalore', '2026-06-30 08:00:00', '2026-06-30 09:30:00', 180, 180);

-- 3. Admin sets the Pricing for that specific flight
-- (Using Flight_ID 1, assuming this is the first flight inserted)
INSERT INTO Flight_Pricing (Flight_ID, Cabin_Class, Price)
VALUES (1, 'Economy', 3500.00),
       (1, 'Business', 8500.00);
	
-- 2. Flight Search
-- Search for available flights and join with pricing details
SELECT f.Flight_Number, f.Source_City, f.Destination_City, f.Departure_Time, fp.Cabin_Class, fp.Price, f.Available_Seats
FROM Flights f
JOIN Flight_Pricing fp ON f.Flight_ID = fp.Flight_ID
WHERE f.Source_City = 'Pune'
  AND f.Destination_City = 'Bangalore'
  AND DATE(f.Departure_Time) = '2026-06-30'
  AND f.Available_Seats > 0; -- Enforcing the limitation that seats must be available 
  
  
-- 3. The Booking Transaction
-- Step A: Generate the Booking (Assuming User_ID 1 books Flight_ID 1)
INSERT INTO Bookings (PNR_Number, User_ID, Flight_ID, Total_Amount, Booking_Status)
VALUES ('PNR12345ABC', 1, 1, 7000.00, 'Confirmed');

-- Step B: Add the 2 Passengers and their selected seats
INSERT INTO Passengers (PNR_Number, Full_Name, Age, Category, Seat_Number)
VALUES ('PNR12345ABC', 'Alex Dev', 26, 'Adult', '12A'),
       ('PNR12345ABC', 'Sam Dev', 24, 'Adult', '12B');

-- Step C: Record the Payment details
INSERT INTO Payments (PNR_Number, Amount_Paid, Payment_Method)
VALUES ('PNR12345ABC', 7000.00, 'Credit Card');

-- Step D: Update the database to deduct 2 seats from the flight's capacity
UPDATE Flights
SET Available_Seats = Available_Seats - 2
WHERE Flight_ID = 1;


-- 4. Ticket Cancellation
-- Step A: Mark the booking as cancelled
UPDATE Bookings
SET Booking_Status = 'Cancelled'
WHERE PNR_Number = 'PNR12345ABC';

-- Step B: Restore the 2 available seats back to the flight pool
UPDATE Flights
SET Available_Seats = Available_Seats + 2
WHERE Flight_ID = (SELECT Flight_ID FROM Bookings WHERE PNR_Number = 'PNR12345ABC');