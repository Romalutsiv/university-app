package org.university.app.dto;

import java.util.List;

public record TaskRequest(
        String teacherId,
        String taskName,
        long timeToCompleteInSec,
        List<String> includeFilesUrls,
        String closedDate,
        String answerType
) {
}
