package dev.students.medicalofficeservice.core.doctor.dto;

import dev.students.medicalofficeservice.core.doctor.Doctor;
import dev.students.medicalofficeservice.core.schedule.WorkDay;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DoctorDTO(Long id, String name, String surname, List<WorkDay> workDays) {

    public static DoctorDTO from(Doctor doctor) {
        return DoctorDTO.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .workDays(doctor.getWorkDays())
                .build();

    }

    public static List<DoctorDTO> from(List<Doctor> doctors) {
        return doctors.stream()
                .map(DoctorDTO::from)
                .collect(Collectors.toList());
    }

}
