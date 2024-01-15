package dev.students.medicalofficeservice.core.doctor;


import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorDTO;
import dev.students.medicalofficeservice.core.schedule.WorkDay;
import dev.students.medicalofficeservice.core.schedule.dto.WorkDayDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Doctor {
    @Id
    private Long Id;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<WorkDay> workDays;

    public static DoctorDTO from(Doctor doctor){
        return  DoctorDTO.builder()
                .Id(doctor.Id)
                .name(doctor.name)
                .surname(doctor.surname)
                .workDays(WorkDayDTO.from(doctor.workDays))
                .build();

    }
}

