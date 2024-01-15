package dev.students.medicalofficeservice.core.patient.dto;

import dev.students.medicalofficeservice.core.patient.Patient;
import dev.students.medicalofficeservice.core.ticket.Ticket;
import lombok.Builder;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public record PatientDTO(Long id,
                         String name,
                         Long personalIdentityNumber,
                         String surname, LocalTime visitTime,
                         Ticket ticket) {

    public static Patient from(PatientDTO patientDTO) {
        return Patient.builder()
                .id(patientDTO.id)
                .name(patientDTO.name)
                .surname(patientDTO.surname)
                .visitTime(patientDTO.visitTime)
                .personalIdentityNumber(patientDTO.personalIdentityNumber())
                .ticket(patientDTO.ticket)
                .build();
    }

    public static List<PatientDTO> fromAll(List<Patient> all) {
        return all.stream().map(Patient::from).collect(Collectors.toList());
    }
}
