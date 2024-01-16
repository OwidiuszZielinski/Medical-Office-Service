package dev.students.medicalofficeservice.app;

import dev.students.medicalofficeservice.core.doctor.DoctorService;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorDTO;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorPostDTO;
import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors/")
public class DoctorController {

    private final DoctorService doctorService;


    @GetMapping
    public ResponseEntity<?> getDoctors(){
        return new ResponseEntity<>(doctorService.getDoctors(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable Long id){
        return new ResponseEntity<>(doctorService.getDoctor(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addDoctor(@RequestBody DoctorPostDTO doctor) {
        DoctorPostDTO doctorToAdd = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(doctorToAdd, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<?> updateDoctorName(@PathVariable Long id, @RequestBody String name) {
        DoctorDTO updateDoctor = doctorService.updateDoctorName(id, name);
        return new ResponseEntity<>(updateDoctor,HttpStatus.OK);
    }

    @PatchMapping("/{id}/surname")
    public ResponseEntity<?> updateDoctorSurname(@PathVariable Long id, @RequestBody String surname) {
        DoctorDTO updateDoctor = doctorService.updateDoctorSurname(id, surname);
        return new ResponseEntity<>(updateDoctor,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/{id}/office")
    public ResponseEntity<?> setDoctorOffice(@PathVariable Long id, @RequestBody Integer officeId) {
        DoctorDTO updateDoctor = doctorService.setDoctorOfficeNumber(id, officeId);
        return new ResponseEntity<>(updateDoctor,HttpStatus.OK);
    }


}
