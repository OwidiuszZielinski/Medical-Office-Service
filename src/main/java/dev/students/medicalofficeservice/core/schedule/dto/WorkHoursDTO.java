package dev.students.medicalofficeservice.core.schedule.dto;



import java.time.LocalDate;
import java.time.LocalTime;


public record WorkHoursDTO(Long doctorId, LocalDate date, Integer startHour,Integer startMinute, Integer endHour,Integer endMinute) {

}
