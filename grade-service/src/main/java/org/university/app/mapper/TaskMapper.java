package org.university.app.mapper;

import org.university.app.dto.TaskRequest;
import org.university.app.model.Task;
import reactor.core.publisher.Mono;

public interface TaskMapper {
    Mono<Task> toTask(Mono<TaskRequest> req, String courseId);

}
