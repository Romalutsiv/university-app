package org.university.app.service;

import org.university.app.dto.TaskRequest;
import org.university.app.model.Task;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TaskService {
    Flux<Task> getCourseTasks(String courseId);
    Mono<Task> getOneById(String id);
    Mono<Task> createTask(Mono<TaskRequest> taskDto, String courseId);
    Mono<Task> updateTask(String id, Mono<TaskRequest> taskDto, String courseId);
    Mono<Void> deleteTask(String id);
//    Mono<Task> setAnswer(Task task, Answer answer);
}
