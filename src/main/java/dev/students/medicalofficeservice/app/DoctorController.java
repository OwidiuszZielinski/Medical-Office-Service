package dev.students.medicalofficeservice.app;

import dev.students.medicalofficeservice.core.doctor.DoctorService;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorDTO;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorPostDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/doctors/")
public class DoctorController {

    private final DoctorService doctorService;


    @GetMapping

    ResponseEntity<List<DoctorDTO>> getDoctors(){
        return new ResponseEntity<>(doctorService.getDoctors(),HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<DoctorPostDTO> addDoctor(DoctorPostDTO doctor) {
        DoctorPostDTO doctorToAdd = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(doctorToAdd, HttpStatus.CREATED);
    }

}
