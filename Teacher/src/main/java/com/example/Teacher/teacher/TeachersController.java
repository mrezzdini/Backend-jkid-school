package com.example.Teacher.teacher;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/teachers")
@CrossOrigin(origins = "http://gcs-conf.com")
public class TeachersController {

    private final TeachersService teachersService;

    @GetMapping
    public List<Teachers> getTeachers() {
        return teachersService.getTeachers();
    }
    @GetMapping("by-email/{teacher-email}")
    public Teachers findTeacherByEmail(
            @PathVariable("teacher-email") String email){
        return teachersService.findTeacherByEmail(email);
    }
    @PostMapping("/affect-class")
    public void affectTeacherToClass(@RequestParam String email, @RequestParam String className){
        teachersService.affectTeacherToClass(email,className);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewTeachers(@RequestBody Teachers teachers){
        teachersService.saveTeachers(teachers);
    }
    @DeleteMapping(path = "{teacher-Id}")
    public ResponseEntity<String> deleteTeachers(@PathVariable("teacher-Id") Long teacherId){
        teachersService.deleteTeachers(teacherId);
        return ResponseEntity.ok("teacher delete successfully");
    }
}
