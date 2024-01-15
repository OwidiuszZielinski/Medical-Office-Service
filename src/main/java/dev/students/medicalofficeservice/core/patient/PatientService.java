package dev.students.medicalofficeservice.core.patient;

import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientDTO getPatient(Long pNo){
        return new PatientDTO(1L,"Owdiusz",999999999L);
    }
    public PatientDTO addPatient(PatientDTO patient){
        patientRepository.save(PatientDTO.from(patient));
        return patient;
    }

}
