package com.school.dashboard.service;

import org.springframework.stereotype.Service;

import com.school.dashboard.model.Student;
import com.school.dashboard.repository.StudentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepo studentRepo;
	
	public Student getStudentByRollNo(String rollNo) {
	return studentRepo.findByStudentRollNo(rollNo); 
	}
	
	
	


	
}
