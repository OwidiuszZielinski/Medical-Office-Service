package dev.students.medicalofficeservice.core.patient;

import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientDTO getPatient(Long personalIdentityNumber){
        return Patient.from(patientRepository.findByPersonalIdentityNumber(personalIdentityNumber));
    }
    public PatientDTO addPatient(PatientDTO patient){
        patientRepository.save(PatientDTO.from(patient));
        return patient;
    }

    public List<PatientDTO> getPatients() {
        return PatientDTO.fromAll(patientRepository.findAll());
    }
}
