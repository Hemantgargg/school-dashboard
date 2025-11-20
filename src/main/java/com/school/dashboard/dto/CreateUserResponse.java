package com.school.dashboard.dto;


import com.school.dashboard.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserResponse {
    private Long id;
    private String email;
    private Role role;
}
