airtribe-projects-repo
LearnTrack
LearnTrack is a simple console-based Java application for managing students, courses, and student enrollments.

The project is developed using Core Java and demonstrates important Object-Oriented Programming concepts such as:

Encapsulation
Inheritance
Method Overriding
Constructor Overloading
Method Overloading
Static members
ArrayList
Exception Handling
Custom Exceptions
Service Layer
Console-based Menu
Project Structure
LearnTrack │ ├── README.md │ ├── docs │ └── Design_Notes.md │ └── src └── com └── airtribe └── learntrack │ ├── entity │ ├── Person.java │ ├── Student.java │ ├── Course.java │ ├── Enrollment.java │ └── EnrollmentStatus.java │ ├── service │ ├── StudentService.java │ ├── CourseService.java │ └── EnrollmentService.java │ ├── exception │ ├── EntityNotFoundException.java │ └── InvalidInputException.java │ ├── util │ ├── IdGenerator.java │ └── InputValidator.java │ └── ui └── Main.java

How to Compile
Open a terminal in the project root directory.

Compile all Java files:

On Windows Command Prompt, you can use:

javac -d out src\com\airtribe\learntrack\entity*.java src\com\airtribe\learntrack\service*.java src\com\airtribe\learntrack\exception*.java src\com\airtribe\learntrack\util*.java src\com\airtribe\learntrack\ui\Main.java

How to Run
After successful compilation:

java -cp out com.airtribe.learntrack.ui.Main


Class Diagram
┌─────────────────────┐
│       Person        │
├─────────────────────┤
│ - id                │
│ - firstName         │
│ - lastName          │
│ - email             │
├─────────────────────┤
│ + getDisplayName()  │
└──────────┬──────────┘
│
extends
│
┌──────────▼──────────┐
│       Student       │
├─────────────────────┤
│ - batch             │
│ - active            │
└─────────────────────┘
┌─────────────────────┐ │ Course │ ├─────────────────────┤ │ - id │ │ - courseName │ │ - description │ │ - durationInWeeks │ │ - active │ └──────────┬──────────┘ │ │ courseId │ ▼ ┌─────────────────────┐ │ Enrollment │ ├─────────────────────┤ │ - id │ │ - studentId │ │ - courseId │ │ - enrollmentDate │ │ - status │ └──────────┬──────────┘ │ │ studentId ▼ ┌───────────┐ │ Student │ └───────────┘

┌─────────────────────┐ │ StudentService │ ├─────────────────────┤ │ - students │ ├─────────────────────┤ │ + addStudent() │ │ + updateStudent() │ │ + findStudentById() │ │ + listStudents() │ │ + deactivateStudent │ └──────────┬──────────┘ │ manages ▼ Student

┌─────────────────────┐ │ CourseService │ ├─────────────────────┤ │ - courses │ ├─────────────────────┤ │ + addCourse() │ │ + findCourseById() │ │ + listCourses() │ │ + activateCourse() │ │ + deactivateCourse()│ └──────────┬──────────┘ │ manages ▼ Course

┌─────────────────────────┐ │ EnrollmentService │ ├─────────────────────────┤ │ - enrollments │ │ - studentService │ │ - courseService │ ├─────────────────────────┤ │ + enrollStudent() │ │ + getEnrollments...() │ │ + completeEnrollment() │ │ + cancelEnrollment() │ └────────────┬────────────┘ │ ┌─────┴─────┐ ▼ ▼ Student Course