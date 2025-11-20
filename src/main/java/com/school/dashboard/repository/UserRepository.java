package com.school.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.dashboard.model.User;

public interface UserRepository extends JpaRepository<User	, Long> {

    User findByEmail(String email); //login by email used to login

}
