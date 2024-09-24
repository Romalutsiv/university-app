package org.university.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.university.app.model.Task;
import org.university.app.model.questions.TestTask;
import org.university.app.service.TestService;
import reactor.core.publisher.Flux;

public interface TestRepository extends ReactiveMongoRepository<TestTask, String> {
    Flux<TestTask> findAllByTask(Task task);
}
