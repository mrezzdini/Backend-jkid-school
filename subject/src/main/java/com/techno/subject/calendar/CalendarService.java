package com.techno.subject.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository repository;

    public void saveCalendar(Calendar calendar) {
        repository.save(calendar);
    }

    public List<Calendar> findAllCalendars() {
        return repository.findAll();
    }
    @Transactional
    public void updateCalender(Long calenderId,String title,String responsible,String nurseryClass,String startDate,String endDate)
    {
        Calendar calendar = repository.findById(calenderId).orElseThrow(
                () -> new IllegalStateException(
                        "Calendar with id" + calenderId + "was not found")
        );

        if (title != null && !title.isEmpty() &&
                !Objects.equals(calendar.getTitle(), title)) {
            calendar.setTitle(title);
        }
        if (nurseryClass != null && !nurseryClass.isEmpty() &&
                !Objects.equals(calendar.getNurseryClass(), nurseryClass)) {
            calendar.setNurseryClass(nurseryClass);
        }

        if (startDate != null && !startDate.isEmpty() &&
                !Objects.equals(calendar.getStartDate(), startDate)) {
            calendar.setStartDate(startDate);
        }
        if (endDate != null && !endDate.isEmpty() &&
                !Objects.equals(calendar.getEndDate(), endDate)) {
            calendar.setEndDate(endDate);
        }
        if (responsible != null && !responsible.isEmpty() &&
                !Objects.equals(calendar.getResponsible(), responsible)) {
            calendar.setResponsible(responsible);
        }
    }
    public List<Calendar> findCalenderByResponsibleAndDate(String responsible, String startDate, String endDate) {

        return repository.findCalenderByResponsibleAndDate( responsible,  startDate,  endDate);

    }

    public Map<String, List<Calendar>> findActivitiesForTodayGroupedByClass() {
        LocalDate today = LocalDate.now();
        String startDate = today.toString();
        String endDate = today.toString(); // Assuming you want events that end on the same day they start

        List<Calendar> activitiesForToday = repository.findCalenderByDateRange(startDate, endDate);

        // Group activities by class
        Map<String, List<Calendar>> activitiesByClass = new HashMap<>();
        for (Calendar activity : activitiesForToday) {
            String nurseryClass = activity.getNurseryClass();
            List<Calendar> activitiesInClass = activitiesByClass.getOrDefault(nurseryClass, new ArrayList<>());
            activitiesInClass.add(activity);
            activitiesByClass.put(nurseryClass, activitiesInClass);
        }

        return activitiesByClass;
    }
    public List<Calendar> findActivitiesForToday() {
        LocalDate today = LocalDate.now();
        String startDate = today.toString();
        String endDate = today.toString(); // Assuming you want events that end on the same day they start

        return repository.findCalenderByDateRange(startDate, endDate);
    }
    public List<Calendar> findCalenderByClassAndDate(String classes, String startDate, String endDate) {
        return repository.findCalenderByClassAndDate( classes,  startDate,  endDate);

    }
    public void deleteCalendar(Long id) {
        repository.deleteById(id);
    }
}