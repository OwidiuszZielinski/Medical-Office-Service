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
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "doctor_schedule",
            joinColumns = @JoinColumn(name = "schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    @JsonBackReference
    private List<Doctor> doctors = new ArrayList<>();
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<WorkHour> hours = new ArrayList<>();

}