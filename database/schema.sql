CREATE DATABASE IF NOT EXISTS airline;
USE airline;

-- 1. Users Table (Handles both regular passengers and Admins)
-- Required fields for registration and admin identification.
CREATE TABLE Users (
    User_ID INT AUTO_INCREMENT PRIMARY KEY,
    Role ENUM('User', 'Admin') DEFAULT 'User',
    Title VARCHAR(10),
    First_Name VARCHAR(50) NOT NULL,
    Last_Name VARCHAR(50) NOT NULL,
    Email_Id VARCHAR(100) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Date_Of_Birth DATE,
    Phone_Number VARCHAR(15)
);

-- 2. Flights Table 
-- Uses Flight_ID as a safe Primary Key, tracks available seats.
CREATE TABLE Flights (
    Flight_ID INT AUTO_INCREMENT PRIMARY KEY,
    Flight_Number VARCHAR(20) NOT NULL,
    Source_City VARCHAR(100) NOT NULL,
    Destination_City VARCHAR(100) NOT NULL,
    Departure_Time DATETIME NOT NULL,
    Arrival_Time DATETIME NOT NULL,
    Total_Seats INT NOT NULL,
    Available_Seats INT NOT NULL
);

-- 3. Flight Pricing Table
-- Handles dynamic pricing for Economy and Business class.
CREATE TABLE Flight_Pricing (
    Pricing_ID INT AUTO_INCREMENT PRIMARY KEY,
    Flight_ID INT NOT NULL,
    Cabin_Class ENUM('Economy', 'Business') NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (Flight_ID) REFERENCES Flights(Flight_ID) ON DELETE CASCADE
);

-- 4. Bookings Table
-- Generates the unique PNR and tracks cancellation status.
CREATE TABLE Bookings (
    PNR_Number VARCHAR(20) PRIMARY KEY,
    User_ID INT NOT NULL,
    Flight_ID INT NOT NULL,
    Booking_Date DATETIME DEFAULT CURRENT_TIMESTAMP,
    Total_Amount DECIMAL(10, 2) NOT NULL,
    Booking_Status ENUM('Confirmed', 'Cancelled', 'Pending') DEFAULT 'Pending',
    FOREIGN KEY (User_ID) REFERENCES Users(User_ID),
    FOREIGN KEY (Flight_ID) REFERENCES Flights(Flight_ID)
);

-- 5. Passengers Table
-- Stores individual passenger details and seat selections.
CREATE TABLE Passengers (
    Passenger_ID INT AUTO_INCREMENT PRIMARY KEY,
    PNR_Number VARCHAR(20) NOT NULL,
    Full_Name VARCHAR(100) NOT NULL,
    Age INT NOT NULL,
    Category ENUM('Adult', 'Child', 'Infant') NOT NULL,
    Seat_Number VARCHAR(10) NOT NULL,
    FOREIGN KEY (PNR_Number) REFERENCES Bookings(PNR_Number) ON DELETE CASCADE
);

-- 6. Payments Table
-- Processes credit/debit transactions and audits deductions.
CREATE TABLE Payments (
    Payment_ID INT AUTO_INCREMENT PRIMARY KEY,
    PNR_Number VARCHAR(20) NOT NULL,
    Amount_Paid DECIMAL(10, 2) NOT NULL,
    Payment_Method ENUM('Credit Card', 'Debit Card') NOT NULL,
    Payment_Date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (PNR_Number) REFERENCES Bookings(PNR_Number) ON DELETE CASCADE
);