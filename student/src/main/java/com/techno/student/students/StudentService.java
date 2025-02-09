package com.techno.student.students;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public void saveStudent(Student student){
        repository.save(student);
    }
    public List<Student> findAllStudents (){
        return repository.findAll();
    }
    public List<Student> findAllStudentsByParent(Integer parentId)  {
        return repository.findAllByParentId(parentId);
    }
    public List<Student> findAllStudentsByClasses(String classes){
        return repository.findAllByClasses(classes);
    }
    public List<Student> findAllStudentsByParentName(String parentName) {
        return repository.findAllByParentName(parentName);
    }
    public void deleteStudent(Integer id) {
        repository.deleteById(id);
    }
    public void pickUpStudent(Integer studentId, String pickUpDate) {
        Student student=repository.findById(studentId).orElseThrow();
        student.setPickUpDate(pickUpDate);
        repository.save(student);
    }
}
