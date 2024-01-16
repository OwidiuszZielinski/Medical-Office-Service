package dev.students.medicalofficeservice.core.patient;

import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Long personalIdentityNumber;
    private Integer visitHour;
    private Integer visitMinute;
    private Integer ticketNumber;
    private boolean accepted;

    public static PatientDTO from(Patient patient) {
        return PatientDTO.builder()
                .id(patient.id)
                .name(patient.name)
                .personalIdentityNumber(patient.personalIdentityNumber)
                .visitHour(patient.visitHour)
                .visitMinute(patient.visitMinute)
                .surname(patient.surname)
                .ticketNumber(patient.ticketNumber)
                .accepted(patient.accepted)
                .build();
    }
}
