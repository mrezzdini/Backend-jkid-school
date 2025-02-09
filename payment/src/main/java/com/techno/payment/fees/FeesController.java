package com.techno.payment.fees;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin(origins = "http://gcs-conf.com")
public class FeesController {

    private final FeesService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Fees fees){
        service.saveAllPayment(fees);
    }
    @GetMapping
    public ResponseEntity<List<Fees>> findAllPayment(){
        return ResponseEntity.ok(service.findAllPayment());
    }
    /*@PostMapping("/list")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Fees> saveListPayments(@RequestBody List<Fees> fees){
        return service.saveAllPayments(fees);
    }*/
    @DeleteMapping(path = "{fees-Id}")
    public ResponseEntity<String> deleteFees(@PathVariable("fees-Id") Long feesId){
        service.deleteFees(feesId);
        return ResponseEntity.ok("class delete successfully");
    }
}
