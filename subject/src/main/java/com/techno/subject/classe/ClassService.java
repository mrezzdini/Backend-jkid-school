package com.techno.subject.classe;


import com.techno.subject.classe.client.TeacherClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassService {


    private final ClassRepository repository;
    @Autowired
    private final TeacherClient teacherClients;

    public void savedClasses(Classe classes) {
        repository.save(classes);
        if (!classes.getEmail().isEmpty() && !classes.getCname().isEmpty()){
            teacherClients.affectTeacherToClass(classes.getEmail(),classes.getCname());
        }
    }
    public List<Classe> findAllClasses() {
        return repository.findAll();
    }
    public void deleteClasses(Long id) {
        repository.deleteById(id);
    }
}
