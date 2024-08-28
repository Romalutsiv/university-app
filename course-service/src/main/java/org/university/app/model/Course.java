package org.university.app.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Course {
    @Id
    private String id;
    private String courseName;
    private String lectorId;
    private List<UniversityClass> classes;

}
