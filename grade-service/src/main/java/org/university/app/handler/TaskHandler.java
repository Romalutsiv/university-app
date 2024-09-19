package org.university.app.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.university.app.dto.TaskRequest;
import org.university.app.model.Task;
import org.university.app.service.TaskService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class TaskHandler {

    private final TaskService taskService;

    public Mono<ServerResponse> getAllCourseTasks(ServerRequest request){
        String courseID = request.pathVariable("courseID");
        Flux<Task> courseTasks = taskService.getCourseTasks(courseID);
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(courseTasks, Task.class);
    }

    public Mono<ServerResponse> getTask(ServerRequest request) {
        String id = request.pathVariable("taskID");
        return ServerResponse
                .ok()
                .contentType(APPLICATION_JSON)
                .body(taskService.getOneById(id), Task.class);
    }

    public Mono<ServerResponse> deleteTask(ServerRequest request) {
        String id = request.pathVariable("taskID");
        return ServerResponse
                .noContent().build(taskService.deleteTask(id));
    }

    public Mono<ServerResponse> createNewTask(ServerRequest request) {
        Mono<TaskRequest> taskDtoMono = request.bodyToMono(TaskRequest.class);
        String courseId = request.pathVariable("courseID");
        return ServerResponse.ok()
                .body(taskService.createTask(taskDtoMono, courseId), Task.class);
    }

    public Mono<ServerResponse> updateTask(ServerRequest request) {
        return null;
    }
}
