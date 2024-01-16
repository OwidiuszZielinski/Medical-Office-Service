package dev.students.medicalofficeservice.core.doctor.dto;

import dev.students.medicalofficeservice.core.doctor.Doctor;
import dev.students.medicalofficeservice.core.schedule.Schedule;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record DoctorDTO(Long id, String name, String surname, List<Schedule> schedules) {

    public static DoctorDTO from(Doctor doctor) {
        return DoctorDTO.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .surname(doctor.getSurname())
                .schedules(doctor.getSchedules())
                .build();

    }

    public static List<DoctorDTO> from(List<Doctor> doctors) {
        return doctors.stream()
                .map(DoctorDTO::from)
                .collect(Collectors.toList());
    }

}
