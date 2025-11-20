package com.school.dashboard.controller;


import com.school.dashboard.dto.CreateUserRequest;
import com.school.dashboard.dto.CreateUserResponse;
import com.school.dashboard.model.User;
import com.school.dashboard.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    /**
     * Create a user. Only PRINCIPAL may call this.
     * If role == STUDENT, `studentRollNo` must reference an existing Student row.
     */
    @PostMapping("/users")
    @PreAuthorize("hasRole('PRINCIPAL')")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        User created = adminService.createUser(request);

        CreateUserResponse response = new CreateUserResponse(
                created.getId(),
                created.getEmail(),
                created.getRole()
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

