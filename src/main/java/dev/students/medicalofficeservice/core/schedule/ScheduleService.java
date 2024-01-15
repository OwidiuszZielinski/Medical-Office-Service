package dev.students.medicalofficeservice.core.schedule;

import dev.students.medicalofficeservice.core.doctor.Doctor;
import dev.students.medicalofficeservice.core.doctor.DoctorRepository;
import dev.students.medicalofficeservice.core.schedule.dto.WorkHourDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final DoctorRepository doctorRepository;
    private final WorkDayRepository workDayRepository;
    private final WorkHourRepository workHoursRepository;


    public WorkHourDTO setWorkHours(WorkHourDTO hours) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(hours.doctorId());
        if (doctorOptional.isEmpty()) {
            throw new RuntimeException("Lekarz o ID " + hours.doctorId() + " nie został znaleziony.");
        }
        Doctor doctor = doctorOptional.get();
        Optional<WorkDay> existingWorkDay = doctor.getWorkDays().stream()
                .filter(workDay -> workDay.getDate().equals(hours.date()))
                .findFirst();
        WorkDay workDay = new WorkDay();
        if (existingWorkDay.isPresent()) {

            workDay = existingWorkDay.get();
        } else {
            workDay.setDate(hours.date());
            workDay.setDoctor(doctor);
            doctor.getWorkDays().add(workDay);
        }
        // Ustaw godziny pracy
        WorkHour workHour = new WorkHour();
        workHour.setStartTime(LocalTime.of(hours.startHour(), hours.startMinute()));
        workHour.setEndTime(LocalTime.of(hours.endHour(), hours.endMinute()));
        workHour.setWorkDay(workDay);
        if (workDay.getHours() == null) {
            workDay.setHours(List.of(workHour));
            workDayRepository.save(workDay);
            return hours;
        }
        workDay.getHours().add(workHour);
        // Zapisz zmiany
        workDayRepository.save(workDay);
        return hours;
    }
}
