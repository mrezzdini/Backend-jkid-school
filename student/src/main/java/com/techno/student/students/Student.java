package com.techno.student.students;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String name;
    private String gender;
    private String cpr;
    private String passport;
    private String img;
    private String parentName;
    private String classes;
    private String date;
    private String phoneEmergency;
    private String healthyInformation;
    private String vaccineProgress;
    private Integer parentId;
    private String pickUpDate;
    private String status;
    private String cprFile;
    private String passportFile;
    private String vaccineDocument;
    private String visaDocument;
}
