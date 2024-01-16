package dev.students.medicalofficeservice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@SpringBootApplication
public class MedicalOfficeServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicalOfficeServiceApplication.class, args);

    }

}
