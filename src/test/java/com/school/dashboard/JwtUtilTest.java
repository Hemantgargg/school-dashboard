package com.school.dashboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.school.dashboard.security.JwtUtil;

@SpringBootTest
public class JwtUtilTest {
	 @Autowired
	    private JwtUtil jwtUtil;

	    @Test
	    void testGenerateToken() {
	        String token = jwtUtil.generateToken("test@gmail.com");
	        System.out.println("Generated Token:");
	        System.out.println(token);
	    }
}
