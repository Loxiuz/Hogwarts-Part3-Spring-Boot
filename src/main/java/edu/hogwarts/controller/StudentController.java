package edu.hogwarts.controller;

import edu.hogwarts.dto.StudentDto;
import edu.hogwarts.model.House;
import edu.hogwarts.model.Student;
import edu.hogwarts.repository.HouseRepository;
import edu.hogwarts.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final HouseRepository houseRepository;

    public StudentController(StudentRepository studentRepository, HouseRepository houseRepository) {
        this.studentRepository = studentRepository;
        this.houseRepository = houseRepository;
    }

    @GetMapping
    public List<StudentDto> getAllStudents(){
        return studentRepository.findAll().stream().map(StudentDto::new).toList();
    }

    @GetMapping("/{id}")
    public StudentDto getStudent(@PathVariable int id){
        Student reservation = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found in database"));
        return new StudentDto(reservation);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto createStudent(@RequestBody StudentDto request){
        Student newStudent = new Student();
        updateStudent(newStudent, request);
        studentRepository.save(newStudent);
        return new StudentDto(newStudent);
    }

    @PutMapping("/{id}")
    public StudentDto editStudent(@PathVariable int id, @RequestBody StudentDto request){
        Student studentToEdit = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found in database")
        );
    updateStudent(studentToEdit, request);
    studentRepository.save(studentToEdit);
    return new StudentDto(studentToEdit);
    }
    public void updateStudent(Student original, StudentDto request) {
        original.setFirstName(request.getFirstName());
        original.setMiddleName(request.getMiddleName());
        original.setLastName(request.getLastName());
        Optional<House> studentHouse = houseRepository.findById(request.getHouse());
        studentHouse.ifPresent(original::setHouse);
    }
}



