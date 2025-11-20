package com.school.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.school.dashboard.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // To fetch student by roll number (needed for Student Dashboard)
    Student findByStudentRollNo(String studentRollNo);
}
