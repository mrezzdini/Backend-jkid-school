package com.example.Teacher.teacher;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String img;
    private String username;
    private String email;
    private String date;
    private String gender;
    private String mobile;
    private String classe;
    private String degree;
    private String cpr;
    private String passport;
    private String contractType;
    private String department;
}

