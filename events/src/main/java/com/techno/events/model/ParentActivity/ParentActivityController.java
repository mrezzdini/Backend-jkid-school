package com.techno.events.model.ParentActivity;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/parentActivity")
@CrossOrigin(origins = "http://gcs-conf.com")
public class ParentActivityController {

    private final ParentActivityService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody ParentActivity parentActivity){
        service.saveParentActivity(parentActivity);
    }

    @GetMapping
    public ResponseEntity<List<ParentActivity>> findAllParentActivity(){
        return ResponseEntity.ok(service.findAllParentActivity());
    }
    @GetMapping("/filter")
    public ResponseEntity<List<ParentActivity>> getAllUniqueParentActivity(){
        return ResponseEntity.ok(service.getAllUniqueParentActivity());
    }
    @PostMapping("/ActivityByClass")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> saveParentActivityByClass(@RequestBody ParentActivity parentActivity) {
        return service.saveParentActivityByClass(parentActivity);
    }
    @GetMapping("/mobile/by-parent-student")
    public ResponseEntity<List<ParentActivity>> findParentActivityByParentIdAndStudentName(@RequestParam Integer parentId,@RequestParam String studentName){
        return ResponseEntity.ok(service.findParentActivityByParentIdAndStudentName(parentId,studentName));
    }
    @GetMapping("/filter/activityNameClasses")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ParentActivity>> findParentActivityByActivityNameAndClasses (@RequestParam String activityName, @RequestParam String classes){
        return ResponseEntity.ok(service.findParentActivityByActivityNameAndClasses(activityName, classes));
    }

    @PostMapping("/UpdateActivityByClass")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateActivityByClass(@RequestBody ParentActivity parentActivity) {
        return service.updateActivityByClass(parentActivity);
    }
    @DeleteMapping("/{parentActivity-id}")
    public ResponseEntity<String> deleteParentActivity(@PathVariable("parentActivity-id") Long parentActivityId) {
        service.deleteParentActivity(parentActivityId);
        return ResponseEntity.ok("student deleted successfully");
    }
}
