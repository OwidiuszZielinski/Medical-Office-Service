package dev.students.medicalofficeservice.core.schedule;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.students.medicalofficeservice.core.doctor.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WorkDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private Doctor doctor;
    @OneToMany(mappedBy = "workDay", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WorkHour> hours = new ArrayList<>();

}