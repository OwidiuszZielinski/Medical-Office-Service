package dev.students.medicalofficeservice;
import dev.students.medicalofficeservice.core.doctor.Doctor;
import dev.students.medicalofficeservice.core.doctor.DoctorService;
import dev.students.medicalofficeservice.core.doctor.DoctorRepository;
import dev.students.medicalofficeservice.core.doctor.dto.DoctorDTO;
import dev.students.medicalofficeservice.core.patient.Patient;
import dev.students.medicalofficeservice.core.patient.PatientCleanupService;
import dev.students.medicalofficeservice.core.patient.PatientRepository;
import dev.students.medicalofficeservice.core.patient.PatientService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dev.students.medicalofficeservice.core.patient.dto.AcceptPatientDTO;
import dev.students.medicalofficeservice.core.patient.dto.CreatePatientDTO;
import dev.students.medicalofficeservice.core.patient.dto.PatientDTO;
import dev.students.medicalofficeservice.core.patient.dto.PatientUpdateVisitTimeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class PatientServiceTest {

    private PatientService patientService;
    private PatientRepository patientRepository;
    private DoctorRepository doctorRepository;
    private DoctorService doctorService;
    @Mock
    private PatientCleanupService patientCleanupService;

    @BeforeEach
    void setUp() {
        patientRepository = mock(PatientRepository.class);
        patientService = new PatientService(patientRepository, null, null, null);
        doctorService = new DoctorService(doctorRepository);
    }

    @Test
    void testGetPatient() {
        // Given
        Long personalIdentityNumber = 123456789L;
        Patient expectedPatient = Patient.builder()
                .id(1L)
                .name("John")
                .surname("Doe")
                .personalIdentityNumber(personalIdentityNumber)
                .visitHour(10)
                .visitMinute(30)
                .ticketNumber(1)
                .accepted(true)
                .build();

        when(patientRepository.findByPersonalIdentityNumber(personalIdentityNumber))
                .thenReturn(expectedPatient);

        // When
        PatientDTO result = patientService.getPatient(personalIdentityNumber);

        // Then
        assertNotNull(result);
        assertEquals(expectedPatient.getId(), result.id());
        assertEquals(expectedPatient.getName(), result.name());
        assertEquals(expectedPatient.getSurname(), result.surname());
        assertEquals(expectedPatient.getPersonalIdentityNumber(), result.personalIdentityNumber());
        assertEquals(expectedPatient.getVisitHour(), result.visitHour());
        assertEquals(expectedPatient.getVisitMinute(), result.visitMinute());
        assertEquals(expectedPatient.getTicketNumber(), result.ticketNumber());
        assertEquals(expectedPatient.isAccepted(), result.accepted());

        verify(patientRepository, times(1)).findByPersonalIdentityNumber(personalIdentityNumber);
    }

    @Test
    void testGetPatients() {
        // Given
        Patient patient1 = Patient.builder()
                .id(1L)
                .name("John")
                .surname("Doe")
                .personalIdentityNumber(123456789L)
                .visitHour(10)
                .visitMinute(30)
                .ticketNumber(1)
                .accepted(true)
                .build();

        Patient patient2 = Patient.builder()
                .id(2L)
                .name("Jane")
                .surname("Smith")
                .personalIdentityNumber(987654321L)
                .visitHour(14)
                .visitMinute(45)
                .ticketNumber(2)
                .accepted(false)
                .build();

        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient1, patient2));

        // When
        List<PatientDTO> result = patientService.getPatients();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());

        PatientDTO resultPatient1 = result.get(0);
        assertEquals(patient1.getId(), resultPatient1.id());
        assertEquals(patient1.getName(), resultPatient1.name());
        assertEquals(patient1.getSurname(), resultPatient1.surname());
        assertEquals(patient1.getPersonalIdentityNumber(), resultPatient1.personalIdentityNumber());
        assertEquals(patient1.getVisitHour(), resultPatient1.visitHour());
        assertEquals(patient1.getVisitMinute(), resultPatient1.visitMinute());
        assertEquals(patient1.getTicketNumber(), resultPatient1.ticketNumber());
        assertEquals(patient1.isAccepted(), resultPatient1.accepted());

        PatientDTO resultPatient2 = result.get(1);
        assertEquals(patient2.getId(), resultPatient2.id());
        assertEquals(patient2.getName(), resultPatient2.name());
        assertEquals(patient2.getSurname(), resultPatient2.surname());
        assertEquals(patient2.getPersonalIdentityNumber(), resultPatient2.personalIdentityNumber());
        assertEquals(patient2.getVisitHour(), resultPatient2.visitHour());
        assertEquals(patient2.getVisitMinute(), resultPatient2.visitMinute());
        assertEquals(patient2.getTicketNumber(), resultPatient2.ticketNumber());
        assertEquals(patient2.isAccepted(), resultPatient2.accepted());

        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void testRegisterPatient() {
        // Given
        CreatePatientDTO createPatientDTO = new CreatePatientDTO("John", "Doe", 123412345L, 10, 10);

        // When
        patientService.registerPatient(createPatientDTO);

        // Then
        verify(patientRepository, times(1)).save(any());

        // You might want to add more assertions based on the specific behavior of your method
    }

    @Test
    void testUpdatePatientName() {
        // Given
        Long patientId = 1L;
        String newName = "NewName";

        Patient existingPatient = Patient.builder()
                .id(patientId)
                .name("OldName")
                .surname("Doe")
                .build();

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));

        // When
        PatientDTO result = patientService.updatePatientName(patientId, newName);

        // Then
        assertNotNull(result);
        assertEquals(existingPatient.getId(), result.id());
        assertEquals(newName, result.name());
        assertEquals(existingPatient.getSurname(), result.surname());

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    void testUpdatePatientNameNotFound() {
        // Given
        Long patientId = 1L;
        String newName = "NewName";

        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> patientService.updatePatientName(patientId, newName));

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, never()).save(any());
    }

    @Test
    void testUpdatePatientSurname() {
        // Given
        Long patientId = 1L;
        String newSurname = "NewSurname";

        Patient existingPatient = Patient.builder()
                .id(patientId)
                .name("OldName")
                .surname("OldSurname")
                .build();

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));

        // When
        PatientDTO result = patientService.updatePatientSurname(patientId, newSurname);

        // Then
        assertNotNull(result);
        assertEquals(existingPatient.getId(), result.id());
        assertEquals(existingPatient.getName(), result.name());
        assertEquals(newSurname, result.surname());

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    void testUpdatePatientSurnameNotFound() {
        // Given
        Long patientId = 1L;
        String newSurname = "NewSurname";

        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> patientService.updatePatientSurname(patientId, newSurname));

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, never()).save(any());
    }

    @Test
    void testUpdatePatientTicketNumber() {
        // Given
        Long patientId = 1L;
        Integer newTicketNumber = 42;

        Patient existingPatient = Patient.builder()
                .id(patientId)
                .name("OldName")
                .surname("OldSurname")
                .ticketNumber(7)
                .build();

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));

        // When
        PatientDTO result = patientService.updatePatientTicketNumber(patientId, newTicketNumber);

        // Then
        assertNotNull(result);
        assertEquals(existingPatient.getId(), result.id());
        assertEquals(existingPatient.getName(), result.name());
        assertEquals(existingPatient.getSurname(), result.surname());
        assertEquals(newTicketNumber, result.ticketNumber());

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    void testUpdatePatientTicketNumberNotFound() {
        // Given
        Long patientId = 1L;
        Integer newTicketNumber = 42;

        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> patientService.updatePatientTicketNumber(patientId, newTicketNumber));

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, never()).save(any());
    }

    @Test
    void testUpdatePatientVisitTime() {
        // Given
        Long patientId = 1L;
        PatientUpdateVisitTimeDTO newVisitTimeDTO = new PatientUpdateVisitTimeDTO(14, 30);

        Patient existingPatient = Patient.builder()
                .id(patientId)
                .name("OldName")
                .surname("OldSurname")
                .visitHour(10)
                .visitMinute(15)
                .build();

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));

        // When
        PatientDTO result = patientService.updatePatientVisitTime(patientId, newVisitTimeDTO);

        // Then
        assertNotNull(result);
        assertEquals(existingPatient.getId(), result.id());
        assertEquals(existingPatient.getName(), result.name());
        assertEquals(existingPatient.getSurname(), result.surname());
        assertEquals(newVisitTimeDTO.visitHour(), result.visitHour());
        assertEquals(newVisitTimeDTO.visitMinute(), result.visitMinute());

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    void testUpdatePatientVisitTimeNotFound() {
        // Given
        Long patientId = 1L;
        PatientUpdateVisitTimeDTO newVisitTimeDTO = new PatientUpdateVisitTimeDTO(14, 30);

        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> patientService.updatePatientVisitTime(patientId, newVisitTimeDTO));

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, never()).save(any());
    }

    @Test
    void testUpdatePatientPersonalIdentityNumber() {
        // Given
        Long patientId = 1L;
        Long newPersonalIdentityNumber = 123456789L;

        Patient existingPatient = Patient.builder()
                .id(patientId)
                .name("OldName")
                .surname("OldSurname")
                .personalIdentityNumber(987654321L)
                .build();

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));

        // When
        PatientDTO result = patientService.updatePatientPersonalIdentityNumber(patientId, newPersonalIdentityNumber);

        // Then
        assertNotNull(result);
        assertEquals(existingPatient.getId(), result.id());
        assertEquals(existingPatient.getName(), result.name());
        assertEquals(existingPatient.getSurname(), result.surname());
        assertEquals(newPersonalIdentityNumber, result.personalIdentityNumber());

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, times(1)).save(existingPatient);
    }

    @Test
    void testUpdatePatientPersonalIdentityNumberNotFound() {
        // Given
        Long patientId = 1L;
        Long newPersonalIdentityNumber = 123456789L;

        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> patientService.updatePatientPersonalIdentityNumber(patientId, newPersonalIdentityNumber));

        verify(patientRepository, times(1)).findById(patientId);
        verify(patientRepository, never()).save(any());
    }

//    @Test
//    void testDeletePatient() {
//        // Given
//        Long patientId = 1L;
//
//        Patient existingPatient = Patient.builder()
//                .id(patientId)
//                .name("OldName")
//                .surname("OldSurname")
//                .build();
//
//        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));
//
//        // When
//        patientService.deletePatient(patientId);
//
//        // Then
//        verify(patientRepository, times(1)).findById(patientId);
//        verify(patientRepository, times(1)).deleteById(patientId);
//    }

//    @Test
//    void testDeletePatientNotFound() {
//        // Given
//        Long patientId = 1L;
//
//        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());
//
//        // When, Then
//        assertThrows(RuntimeException.class, () -> patientService.deletePatient(patientId));
//
//        verify(patientRepository, times(1)).findById(patientId);
//        verify(patientRepository, never()).deleteById(any());
//    }

    @Test
    void testDeletePatients() {
        // Given
        List<Patient> existingPatients = new ArrayList<>();
        existingPatients.add(Patient.builder().id(1L).name("John").surname("Doe").build());
        existingPatients.add(Patient.builder().id(2L).name("Jane").surname("Doe").build());

        when(patientRepository.findAll()).thenReturn(existingPatients);

        // When
        patientService.deletePatients();

        // Then
        verify(patientRepository, times(1)).findAll();
        verify(patientRepository, times(1)).deleteAll(existingPatients);
    }


    @Test
    void testGetPatientToUpdate() {
        // Given
        Long patientId = 1L;

        Patient existingPatient = Patient.builder()
                .id(patientId)
                .name("John")
                .surname("Doe")
                .build();

        when(patientRepository.findById(patientId)).thenReturn(Optional.of(existingPatient));

        // When
        Patient result = patientService.getPatientToUpdate(patientId);

        // Then
        assertNotNull(result);
        assertEquals(existingPatient.getId(), result.getId());
        assertEquals(existingPatient.getName(), result.getName());
        assertEquals(existingPatient.getSurname(), result.getSurname());

        verify(patientRepository, times(1)).findById(patientId);
    }

    @Test
    void testGetPatientToUpdateNotFound() {
        // Given
        Long patientId = 1L;

        when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(RuntimeException.class, () -> patientService.getPatientToUpdate(patientId));

        verify(patientRepository, times(1)).findById(patientId);
    }
}
//    @Test
//    public void testGetLongestWaitingPatient() {
//        // Mocking data
//        Long doctorId = 1L;
//        Doctor doctor = new Doctor();
//        doctor.setOfficeNumber(123);
//
//        Long patientId = 1L;
//        Patient patient = new Patient();
//        patient.setTicketNumber(123);
//
//        // Mocking behavior
//        Mockito.when(doctorService.getDoctor(doctorId)).thenReturn(DoctorDTO.from(doctor));
//
//        // Call the method to be tested
//        AcceptPatientDTO result = patientService.getLongestWaitingPatient(doctorId);
//
//        // Assertions
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(123, result.ticketNumber());
//        Assertions.assertEquals(123, result.officeNumber());
//        Mockito.verify(patientRepository, Mockito.times(1)).save(patient);
//        Mockito.verify(patientCleanupService, Mockito.times(1)).schedulePatientRemoval(123, 60000);
//    }
//}