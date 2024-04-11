package edu.hogwarts.controller;
import edu.hogwarts.dto.TeacherDto;
import edu.hogwarts.model.House;
import edu.hogwarts.model.Teacher;
import edu.hogwarts.repository.HouseRepository;
import edu.hogwarts.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository;
    private final HouseRepository houseRepository;

    public TeacherController(TeacherRepository teacherRepository, HouseRepository houseRepository) {
        this.teacherRepository = teacherRepository;
        this.houseRepository = houseRepository;
    }

    @GetMapping
    public List<TeacherDto> getAllTeachers(){
        return teacherRepository.findAll().stream().map(TeacherDto::new).toList();
    }

    @GetMapping("/{id}")
    public TeacherDto getTeacher(@PathVariable int id){
        Teacher teacher = teacherRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found in database"));
        return new TeacherDto(teacher);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherDto createTeacher(@RequestBody TeacherDto request){
        Teacher newTeacher = new Teacher();
        updateTeacher(newTeacher, request);
        teacherRepository.save(newTeacher);
        return new TeacherDto(newTeacher);
    }

    @PutMapping("/{id}")
    public TeacherDto updateTeacher(@PathVariable int id, @RequestBody TeacherDto request){
        Teacher teacherToEdit = teacherRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found in database")
        );
        updateTeacher(teacherToEdit, request);
        teacherRepository.save(teacherToEdit);
        return new TeacherDto(teacherToEdit);
    }

    @PatchMapping("/{id}/headOfHouse")
    public TeacherDto editTeacherHeadOfHouse(@PathVariable int id, @RequestBody TeacherDto request){
        Teacher teacherToEdit = teacherRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found in database")
        );
        updateTeacherHeadOfHouse(teacherToEdit, request);
        teacherRepository.save(teacherToEdit);
        return new TeacherDto(teacherToEdit);
    }

    @PatchMapping("/{id}/employmentEnd")
    public TeacherDto editTeacherEmploymentEnd(@PathVariable int id, @RequestBody TeacherDto request){
        Teacher teacherToEdit = teacherRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found in database")
        );
        updateTeacherEmploymentEnd(teacherToEdit, request);
        teacherRepository.save(teacherToEdit);
        return new TeacherDto(teacherToEdit);
    }

    @PatchMapping("/{id}/employment")
    public TeacherDto editTeacherEmployment(@PathVariable int id, @RequestBody TeacherDto request){
        Teacher teacherToEdit = teacherRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found in database")
        );
        updateTeacherEmployment(teacherToEdit, request);
        teacherRepository.save(teacherToEdit);
        return new TeacherDto(teacherToEdit);
    }

    @DeleteMapping("/{id}")
    public TeacherDto deleteTeacher(@PathVariable int id){
        Teacher deletedTeacher = teacherRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found in database")
        );
        teacherRepository.deleteById(id);
        return new TeacherDto(deletedTeacher);
    }

    public void updateTeacher(Teacher original, TeacherDto request) {
        original.setFirstName(request.getFirstName());
        original.setMiddleName(request.getMiddleName());
        original.setLastName(request.getLastName());
        original.setDateOfBirth(request.getDateOfBirth());
        Optional<House> teacherHouse = houseRepository.findById(request.getHouse());
        teacherHouse.ifPresent(original::setHouse);
        original.setHeadOfHouse(request.isHeadOfHouse());
        original.setEmployment(request.getEmployment());
        original.setEmploymentStart(request.getEmploymentStart());
        original.setEmploymentEnd(request.getEmploymentEnd());
    }

    public void updateTeacherHeadOfHouse(Teacher original, TeacherDto request){
        original.setHeadOfHouse(request.isHeadOfHouse());
    }

    public void updateTeacherEmploymentEnd(Teacher original, TeacherDto request){
        original.setEmploymentEnd(request.getEmploymentEnd());
    }

    public void updateTeacherEmployment(Teacher original, TeacherDto request){
        original.setEmployment(request.getEmployment());
    }
}

