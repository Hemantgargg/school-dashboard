package com.school.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
		
		System.out.println("Application is Running");
		System.out.println(new BCryptPasswordEncoder().encode("admin123"));
	
		  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		    boolean match = encoder.matches("admin123",
		        "$2a$10$.c5o66xX639Wp3g4ekw/O.xiCAkfyPChPGyLH5UfSRbPzJZX3Kwlq");

		    System.out.println(match);  // should print true

	}

}
