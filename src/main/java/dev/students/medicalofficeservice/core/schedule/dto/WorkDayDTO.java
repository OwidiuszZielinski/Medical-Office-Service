package dev.students.medicalofficeservice.core.schedule.dto;

import dev.students.medicalofficeservice.core.schedule.WorkDay;
import dev.students.medicalofficeservice.core.schedule.WorkHour;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record WorkDayDTO(Long id,
                         LocalDate date, List<WorkHour> hours) {

    public static WorkDayDTO from(WorkDay workDay){
        return WorkDayDTO.builder().id(workDay.getId())
                .date(workDay.getDate())
                .hours(workDay.getHours())
                .build();
    }

    public static List<WorkDayDTO> from(List<WorkDay> list){
        return list.stream().map(WorkDayDTO::from).collect(Collectors.toList());
    }
}
