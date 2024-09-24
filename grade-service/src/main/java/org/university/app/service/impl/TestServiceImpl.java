package org.university.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.university.app.dto.TaskRequest;
import org.university.app.dto.TestRequest;
import org.university.app.exception.DocumentNotFoundException;
import org.university.app.mapper.TestMapper;
import org.university.app.model.questions.TestTask;
import org.university.app.repository.TestRepository;
import org.university.app.service.TaskService;
import org.university.app.service.TestService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;
    private final TaskService taskService;
    private final TestMapper testMapper;
    @Override
    public Flux<TestTask> getAllTaskTests(String taskId) {
        return taskService.getOneById(taskId)
                .flatMapMany(testRepository::findAllByTask);
    }

    @Override
    public Mono<TestTask> getOneByID(String id) {
        return testRepository.findById(id)
                .switchIfEmpty(Mono.error(new DocumentNotFoundException(String.format("Test with id: %s not found!", id))));
    }

    @Override
    public Flux<TestTask> createTests(String taskID, Flux<TestRequest> testsDto) {
        return taskService.getOneById(taskID)
                .flatMapMany(task -> testsDto
                        .flatMap(testRequest -> testMapper.toModel(Mono.just(testRequest), task))
                        .flatMap(testRepository::save));
    }

    @Override
    public Mono<TestTask> updateTest(String id, Mono<TestRequest> testRequest) {
        return null;
    }

    @Override
    public Mono<Void> deleteTest(String id) {
        return getOneByID(id)
                .flatMap(testRepository::delete);
    }


}
