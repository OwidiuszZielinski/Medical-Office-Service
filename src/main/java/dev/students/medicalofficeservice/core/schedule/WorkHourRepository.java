package dev.students.medicalofficeservice.core.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour,Long> {
}
