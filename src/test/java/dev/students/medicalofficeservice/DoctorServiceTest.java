package dev.students.medicalofficeservice;
import dev.students.medicalofficeservice.core.doctor.Doctor;
import dev.students.medicalofficeservice.core.doctor.DoctorRepository;
import dev.students.medicalofficeservice.core.doctor.DoctorService;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorDTO;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorPostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Optional;

@SpringBootTest
public class DoctorServiceTest {

    private DoctorService doctorService;
    private DoctorRepository doctorRepository;

    @BeforeEach
    void setUp() {
        doctorRepository = mock(DoctorRepository.class);
        doctorService = new DoctorService(doctorRepository);
    }

    @Test
    void testAddDoctor() {
        // Given
        DoctorPostDTO doctorPostDTO = DoctorPostDTO.builder()
                .Id(1L)
                .name("Dr. Jan Kowalski")
                .surname("Kowalski")
                .build();

        // When
        Doctor savedDoctor = Doctor.builder()
                .Id(1L)
                .name("Dr. Jan Kowalski")
                .surname("Kowalski")
                .build();

        when(doctorRepository.save(any(Doctor.class))).thenReturn(savedDoctor);

        DoctorPostDTO result = doctorService.addDoctor(doctorPostDTO);

        // Then
        assertNotNull(result);
        assertEquals("Dr. Jan Kowalski", result.name());
        assertEquals("Kowalski", result.surname());

        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }
    @Test
    void testGetDoctors() {
        // Given
        List<Doctor> doctorList = Arrays.asList(
                Doctor.builder().Id(1L).name("Dr. Jan Kowalski").surname("Kowalski").build(),
                Doctor.builder().Id(2L).name("Dr. Anna Nowak").surname("Nowak").build()
        );

        when(doctorRepository.findAll()).thenReturn(doctorList);

        // When
        List<DoctorDTO> result = doctorService.getDoctors();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Dr. Jan Kowalski", result.get(0).name());
        assertEquals("Kowalski", result.get(0).surname());
        assertEquals("Dr. Anna Nowak", result.get(1).name());
        assertEquals("Nowak", result.get(1).surname());
    }

    @Test
    void testGetDoctor() {
        // Given
        Long doctorId = 1L;
        Doctor doctor = Doctor.builder().Id(doctorId).name("Dr. Jan Kowalski").surname("Kowalski").build();

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));

        // When
        DoctorDTO result = doctorService.getDoctor(doctorId);

        // Then
        assertNotNull(result);
        assertEquals("Dr. Jan Kowalski", result.name());
        assertEquals("Kowalski", result.surname());
    }

    @Test
    void testGetDoctorNotFound() {
        // Given
        Long doctorId = 1L;

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> doctorService.getDoctor(doctorId));
    }


    @Test
    void testUpdateDoctorName() {
        // Given
        Long doctorId = 1L;
        String updatedName = "Dr. New Name";
        Doctor doctorToUpdate = Doctor.builder().Id(doctorId).name("Dr. Old Name").surname("Surname").build();

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctorToUpdate));

        // When
        DoctorDTO result = doctorService.updateDoctorName(doctorId, updatedName);

        // Then
        assertNotNull(result);
        assertEquals(updatedName, result.name());

        verify(doctorRepository, times(1)).findById(doctorId);
        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }

    @Test
    void testUpdateDoctorNameNotFound() {
        // Given
        Long doctorId = 1L;
        String updatedName = "Dr. New Name";

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> doctorService.updateDoctorName(doctorId, updatedName));
        verify(doctorRepository, times(1)).findById(doctorId);
        verify(doctorRepository, times(0)).save(any(Doctor.class));
    }
    @Test
    void testUpdateDoctorSurname() {
        // Given
        Long doctorId = 1L;
        String updatedSurname = "NewSurname";
        Doctor doctorToUpdate = Doctor.builder().Id(doctorId).name("Dr. Name").surname("OldSurname").build();

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctorToUpdate));

        // When
        DoctorDTO result = doctorService.updateDoctorSurname(doctorId, updatedSurname);

        // Then
        assertNotNull(result);
        assertEquals(updatedSurname, result.surname());

        verify(doctorRepository, times(1)).findById(doctorId);
        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }

    @Test
    void testDeleteDoctor() {
        // Given
        Long doctorId = 1L;

        // When
        doctorService.deleteDoctor(doctorId);

        // Then
        verify(doctorRepository, times(1)).deleteById(doctorId);
    }

    @Test
    void testUpdateDoctorSurnameNotFound() {
        // Given
        Long doctorId = 1L;
        String updatedSurname = "NewSurname";

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> doctorService.updateDoctorSurname(doctorId, updatedSurname));
        verify(doctorRepository, times(1)).findById(doctorId);
        verify(doctorRepository, times(0)).save(any(Doctor.class));
    }
    @Test
    void testSetDoctorOfficeNumber() {
        // Given
        Long doctorId = 1L;
        Integer officeNumber = 101;
        Doctor doctorToUpdate = Doctor.builder().Id(doctorId).name("Dr. Name").surname("Surname").build();

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctorToUpdate));

        // When
        DoctorDTO result = doctorService.setDoctorOfficeNumber(doctorId, officeNumber);

        // Then
        assertNotNull(result);
        assertEquals(officeNumber, result.officeNumber());

        verify(doctorRepository, times(1)).findById(doctorId);
        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }

    @Test
    void testSetDoctorOfficeNumberNotFound() {
        // Given
        Long doctorId = 1L;
        Integer officeNumber = 101;

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> doctorService.setDoctorOfficeNumber(doctorId, officeNumber));
        verify(doctorRepository, times(1)).findById(doctorId);
        verify(doctorRepository, times(0)).save(any(Doctor.class));
    }
    @Test
    void testGetDoctorToUpdate() {
        // Given
        Long doctorId = 1L;
        Doctor expectedDoctor = Doctor.builder().Id(doctorId).name("Dr. Name").surname("Surname").build();

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(expectedDoctor));

        // When
        Doctor result = doctorService.getDoctorToUpdate(doctorId);

        // Then
        assertNotNull(result);
        assertEquals(doctorId, result.getId());
        assertEquals("Dr. Name", result.getName());
        assertEquals("Surname", result.getSurname());

        verify(doctorRepository, times(1)).findById(doctorId);
    }

    @Test
    void testGetDoctorToUpdateNotFound() {
        // Given
        Long doctorId = 1L;

        when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> doctorService.getDoctorToUpdate(doctorId));
        verify(doctorRepository, times(1)).findById(doctorId);
    }
}