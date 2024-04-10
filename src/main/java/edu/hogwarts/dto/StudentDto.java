package edu.hogwarts.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public static StudentDto fromFullName(@JsonProperty("name") String fullName){
        String[] names = fullName.split(" ");
        StudentDto studentDto = new StudentDto();
        switch (names.length) {
            case 3:
                studentDto.setFirstName(names[0]);
                studentDto.setMiddleName(names[1]);
                studentDto.setLastName(names[2]);
                break;
            case 2:
                studentDto.setFirstName(names[0]);
                studentDto.setLastName(names[1]);
                break;
            case 1:
                studentDto.setFirstName(names[0]);
                break;
            default:
                throw new IllegalArgumentException("Invalid format for full name.");
        }
        return studentDto;
    }
}
