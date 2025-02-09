package com.techno.subject.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository repository;

    public void savedSubject(Subject subject) {
        repository.save(subject);
    }
    public List<Subject> findAllSubject (){
        return repository.findAll();
    }
    public void deleteSubject(Long id) {
        repository.deleteById(id);
    }


}
