package com.technoparent.family;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/family")
public class FamilyMemberController {
    private final FamilyMemberService service;
    @GetMapping
    public ResponseEntity<List<FamilyMember>> findAllFamilyMember(){
        return  ResponseEntity.ok(service.findAllFamilyMember());
    }

    @PostMapping
    public void save (@RequestBody FamilyMember familyMember){
        service.saveFamilyMember(familyMember);
    }

}
