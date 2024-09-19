package org.university.app.model.questions;

import java.util.Map;

public record StudentTests(
        String testId,
        String taskId,
        Map<String, String> questionAndAnswers

) {
}
