package dev.students.medicalofficeservice.core.schedule;

import dev.students.medicalofficeservice.core.doctor.Doctor;
import dev.students.medicalofficeservice.core.doctor.DoctorRepository;
import dev.students.medicalofficeservice.core.schedule.dto.ScheduleDTO;
import dev.students.medicalofficeservice.core.schedule.dto.WorkHourDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;


    public WorkHourDTO setWorkHours(WorkHourDTO hours) {
        Doctor doctor = getDoctorFromRepository(hours.doctorId());
        Optional<Schedule> existingWorkDay = getWorkDay(hours, doctor);
        Schedule schedule = new Schedule();
        if (existingWorkDay.isPresent()) {
            schedule = existingWorkDay.get();
        } else {
            schedule.setDate(hours.date());
            schedule.getDoctors().add(doctor);
            doctor.getSchedules().add(schedule);
        }
        WorkHour workHour = getWorkHour(hours, schedule);
        if (schedule.getHours() == null) {
            schedule.setHours(List.of(workHour));
            scheduleRepository.save(schedule);
            return hours;
        }
        schedule.getHours().add(workHour);
        scheduleRepository.save(schedule);
        return hours;
    }


    public void removeAllWorkHoursFromLastMonth(Long doctorId) {
        Doctor doctor = getDoctorFromRepository(doctorId);
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        Iterator<Schedule> iterator = doctor.getSchedules().iterator();
        while (iterator.hasNext()) {
            Schedule schedule = iterator.next();
            if (!schedule.getDate().isBefore(oneMonthAgo)) {
                iterator.remove();
                scheduleRepository.delete(schedule);
            }
        }
        doctorRepository.save(doctor);
    }

    public void removeWorkHours(Long id, LocalDate date) {
        Doctor doctor = getDoctorFromRepository(id);
        Optional<Schedule> workDayOptional = doctor.getSchedules().stream()
                .filter(workDay -> workDay.getDate().equals(date))
                .findFirst();

        if (workDayOptional.isPresent()) {
            Schedule schedule = workDayOptional.get();
            schedule.setHours(new ArrayList<>());
            scheduleRepository.save(schedule);
        } else {
            throw new NoSuchElementException("No work schedule found for doctor with id " + id + " on date " + date);
        }
    }


    private static WorkHour getWorkHour(WorkHourDTO hours, Schedule schedule) {
        WorkHour workHour = new WorkHour();
        workHour.setStartTime(LocalTime.of(hours.startHour(), hours.startMinute()));
        workHour.setEndTime(LocalTime.of(hours.endHour(), hours.endMinute()));
        workHour.setSchedule(schedule);
        return workHour;
    }

    private static Optional<Schedule> getWorkDay(WorkHourDTO hours, Doctor doctor) {
        return doctor.getSchedules().stream()
                .filter(workDay -> workDay.getDate().equals(hours.date()))
                .findFirst();
    }

    private Doctor getDoctorFromRepository(Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Doctor with id " + id + " not found"));
    }

    public List<ScheduleDTO> getSchedule() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(this::convertToScheduleDTO).collect(Collectors.toList());
    }
    private ScheduleDTO convertToScheduleDTO(Schedule schedule) {
        List<ScheduleDTO.DoctorSchedule> doctorSchedules = schedule.getDoctors().stream()
                .map(doctor -> {
                    String doctorName = doctor.getName() + " " + doctor.getSurname();
                    List<ScheduleDTO.DoctorSchedule.WorkHourInfo> workHours = schedule.getHours().stream()
                            .map(workHour -> new ScheduleDTO.DoctorSchedule.WorkHourInfo(workHour.getStartTime(), workHour.getEndTime()))
                            .collect(Collectors.toList());
                    return new ScheduleDTO.DoctorSchedule(doctorName, workHours);
                }).collect(Collectors.toList());

        return new ScheduleDTO(schedule.getDate(), doctorSchedules);
    }
}
