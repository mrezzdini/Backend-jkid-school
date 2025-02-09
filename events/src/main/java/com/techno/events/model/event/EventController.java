package com.techno.events.model.event;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
@CrossOrigin(origins = "http://gcs-conf.com")
public class EventController {

    private final EventService service;
    @PostMapping
    public void save(@RequestBody Event event){
        service.saveAllEvent(event);
    }
    @PutMapping("/UpdateEventByClass")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateEventByClass(@RequestBody Event event) {
        return service.updateEventByClass(event);
    }
    @GetMapping
    public ResponseEntity<List<Event>> findAllEvent(){
        return ResponseEntity.ok(service.findAllEvent());
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Event>> getAllUniqueEvents(){
        return ResponseEntity.ok(service.getAllUniqueEvents());
    }
    @DeleteMapping(path = "{event-Id}")
    public ResponseEntity<String> deleteClasses(@PathVariable("event-Id") Long eventId){
        service.deleteEvent(eventId);
        return ResponseEntity.ok("event delete successfully");
    }
    @PostMapping("/EventByClass")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> saveEventByClass(@RequestBody Event event) {
        return service.saveEventByClass(event);
    }
}
