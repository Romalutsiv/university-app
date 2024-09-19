package org.university.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.university.app.model.questions.TestType;

import java.util.Map;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentAnswers{
    private String testId;
    private Map<String, String> answers;
    private TestType testType;
    private double mark;
}
