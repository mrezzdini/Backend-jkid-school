package com.techno.student.students;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://gcs-conf.com")
public class StudentController {
    private final StudentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Student student){
        service.saveStudent(student);
    }
    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents(){
        return ResponseEntity.ok(service.findAllStudents());
    }

    @GetMapping("/parent/{parent-id}")
    public ResponseEntity<List<Student>> findAllStudent(
            @PathVariable("parent-id") Integer parentId

    ){
        return ResponseEntity.ok(service.findAllStudentsByParent(parentId));
    }
    @GetMapping("/classes")
    public ResponseEntity<List<Student>> findAllStudentsByClasses(@RequestParam String classes){
        return ResponseEntity.ok(service.findAllStudentsByClasses(classes));
    }
    @GetMapping("/parents/parentName")
    public ResponseEntity<List<Student>> findAllStudentsByParentName(
            @RequestParam String parentName

    ){
        return ResponseEntity.ok(service.findAllStudentsByParentName(parentName));
    }
    @PostMapping("/pick-up/{student-id}")
    public ResponseEntity<String> pickUpStudent(@PathVariable("student-id") Integer studentId,
                                                @RequestParam(required = true) String pickUpDate

    ){
        service.pickUpStudent(studentId,pickUpDate);
        return ResponseEntity.ok("student pickedUP successfully");
    }
    @DeleteMapping("/{student-id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("student-id") Integer studentId) {
        service.deleteStudent(studentId);
        return ResponseEntity.ok("student deleted successfully");
    }
}
