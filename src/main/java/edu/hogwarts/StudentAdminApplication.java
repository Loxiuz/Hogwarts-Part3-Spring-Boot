package edu.hogwarts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class StudentAdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(StudentAdminApplication.class, args);
    }
}
