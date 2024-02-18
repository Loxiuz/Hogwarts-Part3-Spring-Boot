package edu.hogwarts;

import edu.hogwarts.model.InitData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class StudentAdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(StudentAdminApplication.class, args);


    }

    @Component
    public static class AppStartUpData{
        private final InitData initData;

        public AppStartUpData(InitData initData) {
            this.initData = initData;
        }

        @EventListener(ApplicationReadyEvent.class)
        public void init(){
            initData.initData();
        }
    }
}
