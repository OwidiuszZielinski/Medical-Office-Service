package dev.students.medicalofficeservice.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller  // Użyj @Controller zamiast @RestController
@RequestMapping("/queue/ui")  // Ustawienie ścieżki
public class QueueUIController {

    @GetMapping
    public String queueUI() {
        return "forward:/queue-ui.html";  // Przekierowanie do pliku HTML
    }
}