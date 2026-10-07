1. Why did we use ArrayList instead of an array?

We used ArrayList because the number of students, courses and enrollments
is not fixed.

An array has a fixed size.

2. Where did we use static members and why?

Static members are used in the IdGenerator class.

Example:

private static int studentIdCounter = 100;
private static int courseIdCounter = 200;
private static int enrollmentIdCounter = 300;

Static methods are also used:

public static int getNextStudentId()
public static int getNextCourseId()
public static int getNextEnrollmentId()

The reason for using static is that the ID counters should belong to the
class rather than to an individual object.

For example, every time a new student is created, we want the same student
ID counter to be updated.

int id = IdGenerator.getNextStudentId();

We do not need to create an IdGenerator object.

This makes the utility convenient to use and ensures that the counters are
shared across the application.

3. Where did we use inheritance and what did we gain from it?

Inheritance is used between Person and Student.

public class Student extends Person

Person contains common information:

id
firstName
lastName
email

Student contains student-specific information:

batch
active

Without inheritance, we would need to duplicate the common fields inside
Student.

With inheritance, the common functionality is defined once in Person
and reused by Student.