package dev.students.medicalofficeservice.core.schedule.dto;



import java.time.LocalDate;


public record WorkHourDTO(Long doctorId, LocalDate date, Integer startHour, Integer startMinute, Integer endHour, Integer endMinute) {

}
