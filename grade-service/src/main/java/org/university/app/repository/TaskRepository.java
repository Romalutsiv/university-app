package org.university.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.university.app.model.Task;
import reactor.core.publisher.Flux;

public interface TaskRepository extends ReactiveMongoRepository<Task, String> {
    Flux<Task> findAllByCourseId(String courseId);
}
