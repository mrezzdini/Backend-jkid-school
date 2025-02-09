package com.techno.subject.subject;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor@NoArgsConstructor
@Builder
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String details;
    private String classes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private List<Chapitre> chapitres;

    // Getters and Setters
}

