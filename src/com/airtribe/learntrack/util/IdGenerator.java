package com.airtribe.learntrack.util;

public class IdGenerator {

    private static int studentIdCounter = 100;
    private static int courseIdCounter = 200;
    private static int enrollmentIdCounter = 300;

    private IdGenerator() {
        // Utility class - no object creation
    }

    public static int getNextStudentId() {
        return ++studentIdCounter;
    }

    public static int getNextCourseId() {
        return ++courseIdCounter;
    }

    public static int getNextEnrollmentId() {
        return ++enrollmentIdCounter;
    }
}
