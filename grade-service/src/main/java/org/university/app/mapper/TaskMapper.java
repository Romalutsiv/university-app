package org.university.app.mapper;

import org.university.app.dto.TaskRequest;
import org.university.app.dto.TaskResponse;
import org.university.app.model.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskMapper {
    Mono<Task> toTask(Mono<TaskRequest> req, String courseId);
    Mono<TaskResponse> toTaskResponseMono(Mono<Task> task);
    Flux<TaskResponse> toTaskResponseFlux(Flux<Task> task);

}
