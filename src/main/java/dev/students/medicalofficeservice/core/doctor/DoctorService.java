package dev.students.medicalofficeservice.core.doctor;

import dev.students.medicalofficeservice.core.doctor.dto.DoctorDTO;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorPostDTO;
import dev.students.medicalofficeservice.core.patient.Patient;
import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class DoctorService {
    private final DoctorRepository repository;

    public DoctorPostDTO addDoctor(DoctorPostDTO doctor){
        Doctor doctorToSave = DoctorPostDTO.from(doctor);
        doctorToSave.setSchedules(new ArrayList<>());
        repository.save(doctorToSave);
        return doctor;
    }

    public List<DoctorDTO> getDoctors(){
        return DoctorDTO.from(repository.findAll());
    }
    public DoctorDTO getDoctor(Long id){
        return DoctorDTO.from(repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found")));
    }

    public DoctorDTO updateDoctorName(Long id, String name) {
        Doctor doctorToUpdate = getDoctorToUpdate(id);
        doctorToUpdate.setName(name);
        repository.save(doctorToUpdate);
        return Doctor.from(doctorToUpdate);
    }

    public DoctorDTO updateDoctorSurname(Long id, String surname) {
        Doctor doctorToUpdate = getDoctorToUpdate(id);
        doctorToUpdate.setSurname(surname);
        repository.save(doctorToUpdate);
        return Doctor.from(doctorToUpdate);
    }

    public void deleteDoctor(Long id){
        repository.deleteById(id);
    }

    private Doctor getDoctorToUpdate(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

}
