package dev.students.medicalofficeservice.core.doctor;


import dev.students.medicalofficeservice.core.doctor.dto.DoctorDTO;
import dev.students.medicalofficeservice.core.schedule.Schedule;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String surname;
    @ManyToMany(mappedBy = "doctors" ,fetch = FetchType.EAGER)
    @Builder.Default
    private List<Schedule> schedules = new ArrayList<>();


    public static DoctorDTO from(Doctor doctor) {
        return DoctorDTO.builder()
                .id(doctor.Id)
                .name(doctor.name)
                .surname(doctor.surname)
                .schedules(doctor.schedules)
                .build();
    }
}

