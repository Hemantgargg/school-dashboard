package com.school.dashboard.security;



import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.school.dashboard.model.Role;
import com.school.dashboard.model.Student;
import com.school.dashboard.model.User;
import com.school.dashboard.repository.StudentRepository;

@Component
@RequiredArgsConstructor
public class StudentAccessValidator {

    private final StudentRepository studentRepository;

    public boolean canAccessStudent(Authentication authentication, String rollNo) {

        // Defensive checks
        if (authentication == null || authentication.getPrincipal() == null) {
            System.out.println("[StudentAccessValidator] No authentication present");
            return false;
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof CustomUserDetails)) {
            System.out.println("[StudentAccessValidator] Principal is not CustomUserDetails, class=" + principal.getClass());
            return false;
        }

        User loggedInUser = ((CustomUserDetails) principal).getUser();

        System.out.println("[StudentAccessValidator] LoggedInUser id=" + loggedInUser.getId() + " email=" + loggedInUser.getEmail() + " role=" + loggedInUser.getRole());

        // Non-students can access ANY student dashboard
        if (loggedInUser.getRole() != Role.STUDENT) {
            System.out.println("[StudentAccessValidator] Access granted: non-student role=" + loggedInUser.getRole());
            return true;
        }

        // Student: can only access their own dashboard
        Student targetStudent = studentRepository.findByStudentRollNo(rollNo);

        if (targetStudent == null) {
            System.out.println("[StudentAccessValidator] Target student not found for rollNo=" + rollNo);
            return false;
        }

        Long targetUserId = targetStudent.getUser() == null ? null : targetStudent.getUser().getId();
        System.out.println("[StudentAccessValidator] targetStudent.id=" + targetStudent.getId() + " targetStudent.userId=" + targetUserId);

        boolean allowed = targetUserId != null && targetUserId.equals(loggedInUser.getId());

        System.out.println("[StudentAccessValidator] Final allowed=" + allowed);
        return allowed;
    }
}
