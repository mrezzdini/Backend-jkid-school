package com.techno.student.students;

import com.techno.student.students.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByParentId(Integer  parentId);

    List<Student> findAllByClasses(String classes);

    List<Student> findAllByParentName(String parentName);
}
