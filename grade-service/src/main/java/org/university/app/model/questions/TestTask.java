package org.university.app.model.questions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.university.app.model.Task;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TestTask {
    @Id
    private String id;
    private String question;
    @DocumentReference
    private Task task;
    private List<TestAttribute> attributes;
    private List<String> allAnswers;
    private TestType testType;
}
