package com.techno.events.model.event;


import com.techno.events.model.event.client.StudentClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final StudentClient client;

    public void saveAllEvent(Event event) {
         repository.save(event);
    }
    public List<Event> findAllEvent() {
        return repository.findAll();
    }
    public void deleteEvent(Long id) {
        repository.deleteById(id);
    }
    public List<Event> getAllUniqueEvents() {
        List<Event> allEvents = repository.findAll();

        List<Event> filteredEvents = allEvents.stream()
                .collect(Collectors.groupingBy(
                        event ->
                        event.getTitle() +
                        event.getStartDate()  +
                        event.getType() +
                        event.getDetails()))
                .values()
                .stream()
                .map(events -> events.get(0))
                .collect(Collectors.toList());
        return filteredEvents;
    }
    public ResponseEntity<String> saveEventByClass(Event event) {
        List<Student1> students = client.findAllStudentsByClasses(event.getClasses()).getBody();

        if (students != null && !students.isEmpty()) {
            for (Student1 stds : students) {
                Event newEvent = new Event();
                newEvent.setParentId(stds.getParentId());
                newEvent.setStudentName(stds.getName());
                newEvent.setClasses(event.getClasses());
                newEvent.setStatus(event.getStatus().toLowerCase());
                newEvent.setStartDate(event.getStartDate());
                newEvent.setTitle(event.getTitle());
                newEvent.setType(event.getType());
                newEvent.setDetails(event.getDetails());
                saveAllEvent(newEvent);
            }
            return ResponseEntity.ok("Parent Activity saved successfully for all students in class " + event.getClasses());
        }
        return ResponseEntity.ok("Either the specified class does not exist or there are no students enrolled in this class yet.");
    }
    @Transactional
    public ResponseEntity<String> updateEventByClass(Event event) {
        Optional<Event> oldEvent = repository.findById(event.getId());
        if (oldEvent.isPresent()) {
            Event old = oldEvent.get();
            List<Event> requiredEvents = repository.findAllByClassesAndTitleAndDetailsAndStartDateAndDeadline(
                    old.getClasses(), old.getTitle(), old.getDetails(), old.getStartDate(), old.getDeadline());

            if (requiredEvents != null && !requiredEvents.isEmpty()) {
                for (Event evt : requiredEvents) {
                    evt.setDeadline(event.getDeadline());
                    evt.setDetails(event.getDetails());
                    evt.setStartDate(event.getStartDate());
                    evt.setTitle(event.getTitle());
                    evt.setStatus(event.getStatus());
                    evt.setType(event.getType());
                    repository.save(evt); // Saving each updated event
                }
                return ResponseEntity.ok("Event updated successfully for all students in class " + event.getClasses());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Either the specified class does not exist or there are no students enrolled in this class yet.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event not found.");
    }
}
