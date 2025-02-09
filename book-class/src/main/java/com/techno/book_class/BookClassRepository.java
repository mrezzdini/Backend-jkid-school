package com.techno.book_class;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookClassRepository extends JpaRepository<BookClass, Long> {

}
