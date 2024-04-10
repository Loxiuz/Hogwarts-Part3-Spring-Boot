package edu.hogwarts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.hogwarts.model.House;
import edu.hogwarts.model.Teacher;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDto {

    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String house;
    private boolean headOfHouse;
    private String employment;
    private LocalDate employmentStart;
    private LocalDate employmentEnd;

    public TeacherDto(Teacher t){
        this.id = t.getId();
        this.firstName = t.getFirstName();
        this.middleName = t.getMiddleName();
        this.lastName = t.getLastName();
        this.dateOfBirth = t.getDateOfBirth();
        this.house = t.getHouse().getName();
        this.headOfHouse = t.isHeadOfHouse();
        this.employment = t.getEmployment();
        this.employmentStart = t.getEmploymentStart();
        this.employmentEnd = t.getEmploymentEnd();
    }

}
