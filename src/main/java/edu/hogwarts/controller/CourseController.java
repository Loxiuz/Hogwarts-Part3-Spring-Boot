package edu.hogwarts.controller;

import edu.hogwarts.repository.CourseRepository;

public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

}
