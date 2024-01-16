package dev.students.medicalofficeservice.app;

import dev.students.medicalofficeservice.core.doctor.DoctorService;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorDTO;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorPostDTO;
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

    @PostMapping
    public ResponseEntity<?> addDoctor(@RequestBody DoctorPostDTO doctor) {
        DoctorPostDTO doctorToAdd = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(doctorToAdd, HttpStatus.CREATED);
    }

}
