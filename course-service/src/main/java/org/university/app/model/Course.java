package org.university.app.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private String id;
    private String courseName;
    private String lectorId;
    private List<UniversityClass> classes;

}
