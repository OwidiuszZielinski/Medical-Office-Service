package dev.students.medicalofficeservice.core.doctor;

import dev.students.medicalofficeservice.core.doctor.dto.DoctorDTO;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorPostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorRepository repository;

    public DoctorPostDTO addDoctor(DoctorPostDTO doctor){
        Doctor doctorToSave = DoctorPostDTO.from(doctor);
        doctorToSave.setWorkDays(new ArrayList<>());
        repository.save(doctorToSave);
        return doctor;
    }

    public List<DoctorDTO> getDoctors(){
        return DoctorDTO.from(repository.findAll());
    }

}
