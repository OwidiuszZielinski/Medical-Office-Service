package dev.students.medicalofficeservice.app;

import dev.students.medicalofficeservice.core.schedule.ScheduleService;
import dev.students.medicalofficeservice.core.schedule.dto.ScheduleDTO;
import dev.students.medicalofficeservice.core.schedule.dto.WorkHourDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule/")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<WorkHourDTO> setDoctorWorkHours(@RequestBody WorkHourDTO hours){
        WorkHourDTO workHoursToSet = scheduleService.setWorkHours(hours);
        return new ResponseEntity<>(workHoursToSet, HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{doctorId}/date")
    public ResponseEntity<?> deleteDoctorWorkHoursInDay(@PathVariable Long doctorId, @RequestBody LocalDate date){
        scheduleService.removeWorkHours(doctorId, date);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}/")
    public ResponseEntity<?> deleteDoctorWorkHoursLastMonth(@PathVariable Long doctorId){
        scheduleService.removeAllWorkHoursFromLastMonth(doctorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getSchedule(){
        List<ScheduleDTO> schedule = scheduleService.getSchedule();
        return new ResponseEntity<>(schedule,HttpStatus.OK);
    }

}
