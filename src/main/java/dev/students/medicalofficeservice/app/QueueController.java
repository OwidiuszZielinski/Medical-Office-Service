package dev.students.medicalofficeservice.app;

import dev.students.medicalofficeservice.core.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/queue/")
public class QueueController {
    private final PatientService patientService;

    @GetMapping("{doctorId}")
    public ResponseEntity<?> getLongestWaitingPatient(@PathVariable Long doctorId){
        return new ResponseEntity<>(patientService.getLongestWaitingPatient(doctorId), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getQueue(){
        return new ResponseEntity<>(patientService.getQueue(),HttpStatus.OK);
    }

}
