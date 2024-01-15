package dev.students.medicalofficeservice.core.doctor.dto;

import dev.students.medicalofficeservice.core.doctor.Doctor;
import dev.students.medicalofficeservice.core.schedule.WorkDay;
import lombok.Builder;

import java.util.List;

@Builder
public record DoctorPostDTO(Long Id,
                            String name,
                            String surname
) {

    public static Doctor from(DoctorPostDTO doctor) {
        return Doctor.builder()
                .Id(doctor.Id())
                .name(doctor.name())
                .surname(doctor.surname())
                .build();

    }
}
