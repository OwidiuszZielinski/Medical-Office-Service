package dev.students.medicalofficeservice.core.patient.dto;

import dev.students.medicalofficeservice.core.patient.Patient;
import lombok.Builder;

@Builder
public record PatientDTO(Long id,String name,Long pNo) {

    public static Patient from(PatientDTO patientDTO){
        return Patient.builder()
                .id(patientDTO.id)
                .name(patientDTO.name)
                .pNo(patientDTO.pNo())
                .build();
    }
}
