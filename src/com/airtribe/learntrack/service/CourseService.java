package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    // Overloading
    public void addCourse(String courseName, String description,
                          int durationInWeeks) {

        Course course = new Course(
                IdGenerator.getNextCourseId(),
                courseName,
                description,
                durationInWeeks
        );

        courses.add(course);
    }

    public List<Course> listCourses() {
        return courses;
    }

    public Course findCourseById(int id) {

        for (Course course : courses) {

            if (course.getId() == id) {
                return course;
            }
        }

        throw new EntityNotFoundException(
                "Course not found with ID: " + id
        );
    }

    public void activateCourse(int id) {

        Course course = findCourseById(id);
        course.setActive(true);
    }

    public void deactivateCourse(int id) {

        Course course = findCourseById(id);
        course.setActive(false);
    }
}
