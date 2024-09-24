package org.university.app.dto;

import org.university.app.model.questions.TestAttribute;

import java.util.List;

public record TestResponse(
        String id,
        String testType,
        String question,
        List<String> options,
        List<String> answers
) {
}
