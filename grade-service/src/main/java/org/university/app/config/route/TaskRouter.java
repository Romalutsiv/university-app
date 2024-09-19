package org.university.app.config.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.university.app.handler.TaskHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class TaskRouter {
    @Bean
    public RouterFunction<ServerResponse> route(TaskHandler taskHandler){
        return RouterFunctions
                .route()
                .GET("/api/{courseID}/task", accept(APPLICATION_JSON), taskHandler::getAllCourseTasks)
                .GET("/api/{courseID}/task/{taskID}", accept(APPLICATION_JSON), taskHandler::getTask)
                .POST("/api/{courseID}/task", accept(APPLICATION_JSON), taskHandler::createNewTask)
                .PUT("/api/{courseID}/task/{taskID}", accept(APPLICATION_JSON), taskHandler::updateTask)
                .DELETE("/api/{courseID}/task/{taskID}", accept(APPLICATION_JSON), taskHandler::deleteTask)
                .build();
    }
}
