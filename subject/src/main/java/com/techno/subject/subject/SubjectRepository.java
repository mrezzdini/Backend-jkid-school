package com.techno.subject.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {


    @Query("SELECT s FROM Subject s WHERE s.name = ?1")
    Optional<Subject> findSubjectByName(String name);
}
