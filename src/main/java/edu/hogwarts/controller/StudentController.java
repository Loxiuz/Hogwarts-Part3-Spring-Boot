package edu.hogwarts.controller;

import edu.hogwarts.model.Student;
import edu.hogwarts.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id){
        return ResponseEntity.of(studentRepository.findById(id));
    }

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id, @RequestBody Student student){
        Optional<Student> original = studentRepository.findById(id);
        if(original.isPresent()){
            Student originalStudent = original.get();
            originalStudent.setFirstName(student.getFirstName());
            originalStudent.setLastName(student.getLastName());
            originalStudent.setDateOfBirth(student.getDateOfBirth());

            Student updatedStudent = studentRepository.save(originalStudent);
            return ResponseEntity.ok().body(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable int id){
        Optional<Student> student = studentRepository.findById(id);
        studentRepository.deleteById(id);
        return ResponseEntity.of(student);
    }
}

