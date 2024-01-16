package dev.students.medicalofficeservice.core.patient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientQueueRepository extends JpaRepository<PatientQueue,Long> {
}
