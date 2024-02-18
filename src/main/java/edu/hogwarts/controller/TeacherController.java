package edu.hogwarts.controller;

import edu.hogwarts.repository.TeacherRepository;

public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
}
