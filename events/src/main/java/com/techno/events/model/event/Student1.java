package com.techno.events.model.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student1 {
    private Integer id;
    private String name;
    private String classes;
    private String status;
    private String parentName;
    private Integer parentId;
}