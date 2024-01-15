package dev.students.medicalofficeservice.app;

import dev.students.medicalofficeservice.core.schedule.ScheduleService;
import dev.students.medicalofficeservice.core.schedule.WorkDay;
import dev.students.medicalofficeservice.core.schedule.WorkHours;
import dev.students.medicalofficeservice.core.schedule.dto.WorkHoursDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule/")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<WorkHoursDTO> setDoctorWorkHours(@RequestBody WorkHoursDTO hours){
        WorkHoursDTO workHoursToSet = scheduleService.setWorkHours(hours);
        return new ResponseEntity<>(workHoursToSet, HttpStatus.ACCEPTED);

    }

}
