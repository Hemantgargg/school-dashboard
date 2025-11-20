package com.school.dashboard.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String studentRollNo;

    @Column(nullable = false)
    private String name;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    private Long phoneNo;
    private String dob;
    private String address;

    private String parentName;
    private Long parentPhone;
    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String parentEmail;

    private String joiningDate;
    private String currentClass;
}
