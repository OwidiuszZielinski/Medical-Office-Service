package dev.students.medicalofficeservice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import java.io.File;

@Slf4j
@EnableAsync
@SpringBootApplication
public class MedicalOfficeServiceApplication {
    public static void main(String[] args) {
        File logFile = new File("logs/myapp.log");
        if (logFile.exists()) {
            boolean delete = logFile.delete();
            if (delete) {
                log.info("Log File 'myapp.log' deleted");
            } else {
                log.warn("Failed to delete log file 'myapp.log'.");
            }
        }
        SpringApplication.run(MedicalOfficeServiceApplication.class, args);
    }
}
