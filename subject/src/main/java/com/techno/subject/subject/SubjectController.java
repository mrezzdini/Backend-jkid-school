package com.techno.subject.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/subject")
@CrossOrigin(origins = "http://gcs-conf.com")
public class SubjectController {


    private final SubjectService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Subject subject){
        service.savedSubject(subject);
        return "Subject created successfully";
    }
    @GetMapping
    public ResponseEntity<List<Subject>> findAllSubject(){
        return ResponseEntity.ok(service.findAllSubject());
    }
    @DeleteMapping(path = "{subject-Id}")
    public ResponseEntity<String> deleteSubject(@PathVariable("subject-Id") Long subjectId) {
        service.deleteSubject(subjectId);
        return ResponseEntity.ok("subject delete successfully");
    }

}
