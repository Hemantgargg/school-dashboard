package com.school.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school.dashboard.model.Student;
import com.school.dashboard.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

	private final StudentService studentService;
	
	@GetMapping("/{rollNo}")
	public ResponseEntity<Student> getStudentDashboard(@PathVariable String rollNo){
	Student student = studentService.getStudentByRollNo(rollNo);
	
	if (student == null) {
		return ResponseEntity.notFound().build();
	}
	return ResponseEntity.ok(student);
	}
}
