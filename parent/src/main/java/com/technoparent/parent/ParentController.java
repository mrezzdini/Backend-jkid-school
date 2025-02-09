package com.technoparent.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/parents")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Parent parent){
        service.saveParent(parent);
    }
    @GetMapping
    public ResponseEntity<List<Parent>> findAllParents(){
        return ResponseEntity.ok(service.findAllParents());
    }
    @GetMapping("by-email/{parent-email}")
    public FullParentResponse findParentByEmail(
            @PathVariable("parent-email") String email){
        return service.findParentByEmail(email);
    }
    @GetMapping("/with-students/{parent-id}")
    public ResponseEntity<FullParentResponse> findAllParentWithStudent(
            @PathVariable("parent-id") Integer parentId){
        return ResponseEntity.ok(service.findParentsWithStudents(parentId));
    }
    @GetMapping("/with-family/{member-id}")
    public ResponseEntity<FullParentResponse>findAllParentsWithFamily
            (@PathVariable("member-id") Integer memberId){
        return ResponseEntity.ok(service.findParentsWithfamilyMember(memberId));
    }
    @DeleteMapping("/{parent-id}")
    public ResponseEntity<String> deleteParent(@PathVariable("parent-id") Integer parentId) {
        service.deleteParent(parentId);
        return ResponseEntity.ok("Parent deleted successfully");
    }
}