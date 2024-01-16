package dev.students.medicalofficeservice.app;


import dev.students.medicalofficeservice.core.patient.PatientService;
import dev.students.medicalofficeservice.core.patient.dto.*;
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
    public ResponseEntity<?> getPatient(@PathVariable Long pNo){
        PatientDTO patient = patientService.getPatient(pNo);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getPatients(){
        return new ResponseEntity<>(patientService.getPatients(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPatient(@RequestBody CreatePatientDTO patient){
        CreatePatientDTO patientToAdd = patientService.addPatient(patient);
        return new ResponseEntity<>(patientToAdd,HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<?> updatePatientName(@PathVariable Long id, @RequestBody String name) {
        PatientDTO updatedPatient = patientService.updatePatientName(id, name);
        return new ResponseEntity<>(updatedPatient,HttpStatus.OK);
    }

    @PatchMapping("/{id}/surname")
    public ResponseEntity<?> updatePatientSurname(@PathVariable Long id, @RequestBody String surname) {
        PatientDTO updatedPatient = patientService.updatePatientSurname(id, surname);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @PatchMapping("/{id}/ticket-number")
    public ResponseEntity<?> updatePatientTicketNumber(@PathVariable Long id, @RequestBody Integer ticketNumber) {
        PatientDTO updatedPatient = patientService.updatePatientTicketNumber(id, ticketNumber);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @PatchMapping("/{id}/visit-time")
    public ResponseEntity<?> updatePatientVisitTime(@PathVariable Long id, @RequestBody PatientUpdateVisitTimeDTO visitTime) {
        PatientDTO updatedPatient = patientService.updatePatientVisitTime(id, visitTime);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @PatchMapping("/{id}/personal-identity-number")
    public ResponseEntity<?> updatePatientPersonalIdentityNumber(@PathVariable Long id, @RequestBody Long personalIdentityNumber) {
        PatientDTO updatedPatient = patientService.updatePatientPersonalIdentityNumber(id, personalIdentityNumber);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePatients(){
        patientService.deletePatients();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
