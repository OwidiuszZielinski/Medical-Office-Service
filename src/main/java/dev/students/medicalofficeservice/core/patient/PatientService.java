package dev.students.medicalofficeservice.core.patient;

import dev.students.medicalofficeservice.core.doctor.DoctorService;
import dev.students.medicalofficeservice.core.patient.dto.AcceptPatientDTO;
import dev.students.medicalofficeservice.core.patient.dto.CreatePatientDTO;
import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import dev.students.medicalofficeservice.core.patient.dto.PatientUpdateVisitTimeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final DoctorService doctorService;
    private final PatientQueueRepository patientQueueRepository;
    private final PatientCleanupService patientCleanupService;

    public PatientDTO getPatient(Long personalIdentityNumber) {
        return Patient.from(patientRepository.findByPersonalIdentityNumber(personalIdentityNumber));
    }

    public List<PatientDTO> getPatients() {
        return PatientDTO.fromAll(patientRepository.findAll());
    }

    public CreatePatientDTO registerPatient(CreatePatientDTO patient) {
        Patient patientToSave = CreatePatientDTO.from(patient);
        patientToSave.setTicketNumber(generateTicketNumber());
        patientRepository.save(patientToSave);
        return patient;
    }

    public PatientDTO updatePatientName(Long id, String name) {
        Patient patientToUpdate = getPatientToUpdate(id);
        patientToUpdate.setName(name);
        patientRepository.save(patientToUpdate);
        return Patient.from(patientToUpdate);
    }

    public PatientDTO updatePatientSurname(Long id, String surname) {
        Patient patientToUpdate = getPatientToUpdate(id);
        patientToUpdate.setSurname(surname);
        patientRepository.save(patientToUpdate);
        return Patient.from(patientToUpdate);
    }

    public PatientDTO updatePatientTicketNumber(Long id, Integer ticketNumber) {
        Patient patientToUpdate = getPatientToUpdate(id);
        patientToUpdate.setTicketNumber(ticketNumber);
        patientRepository.save(patientToUpdate);
        return Patient.from(patientToUpdate);
    }

    public PatientDTO updatePatientVisitTime(Long id, PatientUpdateVisitTimeDTO patientDTO) {
        Patient patientToUpdate = getPatientToUpdate(id);
        patientToUpdate.setVisitHour(patientDTO.visitHour());
        patientToUpdate.setVisitMinute(patientDTO.visitMinute());
        patientRepository.save(patientToUpdate);
        return Patient.from(patientToUpdate);
    }

    public PatientDTO updatePatientPersonalIdentityNumber(Long id, Long personalIdentityNumber) {
        Patient patientToUpdate = getPatientToUpdate(id);
        patientToUpdate.setPersonalIdentityNumber(personalIdentityNumber);
        patientRepository.save(patientToUpdate);
        return Patient.from(patientToUpdate);
    }

    public void deletePatient(Long pin) {
        Patient byPersonalIdentityNumber = patientRepository.findByPersonalIdentityNumber(pin);
        patientRepository.delete(byPersonalIdentityNumber);

    }

    public void deletePatients() {
        patientRepository.deleteAll(patientRepository.findAll());
    }

    private Patient getPatientToUpdate(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pacjent nie znaleziony"));
    }

    private Integer generateTicketNumber() {
        Integer ticketNumber = patientRepository.findAll()
                .stream()
                .map(Patient::getTicketNumber)
                .max(Integer::compareTo)
                .orElse(1);
        return ticketNumber + 1;
    }

    public AcceptPatientDTO getLongestWaitingPatient(Long doctorId) {
        Patient patient = getPatient();
        Integer officeNumber = doctorService.getDoctor(doctorId).officeNumber();
        saveQueue(officeNumber, patient.getTicketNumber());
        patient.setAccepted(true);
        patientRepository.save(patient);
        patientCleanupService.schedulePatientRemoval(patient.getTicketNumber(), 60000);
        return new AcceptPatientDTO(patient.getTicketNumber(), officeNumber);
    }

    private void saveQueue(Integer officeNumber, Integer ticketNumber) {
        patientQueueRepository.save(PatientQueue.builder()
                .doctorsOfficeNumber(officeNumber)
                .ticketNumber(ticketNumber).build());
    }


    private Patient getPatient() {
        return patientRepository.findAll().stream()
                .filter(patient -> !patient.isAccepted())
                .min(Comparator.comparing(Patient::getTicketNumber))
                .orElseThrow(() -> new NoSuchElementException("Not found"));
    }
    public List<PatientQueue> getQueue(){
        return patientQueueRepository.findAll();
    }

}
