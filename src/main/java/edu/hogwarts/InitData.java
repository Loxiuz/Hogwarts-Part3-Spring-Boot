package edu.hogwarts;

import edu.hogwarts.model.Course;
import edu.hogwarts.model.House;
import edu.hogwarts.model.Student;
import edu.hogwarts.model.Teacher;
import edu.hogwarts.repository.CourseRepository;
import edu.hogwarts.repository.StudentRepository;
import edu.hogwarts.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CourseRepository courseRepository;

    public InitData(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Houses
        House gryffindor = new House();
        gryffindor.setName("Gryffindor");

        House hufflepuff = new House();
        hufflepuff.setName("HufflePuff");

        House ravenclaw = new House();
        ravenclaw.setName("Ravenclaw");

        House slytherin = new House();
        slytherin.setName("Slytherin");

        //Students
        Set<Student> students = new HashSet<>();

        Student harry = new Student();
        harry.setFirstName("Harry");
        harry.setLastName("Potter");
        harry.setHouse(gryffindor);
        studentRepository.save(harry);
        students.add(harry);

        Student hermione = new Student();
        hermione.setFirstName("Hermione");
        hermione.setLastName("Granger");
        hermione.setHouse(gryffindor);
        studentRepository.save(hermione);
        students.add(hermione);

        Student ron = new Student();
        ron.setFirstName("Ronald");
        ron.setLastName("Weasley");
        ron.setHouse(gryffindor);
        studentRepository.save(ron);
        students.add(ron);

        Student hannah = new Student();
        hannah.setFirstName("Hannah");
        hannah.setLastName("Abbott");
        hannah.setHouse(hufflepuff);
        studentRepository.save(hannah);
        students.add(hannah);

        Student susan = new Student();
        susan.setFirstName("Susan");
        susan.setLastName("Bones");
        susan.setHouse(hufflepuff);
        studentRepository.save(susan);
        students.add(susan);

        Student justin = new Student();
        justin.setFirstName("Justin");
        justin.setLastName("Finch-Fletchley");
        justin.setHouse(hufflepuff);
        studentRepository.save(justin);
        students.add(justin);

        Student mandy = new Student();
        mandy.setFirstName("Mandy");
        mandy.setLastName("Brocklehurst");
        mandy.setHouse(ravenclaw);
        studentRepository.save(mandy);
        students.add(mandy);

        Student terry = new Student();
        terry.setFirstName("Terry");
        terry.setLastName("Boot");
        terry.setHouse(ravenclaw);
        studentRepository.save(terry);
        students.add(terry);

        Student michael = new Student();
        michael.setFirstName("Michael");
        michael.setLastName("Corner");
        michael.setHouse(ravenclaw);
        studentRepository.save(michael);
        students.add(michael);

        Student draco = new Student();
        draco.setFirstName("Draco");
        draco.setLastName("Malfoy");
        draco.setHouse(slytherin);
        studentRepository.save(draco);
        students.add(draco);

        Student millicent = new Student();
        millicent.setFirstName("Millicent");
        millicent.setLastName("Bulstrode");
        millicent.setHouse(slytherin);
        studentRepository.save(millicent);
        students.add(millicent);

        Student tracey = new Student();
        tracey.setFirstName("Tracey");
        tracey.setLastName("Davis");
        tracey.setHouse(slytherin);
        studentRepository.save(tracey);
        students.add(tracey);

        Teacher teacher = new Teacher();
        teacher.setFirstName("Minerva");
        teacher.setLastName("McGonagall");
        teacher.setHouse(gryffindor);
        teacherRepository.save(teacher);

        Course course = new Course();
        course.setTeacher(teacher);
        course.setStudents(students);
        courseRepository.save(course);
    }
}
