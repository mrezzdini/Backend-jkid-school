package com.example.Teacher.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface TeachersRepository extends JpaRepository<Teachers, Long> {


    @Query("SELECT s FROM Teachers s WHERE s.email = :email")
    Optional<Teachers> findTeachersByEmail(String email);

    List<Teachers> findAllByClasse(String classe);
}
