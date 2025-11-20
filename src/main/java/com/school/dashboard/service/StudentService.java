package com.school.dashboard.service;

import org.springframework.stereotype.Service;

import com.school.dashboard.model.Student;
import com.school.dashboard.model.User;
import com.school.dashboard.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;
	
	public Student getStudentByRollNo(String rollNo) {
	return studentRepository.findByStudentRollNo(rollNo); 
	}
	
	public Student createStudent(Student student, User user) {
	    student.setUser(user);  // ⭐ Link student to user
	    return studentRepository.save(student);
	}

	
	

}
