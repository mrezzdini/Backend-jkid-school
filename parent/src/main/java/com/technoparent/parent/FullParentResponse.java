package com.technoparent.parent;

import com.technoparent.family.FamilyMember;
import lombok.*;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullParentResponse {
    private Integer id;
    private String username;
    private String email;
    private String cpr;
    private String passport;
    private String address;
    private String photo;
    private String phoneNumber;
    private String emergencyPhone;
    private String status;

    List<Student> students;
    List<FamilyMember> family;
}
