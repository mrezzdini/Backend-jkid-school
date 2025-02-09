package com.techno.events.model.event.client;

import com.techno.events.model.event.Student1;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient(name = "studentEvent-service", url = "${application.config.student-url}")
public interface StudentClient {
    @GetMapping("/classes")
    ResponseEntity<List<Student1>> findAllStudentsByClasses(@RequestParam String classes);
}