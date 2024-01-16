package dev.students.medicalofficeservice.core.patient;

import dev.students.medicalofficeservice.core.patient.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;


    public PatientDTO getPatient(Long personalIdentityNumber) {
        return Patient.from(patientRepository.findByPersonalIdentityNumber(personalIdentityNumber));
    }

    public List<PatientDTO> getPatients() {
        return PatientDTO.fromAll(patientRepository.findAll());
    }

    public CreatePatientDTO addPatient(CreatePatientDTO patient) {
        Patient patientToSave = CreatePatientDTO.from(patient);
        patientToSave.setTicketNumber(generateTicketNumber());
        patientRepository.save(patientToSave);
        return patient;
    }

    public PatientDTO updatePatientName(Long id, PatientUpdateNameDTO patientDTO) {
        Patient patientToUpdate = getPatientToUpdate(id);
        patientToUpdate.setName(patientDTO.name());
        patientRepository.save(patientToUpdate);
        return Patient.from(patientToUpdate);
    }

    public PatientDTO updatePatientSurname(Long id, PatientUpdateSurnameDTO patientDTO) {
        Patient patientToUpdate = getPatientToUpdate(id);
        patientToUpdate.setSurname(patientDTO.surname());
        patientRepository.save(patientToUpdate);
        return Patient.from(patientRepository.save(patientToUpdate));
    }

    public PatientDTO updatePatientTicketNumber(Long id, PatientUpdateTicketNumberDTO patientDTO) {
        Patient patientToUpdate = getPatientToUpdate(id);
        patientToUpdate.setTicketNumber(patientDTO.ticketNumber());
        patientRepository.save(patientToUpdate);
        return Patient.from(patientRepository.save(patientToUpdate));
    }

    public PatientDTO updatePatientVisitTime(Long id, PatientUpdateVisitTimeDTO patientDTO) {
        Patient patientToUpdate = getPatientToUpdate(id);
        patientToUpdate.setVisitHour(patientDTO.visitHour());
        patientToUpdate.setVisitMinute(patientDTO.visitMinute());
        patientRepository.save(patientToUpdate);
        return Patient.from(patientRepository.save(patientToUpdate));
    }

    public PatientDTO updatePatientPersonalIdentityNumber(Long id, PatientUpdatePersonalIdentityNumberDTO patientDTO) {
        Patient patientToUpdate = getPatientToUpdate(id);
        patientToUpdate.setPersonalIdentityNumber(patientDTO.personalIdentityNumber());
        patientRepository.save(patientToUpdate);
        return Patient.from(patientRepository.save(patientToUpdate));
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
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
}
