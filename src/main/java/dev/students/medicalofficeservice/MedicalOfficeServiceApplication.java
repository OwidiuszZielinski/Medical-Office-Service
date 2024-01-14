package dev.students.medicalofficeservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class MedicalOfficeServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(MedicalOfficeServiceApplication.class, args);

    }

}
