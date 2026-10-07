package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student){
        students.add(student);
    }

    //Method overriding
    public void addStudent(String firstName,String lastName,String email,String batch){
        Student student = new Student(IdGenerator.getNextStudentId(),firstName,lastName,email,batch);
        students.add(student);
    }

    // Remove student
    public void removeStudent(int id) {

        Student student = findStudentById(id);
        students.remove(student);
    }


    // Update student
    public void updateStudent(Student updatedStudent) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId() == updatedStudent.getId()) {
                students.set(i, updatedStudent);
                return;
            }
        }

        throw new  EntityNotFoundException(
                "Student not found with ID: " + updatedStudent.getId()
        );
    }

    // list Students
    public List<Student> listStudents() {
        return students;
    }

    // Search by ID
    public Student findStudentById(int id) {

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }

        throw new EntityNotFoundException(
                "Student not found with ID: " + id
        );
    }

    // Deactivate student
    public void deactivateStudent(int id) {

        Student student = findStudentById(id);
        student.setActive(false);
    }
}
