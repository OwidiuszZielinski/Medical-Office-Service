package dev.students.medicalofficeservice.core.doctor;


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
    private List<Schedule> schedules = new ArrayList<>();


}

