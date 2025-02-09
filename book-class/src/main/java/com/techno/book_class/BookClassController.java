package com.techno.book_class;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/bookClass")
@CrossOrigin(origins = "http://gcs-conf.com/")
public class BookClassController {

    private final BookClassService service;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveBookClass(@RequestBody BookClass bookClass){
        service.saveAllBookClass(bookClass);
        return  "bookClass created successfly";
    }
    @GetMapping
    public ResponseEntity<List<BookClass>> findAllBookClass(){
        return ResponseEntity.ok(service.findAllBookClass());
    }
}




