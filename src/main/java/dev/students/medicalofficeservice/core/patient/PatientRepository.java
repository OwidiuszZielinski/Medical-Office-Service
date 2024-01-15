package dev.students.medicalofficeservice.core.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    @Query("select p from Patient p where p.personalIdentityNumber = ?1")
    Patient findByPersonalIdentityNumber(Long personalIdentityNumber);

}
