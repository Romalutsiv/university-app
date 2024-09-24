package org.university.app.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.university.app.dto.TaskRequest;
import org.university.app.dto.TaskResponse;
import org.university.app.mapper.TaskMapper;
import org.university.app.model.Task;
import org.university.app.service.TaskService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class TaskHandler {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public Mono<ServerResponse> getAllCourseTasks(ServerRequest request){
        String courseID = request.pathVariable("courseID");
        Flux<TaskResponse> courseTasks = taskMapper.toTaskResponseFlux(taskService.getCourseTasks(courseID));
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(courseTasks, TaskResponse.class);
    }

    public Mono<ServerResponse> getTask(ServerRequest request) {
        String id = request.pathVariable("taskID");
        Mono<TaskResponse> task = taskMapper.toTaskResponseMono(taskService.getOneById(id));
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(task, TaskResponse.class);
    }

    public Mono<ServerResponse> deleteTask(ServerRequest request) {
        String id = request.pathVariable("taskID");
        return ServerResponse
                .noContent().build(taskService.deleteTask(id));
    }

    public Mono<ServerResponse> createNewTask(ServerRequest request) {
        Mono<TaskRequest> taskDtoMono = request.bodyToMono(TaskRequest.class);
        String courseId = request.pathVariable("courseID");
        Mono<TaskResponse> task = taskMapper.toTaskResponseMono(taskService.createTask(taskDtoMono, courseId));
        return ServerResponse.ok()
                .body(task, TaskResponse.class);
    }

    public Mono<ServerResponse> updateTask(ServerRequest request) {
        return null;
    }
}
