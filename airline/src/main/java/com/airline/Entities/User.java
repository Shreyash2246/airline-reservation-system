package com.airline.Entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "Users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role", columnDefinition = "ENUM('User', 'Admin') DEFAULT 'User'")
    private Role role;

    @Column(name = "Title", length = 10)
    private String title;

    @Column(name = "First_Name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "Last_Name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "Email_Id", nullable = false, unique = true, length = 100)
    private String emailId;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Date_Of_Birth")
    private LocalDate dateOfBirth;

    @Column(name = "Phone_Number", length = 15)
    private String phoneNumber;

    public enum Role { User, Admin }
}
