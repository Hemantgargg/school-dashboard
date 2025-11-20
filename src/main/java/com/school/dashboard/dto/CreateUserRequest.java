package com.school.dashboard.dto;


import com.school.dashboard.model.Role;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String email;
    private String password;
    private Role role;

    // OPTIONAL: Only used if role = STUDENT
    private String studentRollNo;
}
