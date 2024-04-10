package edu.hogwarts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.hogwarts.model.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String house;
    private boolean prefect;
    private int enrollmentYear;
    private int graduationYear;
    private boolean graduated;
    public StudentDto(Student s){
        this.firstName = s.getFirstName();
        this.middleName = s.getMiddleName();
        this.lastName = s.getLastName();
        this.house = s.getHouse().getName();
        this.prefect = s.isPrefect();
        this.enrollmentYear = s.getEnrollmentYear();
        this.graduationYear = s.getGraduationYear();
        this.graduated = s.isGraduated();

    }

}
