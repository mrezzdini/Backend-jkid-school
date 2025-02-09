package com.technoparent.family;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FamilyMemberService {

    private final FamilyMemberRepo repository;

    public void saveFamilyMember(FamilyMember familyMember){
        repository.save(familyMember);
    }
    public List<FamilyMember> findAllFamilyMember(){
       return repository.findAll();
    }
    public List<FamilyMember> findAllFamilyMemberByParent(Integer memberId)  {
        return repository.findAllByMemberId(memberId);
    }

}
