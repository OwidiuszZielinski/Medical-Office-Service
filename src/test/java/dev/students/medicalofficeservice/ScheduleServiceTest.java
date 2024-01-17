package dev.students.medicalofficeservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import dev.students.medicalofficeservice.core.doctor.Doctor;
import dev.students.medicalofficeservice.core.doctor.DoctorRepository;
import dev.students.medicalofficeservice.core.schedule.Schedule;
import dev.students.medicalofficeservice.core.schedule.ScheduleRepository;
import dev.students.medicalofficeservice.core.schedule.ScheduleService;
import dev.students.medicalofficeservice.core.schedule.dto.ScheduleDTO;
import dev.students.medicalofficeservice.core.schedule.dto.WorkHourDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ScheduleServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private ScheduleRepository scheduleRepository;

    @InjectMocks
    private ScheduleService scheduleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSetWorkHours() {
        // Mock data
        WorkHourDTO workHourDTO = new WorkHourDTO(1L, LocalDate.now(),9,30,17,0 );

        // Mock behavior
        when(doctorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Doctor()));
        when(scheduleRepository.save(any())).thenReturn(new Schedule());

        // Call the method
        WorkHourDTO result = scheduleService.setWorkHours(workHourDTO);

        // Assertions
        assertNotNull(result);
        // Add more assertions based on your business logic
    }

    @Test
    void testRemoveAllWorkHoursFromLastMonth() {
        // Mock data
        Long doctorId = 1L;

        // Mock behavior
        when(doctorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Doctor()));

        // Call the method
        scheduleService.removeAllWorkHoursFromLastMonth(doctorId);

        // Assertions
        // Add assertions to verify the expected behavior
    }

//    @Test
//    void testRemoveWorkHours() {
//        // Mock data
//        Long id = 1L;
//        LocalDate date = LocalDate.now();
//
//        // Mock behavior
//        when(doctorRepository.findById(anyLong())).thenReturn(java.util.Optional.of(new Doctor()));
//
//        // Call the method
//        assertDoesNotThrow(() -> scheduleService.removeWorkHours(id, date));
//
//        // Assertions
//        // Add assertions to verify the expected behavior
//    }

    @Test
    void testGetSchedule() {
        // Mock data
        List<Schedule> schedules = new ArrayList<>();
        when(scheduleRepository.findAll()).thenReturn(schedules);

        // Call the method
        List<ScheduleDTO> result = scheduleService.getSchedule();

        assertNotNull(result);

    }
}