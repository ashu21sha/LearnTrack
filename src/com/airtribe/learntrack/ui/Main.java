package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final StudentService studentService =
            new StudentService();

    private static final CourseService courseService =
            new CourseService();

    private static final EnrollmentService enrollmentService =
            new EnrollmentService(studentService, courseService);

    public static void main(String[] args) {

        boolean running = true;

        System.out.println("=================================");
        System.out.println("       Welcome to LearnTrack      ");
        System.out.println("=================================");

        while (running) {

            try {

                showMainMenu();

                int choice = readInt("Enter your choice: ");

                switch (choice) {

                    case 1 -> studentMenu();

                    case 2 -> courseMenu();

                    case 3 -> enrollmentMenu();

                    case 0 -> {
                        running = false;
                        System.out.println("Thank you for using LearnTrack!");
                    }

                    default ->
                            System.out.println("Invalid option. Please try again.");
                }

            } catch (EntityNotFoundException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (InvalidInputException e) {

                System.out.println("Invalid input: " + e.getMessage());

            } catch (Exception e) {

                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

        scanner.close();
    }


    private static void showMainMenu() {

        System.out.println();
        System.out.println("========== MAIN MENU ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
    }


    private static void studentMenu() {

        boolean back = false;

        while (!back) {

            System.out.println();
            System.out.println("======= STUDENT MANAGEMENT =======");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student By ID");
            System.out.println("4. Deactivate Student");
            System.out.println("5. Remove Student");
            System.out.println("6. Update Student");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");

            try {

                switch (choice) {

                    case 1 -> addStudent();

                    case 2 -> viewStudents();

                    case 3 -> searchStudent();

                    case 4 -> deactivateStudent();

                    case 5 -> removeStudent();

                    case 6 -> updateStudent();

                    case 0 -> back = true;

                    default ->
                            System.out.println("Invalid option.");

                }

            } catch (EntityNotFoundException | InvalidInputException e) {

                System.out.println("Error: " + e.getMessage());

            }
        }
    }

    private static void addStudent() {

        String firstName = readString("First Name: ");
        String lastName = readString("Last Name: ");
        String email = readString("Email: ");
        String batch = readString("Batch: ");

        Student student = new Student(
                IdGenerator.getNextStudentId(),
                firstName,
                lastName,
                email,
                batch
        );

        studentService.addStudent(student);

        System.out.println(
                "Student added successfully. ID: "
                        + student.getId()
        );
    }

    private static void viewStudents() {

        List<Student> students = studentService.listStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudent() {

        int id = readInt("Enter Student ID: ");

        Student student = studentService.findStudentById(id);

        System.out.println(student);
    }

    private static void deactivateStudent() {

        int id = readInt("Enter Student ID: ");

        studentService.deactivateStudent(id);

        System.out.println("Student deactivated successfully.");
    }

    private static void removeStudent() {

        int id = readInt("Enter Student ID: ");

        studentService.removeStudent(id);

        System.out.println("Student removed successfully.");
    }

    private static void updateStudent() {

        int id = readInt("Enter Student ID: ");

        Student student = studentService.findStudentById(id);

        String firstName = readString(
                "Enter new first name: "
        );

        String lastName = readString(
                "Enter new last name: "
        );

        String email = readString(
                "Enter new email: "
        );

        String batch = readString(
                "Enter new batch: "
        );

        Student updatedStudent = new Student(
                id,
                firstName,
                lastName,
                email,
                batch
        );

        updatedStudent.setActive(student.isActive());

        studentService.updateStudent(updatedStudent);

        System.out.println("Student updated successfully.");
    }


    private static void courseMenu() {

        boolean back = false;

        while (!back) {

            System.out.println();
            System.out.println("======= COURSE MANAGEMENT =======");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Activate Course");
            System.out.println("4. Deactivate Course");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");

            try {

                switch (choice) {

                    case 1 -> addCourse();

                    case 2 -> viewCourses();

                    case 3 -> activateCourse();

                    case 4 -> deactivateCourse();

                    case 0 -> back = true;

                    default ->
                            System.out.println("Invalid option.");
                }

            } catch (EntityNotFoundException | InvalidInputException e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addCourse() {

        String name = readString("Course Name: ");
        String description = readString("Description: ");
        int duration = readInt("Duration in weeks: ");

        if (duration <= 0) {
            throw new InvalidInputException(
                    "Duration must be greater than zero."
            );
        }

        Course course = new Course(
                IdGenerator.getNextCourseId(),
                name,
                description,
                duration
        );

        courseService.addCourse(course);

        System.out.println(
                "Course added successfully. ID: "
                        + course.getId()
        );
    }

    private static void viewCourses() {

        List<Course> courses = courseService.listCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void activateCourse() {

        int id = readInt("Enter Course ID: ");

        courseService.activateCourse(id);

        System.out.println("Course activated successfully.");
    }

    private static void deactivateCourse() {

        int id = readInt("Enter Course ID: ");

        courseService.deactivateCourse(id);

        System.out.println("Course deactivated successfully.");
    }

    private static void enrollmentMenu() {

        boolean back = false;

        while (!back) {

            System.out.println();
            System.out.println("===== ENROLLMENT MANAGEMENT =====");
            System.out.println("1. Enroll Student");
            System.out.println("2. View Student Enrollments");
            System.out.println("3. Mark Enrollment Completed");
            System.out.println("4. Cancel Enrollment");
            System.out.println("0. Back");

            int choice = readInt("Enter choice: ");

            try {

                switch (choice) {

                    case 1 -> enrollStudent();

                    case 2 -> viewStudentEnrollments();

                    case 3 -> completeEnrollment();

                    case 4 -> cancelEnrollment();

                    case 0 -> back = true;

                    default ->
                            System.out.println("Invalid option.");
                }

            } catch (EntityNotFoundException |
                     InvalidInputException |
                     IllegalArgumentException e) {

                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void enrollStudent() {

        int studentId = readInt("Student ID: ");
        int courseId = readInt("Course ID: ");

        enrollmentService.enrollStudent(
                studentId,
                courseId
        );

        System.out.println(
                "Student enrolled successfully."
        );
    }

    private static void viewStudentEnrollments() {

        int studentId = readInt("Student ID: ");

        List<Enrollment> enrollments =
                enrollmentService.getEnrollmentsForStudent(
                        studentId
                );

        if (enrollments.isEmpty()) {

            System.out.println(
                    "No enrollments found for this student."
            );

            return;
        }

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private static void completeEnrollment() {

        int enrollmentId =
                readInt("Enrollment ID: ");

        enrollmentService.completeEnrollment(
                enrollmentId
        );

        System.out.println(
                "Enrollment marked as completed."
        );
    }

    private static void cancelEnrollment() {

        int enrollmentId =
                readInt("Enrollment ID: ");

        enrollmentService.cancelEnrollment(
                enrollmentId
        );

        System.out.println(
                "Enrollment cancelled."
        );
    }


    private static int readInt(String message) {

        System.out.print(message);

        String input = scanner.nextLine();

        try {

            return Integer.parseInt(input);

        } catch (NumberFormatException e) {

            throw new InvalidInputException(
                    "Please enter a valid number."
            );
        }
    }

    private static String readString(String message) {

        System.out.print(message);

        String input = scanner.nextLine();

        if (input == null || input.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Input cannot be empty."
            );
        }

        return input.trim();
    }
}