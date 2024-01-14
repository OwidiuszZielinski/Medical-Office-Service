package dev.students.medicalofficeservice.core.patient;

import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    public PatientDTO getPatient(Long pNo){
        return new PatientDTO("Owdiusz",999999999L);
    }

}
