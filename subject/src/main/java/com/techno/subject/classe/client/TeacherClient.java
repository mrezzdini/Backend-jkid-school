package com.techno.subject.classe.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "teacher-service", url = "${application.config.teachers-url}")
public interface TeacherClient {

    @PostMapping("/affect-class")
    public void affectTeacherToClass(@RequestParam String email, @RequestParam String className);
}