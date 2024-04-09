package edu.hogwarts.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String founder;
    @ElementCollection
    @CollectionTable(name = "house_colors", joinColumns = @JoinColumn(name = "house_id"))
    private List<String> color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> colors) {
        this.color = colors;
    }
}
