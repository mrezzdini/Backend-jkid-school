package com.techno.payment.fees;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeesService {

    private final FeesRepository repository;



    
    public void saveAllPayment(Fees fees) {
        repository.save(fees);
    }

    public List<Fees> findAllPayment() {
        return repository.findAll();
    }

    public void deleteFees(Long id) {
        repository.deleteById(id);
    }

    /*public List<Fees> saveAllPayments(List<Fees> fees) {
        List<Fees> list = new ArrayList<>();
        for (Fees fee :fees) {
            Fees savedPayment= savePayment(fee);
            list.add(savedPayment);
        }
        return list;
    }*/
}