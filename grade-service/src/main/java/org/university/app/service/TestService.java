package org.university.app.service;

import org.university.app.dto.TestRequest;
import org.university.app.model.questions.TestTask;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TestService {
    Flux<TestTask> getAllTaskTests(String taskId);
    Mono<TestTask> getOneByID(String id);
    Flux<TestTask> createTests(String taskID, Flux<TestRequest> testDto);
    Mono<TestTask> updateTest(String id, Mono<TestRequest> testRequest);
    Mono<Void> deleteTest(String id);

}
