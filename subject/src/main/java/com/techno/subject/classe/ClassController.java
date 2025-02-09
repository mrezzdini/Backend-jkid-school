package com.techno.subject.classe;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/subject/class")
@CrossOrigin(origins = "http://gcs-conf.com")
public class ClassController {

    private final ClassService service;

    @PostMapping
    public void save(@RequestBody Classe classes){
        service.savedClasses(classes);
    }
    @GetMapping
    public ResponseEntity<List<Classe>> findAllClasses(){
        return ResponseEntity.ok(service.findAllClasses());
    }
    @DeleteMapping(path = "{subject-Id}")
    public ResponseEntity<String> deleteClasses(@PathVariable("subject-Id") Long classesId){
        service.deleteClasses(classesId);
        return ResponseEntity.ok("class delete successfully");
    }
}
