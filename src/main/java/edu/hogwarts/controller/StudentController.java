package edu.hogwarts.controller;

import edu.hogwarts.dto.StudentDto;
import edu.hogwarts.model.House;
import edu.hogwarts.model.Student;
import edu.hogwarts.repository.HouseRepository;
import edu.hogwarts.repository.StudentRepository;
import org.springframework.http.HttpStatus;
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
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found in database"));
        return new StudentDto(student);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto createStudent(@RequestBody StudentDto request){
        if(request.getName() != null){
            String[] nameSplit = request.getName().split(" ");
            switch (nameSplit.length) {
                case 3:
                    request.setFirstName(nameSplit[0]);
                    request.setMiddleName(nameSplit[1]);
                    request.setLastName(nameSplit[2]);
                    break;
                case 2:
                    request.setFirstName(nameSplit[0]);
                    request.setLastName(nameSplit[1]);
                    break;
                case 1:
                    request.setFirstName(nameSplit[0]);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid format for full name.");
            }
        }
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

    @PatchMapping("/{id}/prefect")
    public StudentDto editStudentPrefect(@PathVariable int id, @RequestBody StudentDto request){
        Student studentToEdit = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found in database")
        );
        updateStudentPrefect(studentToEdit, request);
        studentRepository.save(studentToEdit);
        return new StudentDto(studentToEdit);
    }

    @PatchMapping("/{id}/schoolYear")
    public StudentDto editStudentSchoolYear(@PathVariable int id, @RequestBody StudentDto request){
        Student studentToEdit = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found in database")
        );
        updateStudentSchoolYear(studentToEdit, request);
        studentRepository.save(studentToEdit);
        return new StudentDto(studentToEdit);
    }

    @PatchMapping("/{id}/graduationYear")
    public StudentDto editStudentGraduationYear(@PathVariable int id, @RequestBody StudentDto request){
        Student studentToEdit = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found in database")
        );
        updateStudentGraduationYear(studentToEdit, request);
        studentRepository.save(studentToEdit);
        return new StudentDto(studentToEdit);
    }

    public void updateStudentGraduationYear(Student original, StudentDto request){
        if(request.getGraduationYear() > 0){
            original.setGraduationYear(request.getGraduationYear());
            original.setGraduated(true);
        }
    }

    public void updateStudentPrefect(Student original, StudentDto request){
        original.setPrefect(request.isPrefect());
    }
    public void updateStudentSchoolYear(Student original, StudentDto request){
        original.setSchoolYear(request.getSchoolYear());
    }

    public void updateStudent(Student original, StudentDto request) {
        original.setFirstName(request.getFirstName());
        original.setMiddleName(request.getMiddleName());
        original.setLastName(request.getLastName());
        original.setDateOfBirth(request.getDateOfBirth());
        Optional<House> studentHouse = houseRepository.findById(request.getHouse());
        studentHouse.ifPresent(original::setHouse);
        original.setSchoolYear(request.getSchoolYear());
        original.setPrefect(request.isPrefect());
        original.setEnrollmentYear(request.getEnrollmentYear());
        original.setGraduationYear(request.getGraduationYear());
        original.setGraduated(request.isGraduated());
    }
}



