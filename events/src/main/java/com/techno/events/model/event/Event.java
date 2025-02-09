package com.techno.events.model.event;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String title;
    private String startDate;
    private String deadline;
    private String type;
    private String details;
    private String status;
    private String classes;
    private String studentName;
    private Integer parentId;

}
