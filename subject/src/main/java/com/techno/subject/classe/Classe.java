package com.techno.subject.classe;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = ("classes"))
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String cname;
    private String hod;
    private String phone;
    private String email;
    private String syear;
    private String scapacity;
}
