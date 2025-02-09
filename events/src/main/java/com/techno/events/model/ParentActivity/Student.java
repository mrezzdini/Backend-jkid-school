package com.techno.events.model.ParentActivity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private Integer id;
    private String name;
    private String classes;
    private String status;
    private String parentName;
    private Integer parentId;
}