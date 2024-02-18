package edu.hogwarts.controller;

import edu.hogwarts.model.Course;
import edu.hogwarts.model.Student;
import edu.hogwarts.model.Teacher;
import edu.hogwarts.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    @GetMapping("/courses")
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id){

    }
    @GetMapping("/courses/{id}/students")
    public List<Student> getCourseStudents(@PathVariable int id){}
    @GetMapping("/courses/{id}/teacher")
    public  ResponseEntity<Teacher> getCourseTeacher(@PathVariable int id){}
    @PostMapping("/courses")
    public Course createCourse(){}
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int Id, @RequestBody Course course){}
    @PutMapping("/courses/{id}/teacher")
    public ResponseEntity<Teacher> newTeacher(@PathVariable int id, @RequestBody Teacher teacher){}
    @PutMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Student> addStudent(@PathVariable int courseId, @PathVariable int studentId){}
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Course> courseTeacher(@PathVariable int id){}
    @DeleteMapping("/courses/{id}/teacher")
    public ResponseEntity<Student> deleteCourseStudent(@PathVariable int courseId, @PathVariable int studentId){}
    @DeleteMapping("/courses/{id}/students/{id}")
    public ResponseEntity<Student> deleteCourseTeacher(@PathVariable int courseId){}
}
