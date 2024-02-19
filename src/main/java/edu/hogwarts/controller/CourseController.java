package edu.hogwarts.controller;

import edu.hogwarts.model.Course;
import edu.hogwarts.model.Student;
import edu.hogwarts.model.Teacher;
import edu.hogwarts.repository.CourseRepository;
import edu.hogwarts.repository.StudentRepository;
import edu.hogwarts.repository.TeacherRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    private final StudentRepository studentRepository;

    public CourseController(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }
    @GetMapping("/courses")
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable int id){
        return ResponseEntity.of(courseRepository.findById(id));
    }
    @GetMapping("/courses/{id}/students")
    public ResponseEntity<Set<Student>> getCourseStudents(@PathVariable int id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return ResponseEntity.ok().body(course.get().getStudents());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/courses/{id}/teacher")
    public  ResponseEntity<Teacher> getCourseTeacher(@PathVariable int id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return ResponseEntity.ok().body(course.get().getTeacher());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/courses")
    public Course createCourse(@RequestBody Course course){
        return courseRepository.save(course);
    }
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course course){
        Optional<Course> original = courseRepository.findById(id);
        if(original.isPresent()){
            Course originalCourse = original.get();
            originalCourse.setTeacher(course.getTeacher());
            originalCourse.setStudents(course.getStudents());
            Course updatedCourse = courseRepository.save(originalCourse);
            return ResponseEntity.ok().body(updatedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/courses/{id}/teacher")
    public ResponseEntity<Teacher> updateCourseTeacher(@PathVariable int id, @RequestBody Teacher teacher){
        Optional<Course> course = courseRepository.findById(id);
        Optional<Teacher> newTeacher = teacherRepository.findById(teacher.getId());
        if(course.isPresent() && newTeacher.isPresent()){
            Course currentCourse = course.get();
            Teacher currentTeacher = newTeacher.get();
            currentCourse.setTeacher(currentTeacher);

            courseRepository.save(currentCourse);

            return ResponseEntity.ok().body(currentCourse.getTeacher());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Course> addStudentToCourse(@PathVariable int courseId, @PathVariable int studentId){
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Student> student = studentRepository.findById(studentId);

        if(course.isPresent() && student.isPresent()){
            Course currentCourse = course.get();
            currentCourse.getStudents().add(student.get());
            Course updatedCourse = courseRepository.save(currentCourse);
            return ResponseEntity.ok().body(updatedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable int id){
        Optional<Course> course = courseRepository.findById(id);
        courseRepository.deleteById(id);
        return ResponseEntity.of(course);
    }
    @DeleteMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Student> deleteCourseStudent(@PathVariable int courseId, @PathVariable int studentId){
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Student> student = studentRepository.findById(studentId);

        if(course.isPresent() && student.isPresent()){
            Course currentCourse = course.get();
            currentCourse.getStudents().remove(student.get());
            courseRepository.save(currentCourse);
            return ResponseEntity.ok().body(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/courses/{id}/teacher")
    public ResponseEntity<Teacher> deleteCourseTeacher(@PathVariable int id){
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            Course currentCourse = course.get();
            currentCourse.setTeacher(null);
            Course updatedCourse = courseRepository.save(currentCourse);
            return ResponseEntity.ok().body(updatedCourse.getTeacher());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
