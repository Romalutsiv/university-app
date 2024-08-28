package org.university.app.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UniversityClass {
    private String id;
    private int yearOfStudy;
    private String className;
    private List<String> students;

}
