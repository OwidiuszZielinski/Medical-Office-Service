package dev.students.medicalofficeservice.core.schedule.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record WorkHourDTO(Long doctorId, LocalDate date, Integer startHour, Integer startMinute, Integer endHour, Integer endMinute) {

}
