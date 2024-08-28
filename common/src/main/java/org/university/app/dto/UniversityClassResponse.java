package org.university.app.dto;

import java.util.List;

public record UniversityClassResponse(
        String id,
        int yearOfStudy,
        String className,
        List<String> studentIds,
        String classTeacherId
) {
}
