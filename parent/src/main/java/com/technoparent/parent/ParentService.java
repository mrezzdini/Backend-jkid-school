package com.technoparent.parent;

import com.technoparent.parent.client.StudentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository repository;
    private  final StudentClient client;

    public void saveParent(Parent parent){
        repository.save(parent);
    }
    public List<Parent> findAllParents (){
        return repository.findAll();
    }

    public FullParentResponse findParentByEmail(String email){
        var parent = repository.findByEmail(email).orElseThrow();
        var students = client.findAllStudentsByParent(parent.getId());
        return FullParentResponse.builder()
                .username(parent.getUsername())
                .email(parent.getEmail())
                .photo(parent.getPhoto())
                .id(parent.getId())
                .phoneNumber(parent.getPhoneNumber())
                .emergencyPhone(parent.getEmergencyPhone())
                .cpr(parent.getCpr())
                .address(parent.getAddress())
                .passport(parent.getPassport())
                .status(parent.getStatus())
                .students(students)
                .build();
    }


    public FullParentResponse findParentsWithStudents(Integer parentId){
        var parent = repository.findById(parentId).orElse(
                Parent.builder()
                        .username("NOT_FOUND")
                        .email("NOT_FOUND")
                        .photo("NOT_FOUND")
                        .build()
        );
        var students = client.findAllStudentsByParent(parentId);
        return FullParentResponse.builder()
                .username(parent.getUsername())
                .email(parent.getEmail())
                .photo(parent.getPhoto())
                .id(parent.getId())
                .cpr(parent.getCpr())
                .address(parent.getAddress())
                .passport(parent.getPassport())
                .students(students)
                .build();
    }
    public FullParentResponse findParentsWithfamilyMember(Integer memberId){
        var parent = repository.findById(memberId).orElse(
                Parent.builder()
                        .username("NOT_FOUND")
                        .email("NOT_FOUND")
                        .build()
        );
        var students = client.findAllStudentsByParent(memberId);
        return FullParentResponse.builder()
                .username(parent.getUsername() )
                .email(parent.getEmail())
                .students(students)
                .photo(parent.getPhoto())
                .id(parent.getId())
                .build();
    }

    public void deleteParent(Integer id) {
        repository.deleteById(id);
    }
}
