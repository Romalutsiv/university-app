package org.university.app.mapper;

import org.university.app.dto.TestRequest;
import org.university.app.dto.TestResponse;
import org.university.app.model.Task;
import org.university.app.model.questions.TestTask;
import reactor.core.publisher.Mono;

public interface TestMapper {
    Mono<TestTask> toModel(Mono<TestRequest> req, Task task);
    Mono<TestResponse> toResponseDto(Mono<TestTask> test);
}
