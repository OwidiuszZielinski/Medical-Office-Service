package dev.students.medicalofficeservice.core.patient;

import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import dev.students.medicalofficeservice.core.ticket.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Long personalIdentityNumber;
    private LocalTime visitTime;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Ticket ticket;

    public static PatientDTO from(Patient patient){
        return PatientDTO.builder()
                .id(patient.id)
                .name(patient.name)
                .personalIdentityNumber(patient.personalIdentityNumber)
                .visitTime(patient.visitTime)
                .surname(patient.surname)
                .ticket(patient.ticket)
                .build();
    }


}
