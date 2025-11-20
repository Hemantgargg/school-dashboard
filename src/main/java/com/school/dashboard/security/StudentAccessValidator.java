package com.school.dashboard.security;




import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.school.dashboard.model.Role;
import com.school.dashboard.model.Student;
import com.school.dashboard.model.User;
import com.school.dashboard.repository.StudentRepo;

@Component
@RequiredArgsConstructor
public class StudentAccessValidator {

    private final StudentRepo studentRepo;

    public boolean canAccessStudent(Authentication authentication, String rollNo) {

        User loggedInUser = ((CustomUserDetails) authentication.getPrincipal()).getUser();

        // Non-students can access ANY student dashboard
        if (loggedInUser.getRole() != Role.STUDENT) {
            return true;
        }

        // Student: can only access their own dashboard
        Student targetStudent = studentRepo.findByStudentRollNo(rollNo);

        if (targetStudent == null) { return false;
        }
        return targetStudent.getUser() != null
                && targetStudent.getUser().getId().equals(loggedInUser.getId());
    }
}
