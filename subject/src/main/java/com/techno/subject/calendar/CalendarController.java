package com.techno.subject.calendar;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subject/calendar")
@CrossOrigin(origins = "http://gcs-conf.com")
public class CalendarController {

    @Autowired
    private final CalendarService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCalendar(@RequestBody Calendar calendar){
        service.saveCalendar(calendar);
    }
    @GetMapping
    public ResponseEntity<List<Calendar>> findAllCalendars(){
        return ResponseEntity.ok(service.findAllCalendars());
    }
    @PutMapping(path = "{calenderId}")
    public void updateCalender(
            @PathVariable("calenderId") Long calenderId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String responsible,
            @RequestParam(required = false) String nurseryClass,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String startDate) {

        service.updateCalender(calenderId, title, responsible, nurseryClass, startDate, endDate);
    }

    @GetMapping("/mobile/by-class-and-date")
    public ResponseEntity<List<Calendar>>  findCalenderByClassAndDate(
            @RequestParam("nurseryClass") String nurseryClass,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return ResponseEntity.ok(service.findCalenderByClassAndDate(nurseryClass, startDate, endDate));
    }
    @GetMapping("/activities-for-today")
    public ResponseEntity<List<Calendar>> findActivitiesForToday() {
        List<Calendar> activitiesForToday = service.findActivitiesForToday();
        return ResponseEntity.ok(activitiesForToday);
    }
    @GetMapping("/activities-for-today-grouped-by-class")
    public ResponseEntity<Map<String, List<Calendar>>> findActivitiesForTodayGroupedByClass() {
        Map<String, List<Calendar>> activitiesByClass = service.findActivitiesForTodayGroupedByClass();
        return ResponseEntity.ok(activitiesByClass);
    }
    @GetMapping("/mobile/by-responsible-and-date")
    public ResponseEntity<List<Calendar>>  findCalenderByResponsibleAndDate(
            @RequestParam("responsible") String responsible,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        return ResponseEntity.ok(service.findCalenderByResponsibleAndDate(responsible, startDate, endDate));
    }
    @DeleteMapping(path = "{calendar-Id}")
    public ResponseEntity<String> deleteCalendar(@PathVariable("calendar-Id") Long calendarId) {
        service.deleteCalendar(calendarId);
        return ResponseEntity.ok("Calendar delete successfully");
    }
}
