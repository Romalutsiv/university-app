package org.university.app.dto;

import java.util.List;

public record TaskResponse(
        String id,
        String taskName,
        List<String> includeFilesUrls,
        String createdDate,
        String updatedDate,
        String closedDate,
        String answerType,
        String taskStatus

) {
}
