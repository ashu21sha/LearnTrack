package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.EnrollmentStatus;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private final List<Enrollment> enrollments = new ArrayList<>();

    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(StudentService studentService,
                             CourseService courseService) {

        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void enrollStudent(int studentId, int courseId) {

        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new IllegalArgumentException(
                    "Student is not active."
            );
        }

        if (!course.isActive()) {
            throw new IllegalArgumentException(
                    "Course is not active."
            );
        }

        Enrollment enrollment = new Enrollment(
                IdGenerator.getNextEnrollmentId(),
                studentId,
                courseId
        );

        enrollments.add(enrollment);
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) {

        // Check that student exists
        studentService.findStudentById(studentId);

        List<Enrollment> result = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getStudentId() == studentId) {
                result.add(enrollment);
            }
        }

        return result;
    }

    public void completeEnrollment(int enrollmentId) {

        Enrollment enrollment = findEnrollmentById(enrollmentId);

        enrollment.setStatus(EnrollmentStatus.COMPLETED);
    }

    public void cancelEnrollment(int enrollmentId) {

        Enrollment enrollment = findEnrollmentById(enrollmentId);

        enrollment.setStatus(EnrollmentStatus.CANCELLED);
    }

    private Enrollment findEnrollmentById(int id) {

        for (Enrollment enrollment : enrollments) {

            if (enrollment.getId() == id) {
                return enrollment;
            }
        }

        throw new EntityNotFoundException(
                "Enrollment not found with ID: " + id
        );
    }
}

