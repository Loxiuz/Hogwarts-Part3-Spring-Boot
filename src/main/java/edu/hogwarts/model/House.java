package edu.hogwarts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class House {
    @Id
    private String name;
    private String founder;
    @ElementCollection
    @CollectionTable(name = "house_colors", joinColumns = @JoinColumn(name = "house"))
    private List<String> colors;

    public House(String name, String founder, List<String> colors) {
        this.name = name;
        this.founder = founder;
        this.colors = colors;
    }
}
