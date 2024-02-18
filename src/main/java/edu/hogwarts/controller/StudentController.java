package edu.hogwarts.controller;

import edu.hogwarts.model.Student;
import edu.hogwarts.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){

    }
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id){}

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student){}
    @PutMapping("students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student){}
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id){}
}
