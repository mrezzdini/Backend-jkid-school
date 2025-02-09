package com.techno.events.model.ParentActivity;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ParentActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private Long id;
    private String img;
    private String activityName;
    private String startDate;
    private String deadline;
    @Column(length = 300)
    private String details;
    private String responseType;
    private String parentComment;
    private String parentResponse;
    private String status;
    private Integer parentId;
    private String studentName;
    private String classes;
}
