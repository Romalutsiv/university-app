package org.university.app.dto;

import org.university.app.model.questions.TestAttribute;
import org.university.app.model.questions.TestType;

import java.util.List;

public record TestRequest(
        String testType,
        String question,
        List<TestAttribute> attributes,
        List<String> allAnswers
) {
}
