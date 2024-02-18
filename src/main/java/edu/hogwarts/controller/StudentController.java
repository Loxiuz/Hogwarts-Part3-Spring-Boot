package edu.hogwarts.controller;

import edu.hogwarts.repository.StudentRepository;

public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
