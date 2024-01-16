package dev.students.medicalofficeservice.core.patient.dto;

import dev.students.medicalofficeservice.core.patient.Patient;
import lombok.Builder;

@Builder
public record CreatePatientDTO(
        String name,
        String surname,
        Long personalIdentityNumber,
        Integer visitHour,
        Integer visitMinute
) {
    public static Patient from(CreatePatientDTO patient) {
        return Patient.builder()
                .name(patient.name)
                .surname(patient.surname)
                .visitHour(patient.visitHour())
                .visitMinute(patient.visitMinute())
                .personalIdentityNumber(patient.personalIdentityNumber())
                .build();

    }
}
