package com.techno.events.model.ParentActivity.client;

import com.techno.events.model.ParentActivity.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "student-service", url = "${application.config.students-url}")
public interface StudentClient {
    @GetMapping("/classes")
    ResponseEntity<List<Student>> findAllStudentsByClasses(@RequestParam String classes);
}
