package org.university.app.dto;

import java.util.List;

public record CourseDto(
    String id,
    String courseName,
    String lectorId,
    List<String> classIds
) {
}
