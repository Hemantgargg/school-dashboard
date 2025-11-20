package com.school.dashboard.service;



import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.dashboard.dto.CreateUserRequest;
import com.school.dashboard.model.Role;
import com.school.dashboard.model.Student;
import com.school.dashboard.model.User;
import com.school.dashboard.repository.StudentRepository;
import com.school.dashboard.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;

    public User createUser(CreateUserRequest request) {

        // 1) Create and save the User
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        User savedUser = userRepository.save(user);

        // 2) If role = STUDENT, link to student
        if (request.getRole() == Role.STUDENT) {

            if (request.getStudentRollNo() == null || request.getStudentRollNo().isEmpty()) {
                throw new RuntimeException("studentRollNo is required for STUDENT role");
            }

            Student student = studentRepository.findByStudentRollNo(request.getStudentRollNo());

            if (student == null) {
                throw new RuntimeException("Student record not found for rollNo: " + request.getStudentRollNo());
            }

            // Link user → student
            student.setUser(savedUser);
            studentRepository.save(student);
        }

        return savedUser;
    }
}

