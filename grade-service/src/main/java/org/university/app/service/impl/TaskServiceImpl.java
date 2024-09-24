package org.university.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.university.app.dto.TaskRequest;
import org.university.app.exception.DocumentNotFoundException;
import org.university.app.mapper.TaskMapper;
import org.university.app.model.Task;
import org.university.app.repository.TaskRepository;
import org.university.app.service.TaskService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Override
    public Flux<Task> getCourseTasks(String courseId) {
        return taskRepository.findAllByCourseId(courseId);
    }

    @Override
    public Mono<Task> getOneById(String id) {
        return taskRepository.findById(id)
                .switchIfEmpty(Mono.error(new DocumentNotFoundException(String.format("Task with id: %s not found!", id))));
    }

    @Override
    public Mono<Task> createTask(Mono<TaskRequest> taskDto, String courseId) {
        return taskMapper.toTask(taskDto, courseId)
                .flatMap(taskRepository::save);
    }

    @Override
    public Mono<Task> updateTask(String id, Mono<TaskRequest> taskDto,  String courseId) {
        return null;
    }

    @Override
    public Mono<Void> deleteTask(String id) {
        return getOneById(id).flatMap(taskRepository::delete);
    }
}
