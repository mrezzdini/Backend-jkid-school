package com.example.Teacher.teacher;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TeachersService {
    private final TeachersRepository teachersRepository;


    public List<Teachers> getTeachers(){
        return teachersRepository.findAll();
    }

    public Teachers findTeacherByEmail(String email){
        return teachersRepository.findTeachersByEmail(email).orElseThrow();
    }
    public void saveTeachers(Teachers teachers) {
                teachersRepository.save(teachers);
    }


    public void deleteTeachers(Long id) {
        teachersRepository.deleteById(id);
    }

    public void affectTeacherToClass(String email, String className) {
        Optional<Teachers> teacher=teachersRepository.findTeachersByEmail(email);
        List<Teachers> teachers=teachersRepository.findAllByClasse(className);
        for(Teachers oldTech:teachers){
            oldTech.setClasse(null);
            teachersRepository.save(oldTech);
        }
        if(teacher.isPresent()){
            Teachers t=teacher.get();
            t.setClasse(className);
            teachersRepository.save(t);
        }
    }
}
