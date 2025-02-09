package com.technoparent.family;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyMemberRepo extends JpaRepository<FamilyMember, Integer> {

    List<FamilyMember> findAllByMemberId(Integer  memberId);
}
