package dev.students.medicalofficeservice.app;


import dev.students.medicalofficeservice.core.patient.PatientService;
import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patients/")
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{pNo}")
    ResponseEntity<PatientDTO> getPatient(@PathVariable Long pNo){
        PatientDTO patient = patientService.getPatient(pNo);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping
    ResponseEntity<List<PatientDTO>> getPatients(){
        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<PatientDTO> addPatient(PatientDTO patient){
        PatientDTO patientToAdd = patientService.addPatient(patient);
        return new ResponseEntity<>(patientToAdd,HttpStatus.CREATED);
    }


}
