package dev.students.medicalofficeservice.core.patient;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PatientCleanupService {
    private final PatientQueueRepository patientQueueRepository;

    @Async
    public void schedulePatientRemoval(Integer ticketNumber, long delay) {
        try {
            Thread.sleep(delay);
            removePatientByTicketNumber(ticketNumber);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        }
    }

    private void removePatientByTicketNumber(Integer ticketNumber) {
        patientQueueRepository.findByTicketNumber(ticketNumber)
                .ifPresent(patientQueueRepository::delete);
    }
}
