package dev.students.medicalofficeservice.app;


import dev.students.medicalofficeservice.core.patient.PatientService;
import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patients/")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    ResponseEntity<PatientDTO> getPatient(Long pNo){
        PatientDTO patient = patientService.getPatient(pNo);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<PatientDTO> addPatient(PatientDTO patient){
        PatientDTO patientToAdd = patientService.addPatient(patient);
        return new ResponseEntity<>(patientToAdd,HttpStatus.CREATED);
    }

}
