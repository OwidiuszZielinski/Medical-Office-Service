package dev.students.medicalofficeservice.core.doctor.dto;
import dev.students.medicalofficeservice.core.doctor.Doctor;
import dev.students.medicalofficeservice.core.schedule.WorkDay;
import dev.students.medicalofficeservice.core.schedule.WorkHours;
import dev.students.medicalofficeservice.core.schedule.dto.WorkDayDTO;
import lombok.Builder;

import java.util.List;
@Builder
public record DoctorDTO(Long Id,
        String name,
        String surname, List<WorkDayDTO> workDays
        ) {
    public static Doctor from(DoctorPostDTO doctor) {
        return Doctor.builder()
                .Id(doctor.Id())
                .name(doctor.name())
                .surname(doctor.surname())
                .build();
    }
}
