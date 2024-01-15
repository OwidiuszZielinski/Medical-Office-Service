package dev.students.medicalofficeservice.core.doctor;

import dev.students.medicalofficeservice.core.doctor.dto.DoctorDTO;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorRepository repository;

    public DoctorPostDTO addDoctor(DoctorPostDTO doctor){
        repository.save(DoctorPostDTO.from(doctor));
        return doctor;
    }

    public List<DoctorDTO> getDoctors(){
        return repository.findAll().stream().map(Doctor::from).collect(Collectors.toList());
    }

}
