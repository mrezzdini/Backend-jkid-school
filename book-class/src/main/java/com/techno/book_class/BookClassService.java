package com.techno.book_class;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookClassService {

    private final BookClassRepository repository;

    public void saveAllBookClass(BookClass bookClass) {
        repository.save(bookClass);
    }

    public List<BookClass> findAllBookClass() {
        return repository.findAll();
    }
}
