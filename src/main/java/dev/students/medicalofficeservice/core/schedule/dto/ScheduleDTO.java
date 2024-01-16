package dev.students.medicalofficeservice.core.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ScheduleDTO {
    private LocalDate date;
    private List<DoctorSchedule> doctorSchedules;

    // Konstruktor, gettery, settery

    @Getter
    @Setter
    @AllArgsConstructor
    public static class DoctorSchedule {
        private String doctorName;
        private List<WorkHourInfo> workHours;

        // Konstruktor, gettery, settery

        @Getter
        @Setter
        @AllArgsConstructor
        public static class WorkHourInfo {
            private LocalTime startTime;
            private LocalTime endTime;

            // Konstruktor, gettery, settery
        }
    }
}