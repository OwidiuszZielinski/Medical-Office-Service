package dev.students.medicalofficeservice.core.patient.dto;

import dev.students.medicalofficeservice.core.patient.Patient;
import lombok.Builder;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record PatientDTO(Long id,
                         String name,
                         Long personalIdentityNumber,
                         String surname,
                         Integer visitHour,
                         Integer visitMinute,
                         Integer ticketNumber) {

    public static Patient from(PatientDTO patientDTO) {
        return Patient.builder()
                .id(patientDTO.id())
                .name(patientDTO.name())
                .surname(patientDTO.surname())
                .visitHour(patientDTO.visitHour())
                .visitMinute(patientDTO.visitMinute())
                .personalIdentityNumber(patientDTO.personalIdentityNumber())
                .ticketNumber(patientDTO.ticketNumber())
                .build();
    }

    public static List<PatientDTO> fromAll(List<Patient> all) {
        return all.stream().map(Patient::from).collect(Collectors.toList());
    }
}
