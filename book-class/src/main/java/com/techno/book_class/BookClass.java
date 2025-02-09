package com.techno.book_class;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BookClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String subject;
    private String duration;
    private String chapter;
    private String specificObject;
    private String objectOfClass;
    private String note;
    private String pedagogicalApproach;
}
